package a3.math;

public class Vector3D {
	private float x;
	private float y;
	private float z;
	
	public Vector3D() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	/**  TODO The methods below will mutate the instance as well as return it **/
	
	public Vector3D normalize() {
		return this;
	}
	
	public Vector3D scale(float scaler) {
		return this;
	}
	
	/**  TODO The methods below will NOT mutate the instance and return new Vector3D **/
	
	public Vector3D mult(Vector3D vec2) {
		return null;
	}
	
	public Vector3D add(Vector3D vec2) {
		return null;
	}
	
	public Vector3D sub(Vector3D vec2) {
		return null;
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
	
	public String toString() {
		return "";
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
}
