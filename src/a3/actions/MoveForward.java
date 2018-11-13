package a3.actions;

import a3.engine.Camera;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class MoveForward extends AbstractAction {
	private Camera camera;
	
	public MoveForward (Camera c) {
		super();
		this.camera = c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		camera.moveForward();
	}

}
