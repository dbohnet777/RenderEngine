package a3;
import a3.engine.Engine;

public class Starter{
	private Engine engine;

	public Starter(){
		engine = new Engine();
		engine.start();		
	}
	
	public static void main(String[] args) {
		new Starter();
	}
}