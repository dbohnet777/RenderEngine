package a3.engine;

import a3.math.Matrix;
import a3.math.Vector3D;
import a3.math.Point3D;

public class Camera {
	private Vector3D u, v, n;
	private Point3D camLoc, lookAt;
	private Matrix viewMatrix;
	private float inc;
	private float rotation;
	
	//Instantiate camera directly behind origin directed at origin
	public Camera() {
		Vector3D worldUp = new Vector3D(0.0f, 1.0f, 0.0f);
		Vector3D temp = new Vector3D();
		inc = 0.125f; rotation = 0.125f;
		this.camLoc = new Point3D(0.0f, -1.0f, 0.0f);
		this.lookAt = new Point3D();
		this.n = this.getForwardVector().normalize().inverseVector();
		temp = n.cross(worldUp);
		this.u = temp.normalize();
		temp = u.cross(n);
		this.v = temp.normalize();
		
		this.viewMatrix = viewMatrix.createCameraMatrix(n, u, camLoc);
	}
	
	//Instantiate camera at given location directed at origin
	public Camera(float x, float y, float z) {
		Vector3D worldUp = new Vector3D(0.0f, 1.0f, 0.0f);
		Vector3D temp = new Vector3D();
		inc = 0.125f; rotation = 0.125f;
		this.camLoc = new Point3D(x, y, z);
		this.lookAt = new Point3D();
		this.n = this.getForwardVector().normalize().inverseVector();
		temp = n.cross(worldUp);
		this.u = temp.normalize();
		temp = u.cross(n);
		this.v = temp.normalize();
		
		this.viewMatrix = viewMatrix.createCameraMatrix(n, u, camLoc);
	}
	
	public Camera(Point3D cameraPoint) {
		Vector3D worldUp = new Vector3D(0.0f, 1.0f, 0.0f);
		Vector3D temp = new Vector3D();
		inc = 0.125f; rotation = 0.125f;
		this.camLoc = cameraPoint;
		this.lookAt = new Point3D();
		this.n = this.getForwardVector().normalize().inverseVector();
		temp = n.cross(worldUp);
		this.u = temp.normalize();
		temp = u.cross(n);
		this.v = temp.normalize();
		
		this.viewMatrix = viewMatrix.createCameraMatrix(n, u, camLoc);
	}
	
	//Instantiate camera at given location directed at given location
	public Camera(float xC, float yC, float zC, float xL, float yL, float zL) {
		Vector3D worldUp = new Vector3D(0.0f, 1.0f, 0.0f);
		Vector3D temp = new Vector3D();
		inc = 0.125f; rotation = 0.125f;
		this.camLoc = new Point3D(xC, yC, zC);
		this.lookAt = new Point3D(xL, yL, zL);
	
		this.n = this.getForwardVector().normalize().inverseVector();
		temp = n.cross(worldUp);
		this.u = temp.normalize();
		temp = u.cross(n);
		this.v = temp.normalize();
		
		this.viewMatrix = viewMatrix.createCameraMatrix(n, u, camLoc);
	}
	
	public Camera(Point3D cameraPoint, Point3D lookAtPoint) {
		Vector3D worldUp = new Vector3D(0.0f, 1.0f, 0.0f);
		Vector3D temp = new Vector3D();
		inc = 0.125f; rotation = 0.125f;
		this.camLoc = cameraPoint;
		this.lookAt = lookAtPoint;
	
		this.n = this.getForwardVector().normalize().inverseVector();
		temp = n.cross(worldUp);
		this.u = temp.normalize();
		temp = u.cross(n);
		this.v = temp.normalize();
		
		this.viewMatrix = viewMatrix.createCameraMatrix(n, u, camLoc);
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
		Matrix forwardMatrix = viewMatrix.createTranslationMatrix(0.0f, 0.0f, inc);
		viewMatrix = viewMatrix.mult(forwardMatrix);
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
		Matrix yawRightMatrix = viewMatrix.createRotationMatrix(-rotation, v);
		viewMatrix = viewMatrix.mult(yawRightMatrix);
	}
	
	public void pitchUp() {
		Matrix pitchUpMatrix = viewMatrix.createRotationMatrix(rotation, u);
		viewMatrix = viewMatrix.mult(pitchUpMatrix);
	}
	
	public void pitchDown() {
		Matrix pitchDownMatrix = viewMatrix.createRotationMatrix(-rotation, u);
		viewMatrix = viewMatrix.mult(pitchDownMatrix);
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
