package a3.lighting;

public abstract class Light {
	private RGBa ambient;
	private RGBa diffuse;
	private RGBa specular;
	private String type;
	
	public Light(String type) {
		this.type = type;
	}
	public RGBa getAmbient() {
		return this.ambient;
	}
	public void setAmbient(RGBa ambient) {
		this.ambient = ambient;
	}
	public RGBa getDiffuse() {
		return this.diffuse;
	}
	public void setDiffuse(RGBa diffuse) {
		this.diffuse = diffuse;
	}
	public RGBa getSpecular() {
		return this.specular;
	}
	public void setSpecular(RGBa specular) {
		this.specular = specular;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
	public String toString() {
		return "This is a " + type + " light.";
	}
	
	
}
