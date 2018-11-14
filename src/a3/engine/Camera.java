package a3.engine;

import a3.math.Matrix;
import a3.math.Vector3D;
import a3.math.Point3D;

public class Camera {
	private Vector3D u, v, n;
	private Point3D camLoc, lookAt;
	private Matrix viewMatrix;
	
	public Camera(float x, float y, float z) {
		Vector3D yAxis = new Vector3D(0.0f, 1.0f, 0.0f);
		Vector3D temp = new Vector3D();
		
		this.camLoc = new Point3D(x, y, z);
		this.n = this.getForwardVector().normalize().inverseVector();
		temp = n.cross(yAxis);
		this.u = temp.normalize();
		temp = u.cross(n);
		this.v = temp.normalize();
		
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
		
	}
	public void moveBackward() {
		
	}
	public void moveLeft() {
	
	}
	public void moveRight() {
	
	}
	public void moveUp() {
	
	}
	public void moveDown() {
		
	}
	public void yawLeft() {
		
	}
	public void yawRight() {
		
	}
	public void pitchUp() {
		
	}
	public void pitchDown() {
		
	}
}
