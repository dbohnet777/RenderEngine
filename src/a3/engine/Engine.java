package a3.engine;
import static com.jogamp.opengl.GL.GL_NO_ERROR;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2ES2.GL_INFO_LOG_LENGTH;
import static com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER;
import static com.jogamp.opengl.GL2ES3.GL_COLOR;
import java.nio.FloatBuffer;
import javax.swing.JFrame;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import a3.math.Vector3D;
import graphicslib3D.GLSLUtils;

@SuppressWarnings("serial")
public class Engine extends JFrame implements GLEventListener{
	private GLCanvas myCanvas;
	private GLSLUtils util = new GLSLUtils();
	private int rendering_program;
	private int vao[] = new int[1];
	
	private ErrorChecking errorChecking;
	private Vector3D test1, test2, test3; //vectors for testing
	public Engine() {
		setTitle("Assignment #3");
		setSize(1000, 1000);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		getContentPane().add(myCanvas);
		setVisible(true);
		errorChecking = new ErrorChecking();
		errorChecking.checkOpenGLError();
	}

	public void start() {
		//create and start the FPS animator
		FPSAnimator animator = new FPSAnimator(myCanvas, 30);
		animator.start();
	}
	
	public void display(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		gl.glUseProgram(rendering_program);
		float bkg[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(bkg);
		gl.glClearBufferfv(GL_COLOR, 0, bkgBuffer);		
		gl.glDrawArrays(GL_TRIANGLES,0,3);
	}

	public void init(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		rendering_program = createShaderProgram();
		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		

		System.out.println("OpenGL Version: " + gl.glGetString(GL.GL_VERSION));
		System.out.println("JOGL Version: " + Package.getPackage("com.jogamp.opengl").getImplementationVersion());
		System.out.println("Java Version: " + System.getProperty("java.version"));
		
		//*************************************************VECTOR TESTING**************************************//
		
		test1 = new Vector3D(1.0f, 2.0f, 3.0f);
		test2 = new Vector3D(4.0f, 5.0f, 6.0f);
		test3 = new Vector3D();
		System.out.println(test1.toString());
		test3 = test1.add(test2);
		System.out.println(test3.toString());
		test3 = test1.sub(test2);
		System.out.println(test3.toString());
		test1.normalize();
		System.out.println(test1.toString());
		//*********************************************END VECTOR TESTING**************************************//
	}

	@SuppressWarnings("static-access")
	private int createShaderProgram(){
		GL4 gl = (GL4) GLContext.getCurrentGL();

		String vshaderSource[] = util.readShaderSource("src/a3/shaders/vert.shader");
		String fshaderSource[] = util.readShaderSource("src/a3/shaders/frag.shader");

		int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
		int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

		gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
		gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);

		gl.glCompileShader(vShader);
		gl.glCompileShader(fShader);

		int vfprogram = gl.glCreateProgram();
		gl.glAttachShader(vfprogram, vShader);
		gl.glAttachShader(vfprogram, fShader);
		gl.glLinkProgram(vfprogram);
		
		return vfprogram;
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}
}
