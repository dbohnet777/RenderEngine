package a3.engine;

import a3.math.Matrix;
import a3.math.Vector3D;
import a3.math.Point3D;

public class Camera {
	private Vector3D u, v, n;
	private Point3D camLoc, lookAt;
	private Matrix viewMatrix;
	private float inc = 0.125f;
	private float rotation = 0.125f;
	
	//Instantiate camera directly behind origin directed at origin
	public Camera() {
		this.camLoc = new Point3D(0.0f, -1.0f, 0.0f);
		this.lookAt = new Point3D();
        calculateUVN();
		setViewMatrix();
	}
	
	//Instantiate camera at given location directed at origin
	public Camera(float x, float y, float z) {
		this.camLoc = new Point3D(x, y, z);
		this.lookAt = new Point3D();
		calculateUVN();
		setViewMatrix();
	}
	
	public Camera(Point3D cameraPoint) {
		this.camLoc = cameraPoint;
		this.lookAt = new Point3D();
        calculateUVN();
        setViewMatrix();
	}
	
	//Instantiate camera at given location directed at given location
	public Camera(float xC, float yC, float zC, float xL, float yL, float zL) {
		this.camLoc = new Point3D(xC, yC, zC);
		this.lookAt = new Point3D(xL, yL, zL);
	    calculateUVN();
        setViewMatrix();
	}
	
	public Camera(Point3D cameraPoint, Point3D lookAtPoint) {
		this.camLoc = cameraPoint;
		this.lookAt = lookAtPoint;
		calculateUVN();
		setViewMatrix();
	}
	
	public Vector3D getForwardAxis() {
		return n;
	}
	
	public Vector3D getUpAxis() {
		return v;
	}
	
	public Vector3D getRightAxis() {
		return u;
	}
	
	public Vector3D getForwardVector() {
		float x = camLoc.getX() - lookAt.getX();
		float y = camLoc.getY() - lookAt.getY();
		float z = camLoc.getZ() - lookAt.getZ();
		return new Vector3D(x, y, z);
	}

	public void calculateUVN(){
        Vector3D worldUp = new Vector3D(0.0f, 1.0f, 0.0f);
        Vector3D temp = new Vector3D();
        this.n = this.getForwardVector().normalize().inverseVector();
        temp = n.cross(worldUp);
        this.u = temp.normalize();
        temp = u.cross(n);
        this.v = temp.normalize();
    }

    public void setViewMatrix(){
	    this.viewMatrix = new Matrix().createCameraMatrix(n, u, camLoc);
    }

	
	public Point3D getLocation() {
		return this.camLoc;
	}
	
	public void setLocation(Point3D newLoc) {
		this.camLoc.setLocation(newLoc);
	}
	
	public void setLocation(float x, float y, float z, float w) {
		this.camLoc.setLocation(x, y, z, w);
	}
	
	public void moveForward() {
	    /* TODO fuckin look at this */
	    //this.camLoc = this.u.normalize().scale(inc);
    }
	
	public void moveBackward() {
		Matrix backwardMatrix = viewMatrix.createTranslationMatrix(0.0f, 0.0f, -inc);
		viewMatrix = viewMatrix.mult(backwardMatrix);
	}
	
	public void moveLeft() {
		Matrix leftMatrix = viewMatrix.createTranslationMatrix(-inc, 0.0f, 0.0f);
		viewMatrix = viewMatrix.mult(leftMatrix);
	}
	
	public void moveRight() {
		Matrix rightMatrix = viewMatrix.createTranslationMatrix(inc, 0.0f, 0.0f);
		viewMatrix = viewMatrix.mult(rightMatrix);
	}
	
	public void moveUp() {
		Matrix upMatrix = viewMatrix.createTranslationMatrix(0.0f, inc, 0.0f);
		viewMatrix = viewMatrix.mult(upMatrix);
	}
	
	public void moveDown() {
		Matrix downMatrix = viewMatrix.createTranslationMatrix(0.0f, -inc, 0.0f);
		viewMatrix = viewMatrix.mult(downMatrix);
	}
	
	public void yawLeft() {
		Matrix yawLeftMatrix = viewMatrix.createRotationMatrix(rotation, v);
		viewMatrix = viewMatrix.mult(yawLeftMatrix);
	}
	
	public void yawRight() {

	}
	
	public void pitchUp() {
		Matrix pitchUpMatrix = viewMatrix.createRotationMatrix(rotation, u);
		viewMatrix = viewMatrix.mult(pitchUpMatrix);
	}
	
	public void pitchDown() {
		Vector3D pitchDownVector = this.getForwardVector();
		pitchDownVector.setY(pitchDownVector.getY() - inc);

	}
	
	public Matrix getViewMatrix() {
		return this.viewMatrix;
	}
	
	public void setIncrement(float inc) {
		this.inc = inc;
	}
	
	public float getIncrement() {
		return this.inc;
	}
	
	public void setRotation(float rot) {
		this.rotation = rot;
	}
	
	public float getRotation() {
		return this.rotation;
	}
}
