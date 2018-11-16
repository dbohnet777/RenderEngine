package a3.engine;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;
import static com.jogamp.opengl.GL4.*;


public class Shadows {
	private String type;
	private int[] shadow_tex = new int[1];
	
	public Shadows(String type) {
		this.type = type;
	}
	
	public void setupShadowBuffers() {
		GL4 gl = (GL4) GLContext.getCurrentGL();
		
		gl.glGenTextures(1, shadow_tex, 0);
		gl.glBindTexture(GL_TEXTURE_2D, shadow_tex[0]);
		
	}
}
