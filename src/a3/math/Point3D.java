package a3.math;

public class Point3D {
	private float x;
	private float y;
	private float z;
	private float w;
	
	public Point3D() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
		this.w = 1.0f;
	}
	
	public Point3D(float[] values) {
		/* TODO add error checking */
		this.x = values[0];
		this.y = values[1];
		this.z = values[2];
		this.w = 1.0f;
	}
	
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1.0f;
	}
	
	public Point3D(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Point3D(Vector3D vector) {
		this.x = vector.getX();
		this.y = vector.getY();
		this.z = vector.getZ();
		this.w = 1.0f;
	}
	
	/** The methods below will NOT mutate the current point and return a new Point3D **/
	public Point3D add(Point3D point2) {
		float x = this.x + point2.getX();
		float y = this.y + point2.getY();
		float z = this.z + point2.getZ();
		return new Point3D(x, y, z);
	}
	
	public Point3D sub(Point3D point2) {
		float x = this.x - point2.getX();
		float y = this.y - point2.getY();
		float z = this.z - point2.getZ();
		return new Point3D(x, y, z);
	}
	
	public Point3D mult(float value) {
		float x = this.x * value;
		float y = this.y * value;
		float z = this.z * value;
		return new Point3D(x, y, z);
	}
	
	public Point3D divide(float value) {
		float x = this.x / value;
		float y = this.y / value;
		float z = this.z / value;
		return new Point3D(x, y, z);
	}
	
	public Point3D midpoint(Point3D point2) {
		float x = (this.x + point2.getX()) / 2;
		float y = (this.y + point2.getY()) / 2;
		float z = (this.z + point2.getZ()) / 2;
		return new Point3D(x, y, z);
	}
	
	/** The methods below mutate the current Point3D **/
	public void scale(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
	}
	
	/** The methods below do not mutate or return a Point3D **/
	public boolean equals(Point3D point) {
		if(this.x == point.getX() && this.y == point.getY() && this.z == point.getZ() && this.w == point.getW()) {
			return true;
		}
		else return false;
	}
	
	public float distanceBetween(Point3D point) {
		float x = this.x - point.getX();
		float y = this.y - point.getY();
		float z = this.z - point.getZ();
		Vector3D temp = new Vector3D(x, y, z);
		float distance = temp.getMagnitude();
		return distance;
	}
	
	public float getX() {
		return this.x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return this.z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return this.w;
	}

	public void setW(float w) {
		this.w = w;
	}
	
	public String toString() {
		return "(X, Y, Z, W) = " + " (" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
	}
}
