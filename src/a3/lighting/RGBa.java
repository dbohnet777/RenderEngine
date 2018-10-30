package a3.lighting;

public class RGBa {
	private float red, green, blue, alpha;
	public RGBa() {
		this.red = 0;
		this.green = 0;
		this.blue = 0;
		this.alpha = 0;
	}
	public RGBa(float red, float green, float blue, float alpha) {
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.alpha = alpha;
	}
	public float getRed() {
		return this.red;
	}
	public void setRed(float red) {
		this.red = red;
	}
	public float getGreen() {
		return this.green;
	}
	public void setGreen(float green) {
		this.green = green;
	}
	public float getBlue() {
		return this.blue;
	}
	public void setBlue(float blue) {
		this.blue = blue;
	}
	public float getAlpha() {
		return this.alpha;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	public String toString() {
		return "RGBa = (" + this.red + ", " + this.green + ", " + this.blue + ", " + this.alpha + ")";
	}
}
