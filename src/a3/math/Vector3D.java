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
	
	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D(float[] values) {
		this.x = values[0];
		this.y = values[1];
		this.z = values[2];
	}

	/**  The methods below will mutate the instance as well as return it **/
	
	public Vector3D normalize() {
		this.setDirection(this.x / this.getMagnitude(), this.y / this.getMagnitude(),	this.z / this.getMagnitude());
		return this;
	}
	
	public Vector3D scale(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		return this;
	}
	
	/**  The methods below will NOT mutate the instance and return new Vector3D **/
	public Vector3D add(Vector3D vec2) {
		float x = this.x + vec2.getX();
		float y = this.y + vec2.getY();
		float z = this.z + vec2.getZ();
		return new Vector3D(x, y, z);
	}
	
	public Vector3D sub(Vector3D vec2) {
		float x = this.x - vec2.getX();
		float y = this.y - vec2.getY();
		float z = this.z - vec2.getZ();
		return new Vector3D(x, y, z);
	}
	
	public float dot(Vector3D vec2) {
		return (this.x * vec2.getX()) + (this.y * vec2.getY()) + (this.z * vec2.getZ());
	}
	
	public Vector3D cross(Vector3D vec2) {
		float x = this.y * vec2.getZ() - this.z * vec2.getY();
		float y = this.z * vec2.getX() - this.x * vec2.getZ();
		float z = this.x * vec2.getY() - this.y * vec2.getX();
		return new Vector3D(x, y, z);
	}
	
	public float getMagnitude() {
		return (float) Math.sqrt(this.sumOfSquares(this.x, this.y, this.z));
	}
	
	/** Other methods that do not mutate or return instances of the Vector class **/
	public float getAngleBetween(Vector3D vec2) {
		return (float) Math.acos((this.x * vec2.getX() + this.y * vec2.getY() + this.z * vec2.getZ()) / (this.getMagnitude() * vec2.getMagnitude()));
	}
	
	public float[] getDirection() {
		return new float[] {x, y, z};
	}
	
	public void setDirection(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	private float sumOfSquares(float x, float y, float z) {
		return x*x + y*y + z*z;
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
		return "Vector3D <" + this.x + ", "  + this.y + ", " + this.z + "> With magnitude = " + this.getMagnitude();
	}
}
