package a3.lighting;

public class Ambient extends Light {
	public Ambient() {
		super("Ambient");
		this.setAmbient(new RGBa(0.5f, 0.5f, 0.5f, 1.0f));
		this.setDiffuse(new RGBa());
		this.setSpecular(new RGBa());
	}
	
}
