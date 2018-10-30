package a3.lighting;

import graphicslib3D.Vector3D;

public class Spot extends Light {
	private float[] direction;
	private Vector3D position;
	
	public Spot() {
		super("Spot");
	}
	
	public float[] getDirection() {
		return direction;
	}
	public void setDirection() {
		
	}
	public void setPosition(Vector3D position) {
		
	}
	public Vector3D getPosition() {
		return this.position;
	}
	
}
