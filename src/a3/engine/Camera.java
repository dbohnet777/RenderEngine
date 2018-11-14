package a3.engine;

import a3.math.Matrix;
import a3.math.Vector3D;
import a3.math.Point3D;

public class Camera {
	private Vector3D u, v, n;
	private Point3D camLoc, lookAt;
	private Matrix viewMatrix;
	
	public Camera(float x, float y, float z) {
		this.camLoc = new Point3D(x, y, z);
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
	
	public Point3D getLocation() {
		return camLoc;
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
