package a3.actions;

import a3.engine.Camera;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class YawRight extends AbstractAction {
	private Camera camera;
	
	public YawRight (Camera c) {
		super();
		this.camera = c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		camera.moveForward();
	}

}

