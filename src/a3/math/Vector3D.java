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
	
	/**  TODO The methods below will mutate the instance as well as return it **/
	
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
	
	/**  TODO The methods below will NOT mutate the instance and return new Vector3D **/
	
	public Vector3D mult(Vector3D vec2) {
		return null;
	}
	
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
	
	public Vector3D div(Vector3D vec2) {
		return null;
	}
	
	public Vector3D dot(Vector3D vec2) {
		return null;
	}
	
	public Vector3D cross(Vector3D vec2) {
		return null;
	}
	
	/**  The magnitude function takes the current vector and returns it's magnitude as a float value **/
	public float mag() {
		float temp = 0.0f;
		float mag = temp;
		temp = sumOfSquares(x, y, z);
		mag = (float) Math.sqrt(temp);
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
	
	/** TODO other methods that do not mutate or return instances of the Vector class **/
	public float angleBetween(Vector3D vec2) {
		// https://www.wikihow.com/Find-the-Angle-Between-Two-Vectors
		return 1;
	}
	
	/**  The Methods below are done  **/
	
	public float[] getDirection() {
		return new float[] {x, y, z};
	}
	
	public void setDirection(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**  Helper functions for math functions  **/
	private float sumOfSquares(float x, float y, float z) {
		float sumOfSquares = (x*x + y*y + z*z);		
		return sumOfSquares;
	}
}
