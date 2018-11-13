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

	
	
	
}
