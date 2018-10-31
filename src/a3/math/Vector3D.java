package a3.math;

import java.lang.Math;

public class Vector3D {
	private float x;
	private float y;
	private float z;
	
	public Vector3D() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**  The methods below will mutate the instance as well as return it **/
	
	public Vector3D normalize() {
		float temp = this.mag(); //store initial magnitude of vector to divide x,y,z
		this.setX(getX() / temp);
		this.setY(getY() / temp);
		this.setZ(getZ() / temp);
		return this;
	}
	
	public Vector3D scale(float scalar) {
		this.setX(getX() * scalar);
		this.setY(getY() * scalar);
		this.setZ(getZ() * scalar);
		return this;
	}
	
	/**  The methods below will NOT mutate the instance and return new Vector3D **/
	public Vector3D add(Vector3D vec2) {
		Vector3D result = new Vector3D();
		result.setX(this.x + vec2.getX());
		result.setY(this.y + vec2.getY());
		result.setZ(this.z + vec2.getZ());
		return result;
	}
	
	public Vector3D sub(Vector3D vec2) {
		Vector3D result = new Vector3D();
		result.setX(this.x - vec2.getX());
		result.setY(this.y - vec2.getY());
		result.setZ(this.z - vec2.getZ());
		return result;
	}
	
	public float dot(Vector3D vec2) {
		float dot = ((this.getX() * vec2.getX()) + (this.getY() * vec2.getY()) + (this.getZ() * vec2.getZ()));
		return dot;
	}
	
	public Vector3D cross(Vector3D vec2) {
		float xX, xY, xZ;
		xX = ((this.getY() * vec2.getZ()) - (this.getZ() * vec2.getY()));
		xY = ((this.getZ() * vec2.getX()) - (this.getX() * vec2.getZ()));
		xZ = ((this.getX() * vec2.getY()) - (this.getY() * vec2.getX()));
		Vector3D cross = new Vector3D(xX, xY, xZ);
		return cross;
	}
	
	/**  The magnitude function takes the current vector and returns it's magnitude as a float value **/
	public float mag() {
		float mag = (float) Math.sqrt(sumOfSquares(x, y, z));
		return mag;
	}
	
	/** Getters and setters for x, y, and z values of Vector3D **/
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX() {
		return this.x;
	}	
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return this.y;	
	}	
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public float getZ() {
		return this.z;
	}
	
	public String toString() {
		return "Vector -> (" + this.x + ", "  + this.y + ", " + this.z + ") magnitude = " + mag();
	}
	
	public float angleBetween(Vector3D vec2) {
		float angle = 0.0f;
		angle = (float) Math.acos(((this.x * vec2.getX()) + (this.y * vec2.getY()) + (this.z * vec2.getZ())) / (this.mag() * vec2.mag()));
		return angle;
	}
	
	public float[] getDirection() {
		return new float[] {x, y, z};
	}
	
	public void setDirection(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**  Helper function for finding magnitude  **/
	private float sumOfSquares(float x, float y, float z) {
		float sumOfSquares = (x*x + y*y + z*z);		
		return sumOfSquares;
	}
}
