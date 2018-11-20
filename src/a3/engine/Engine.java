package a3.engine;

import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER;
import static com.jogamp.opengl.GL2ES3.GL_COLOR;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.nio.FloatBuffer;
import javax.swing.JFrame;
import graphicslib3D.GLSLUtils;
import graphicslib3D.MatrixStack;
import graphicslib3D.Matrix3D;

@SuppressWarnings("serial")
public class Engine extends JFrame implements GLEventListener{
	//OpenGL initializers
	private GLCanvas myCanvas;
	private GLSLUtils util = new GLSLUtils();
	private int rendering_program;
	private int vao[] = new int[1];
	private int vbo[] = new int[15];
	private float camX, camY, camZ, cLocX, cLocY, cLocZ;
	private Matrix3D pMat;
	private MatrixStack mvStack = new MatrixStack(15);
	//Camera initializers
	//private Camera myCam = new Camera();
	
	public Engine() {
		setTitle("Assignment #3");
		setSize(1000, 1000);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		getContentPane().add(myCanvas);
		setVisible(true);
		this.start();
	}

	public void start() {
		//create and start the FPS animator
		FPSAnimator animator = new FPSAnimator(myCanvas, 60);
		animator.start();
	}
	
	public void display(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		gl.glUseProgram(rendering_program);
        gl.glClear(GL_DEPTH_BUFFER_BIT);
        float bkg[] = {0.0f, 0.0f, 0.0f, 1.0f};
        FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(bkg);
        gl.glClearBufferfv(GL_COLOR, 0, bkgBuffer);

		Matrix3D vMat = new Matrix3D();
		vMat.translate(-camX, -camY, -camZ);
		Matrix3D mMat = new Matrix3D();
		double t = (double)(System.currentTimeMillis()) / 10000.0;
		mMat.translate(Math.sin(2*t)*2.0, Math.sin(3*t)*2.0, Math.sin(4*t)*2.0);
		mMat.rotate(1000*t, 1000*t, 1000*t);
		mMat.translate(cLocX, cLocY, cLocZ);
		Matrix3D mvMat = new Matrix3D();
		mvMat.concatenate(vMat);
		mvMat.concatenate(mMat);
		int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
		int proj_loc = gl.glGetUniformLocation(rendering_program, "proj_matrix");
		gl.glUniformMatrix4fv(proj_loc, 1, false, pMat.getFloatValues(), 0);
		gl.glUniformMatrix4fv(mv_loc, 1, false, mvMat.getFloatValues(), 0);
		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);
		gl.glDrawArraysInstanced(GL_TRIANGLES, 0, 36, 24);


	}

	public void init(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		rendering_program = createShaderProgram();
		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		
		//****** print the current running versions ********//
		System.out.println("OpenGL Version: " + gl.glGetString(GL.GL_VERSION));
		System.out.println("JOGL Version: " + Package.getPackage("com.jogamp.opengl").getImplementationVersion());
		System.out.println("Java Version: " + System.getProperty("java.version"));


		setupVerticies();
		camX = 0.0f; camY = 0.0f; camZ = 8.0f;
		cLocX = 0.0f; cLocY = -2.0f; cLocZ = 0.0f;

			float aspect = (float) myCanvas.getWidth()/(float)myCanvas.getHeight();
		pMat = perspective(60.0f, aspect, 0.1f, 1000.f);
	}

	@SuppressWarnings("static-access")
	private int createShaderProgram(){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		ErrorChecking ec = new ErrorChecking(gl);
		int[] vertCompiled = new int[1];
		int[] fragCompiled = new int[1];
		int[] linked = new int[1];
		
		String vshaderSource[] = util.readShaderSource("src/a3/shaders/vert.shader");
		String fshaderSource[] = util.readShaderSource("src/a3/shaders/frag.shader");
		//create vertex shader
		int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
		gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
		gl.glCompileShader(vShader);
		//check errors for vertex shader
		ec.checkOpenGLError();
		gl.glGetShaderiv(vShader, GL_COMPILE_STATUS, vertCompiled, 0);
		if (vertCompiled[0] == 1)
		{	System.out.println("vertex compilation success");
		} else
		{	System.out.println("vertex compilation failed");
			ec.printShaderLog(vShader);
		}
		//create fragment shader
		int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);
		gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);
		gl.glCompileShader(fShader);
		//check errors for fragment shader
		ec.checkOpenGLError();  // can use returned boolean if desired
		gl.glGetShaderiv(fShader, GL_COMPILE_STATUS, fragCompiled, 0);
		if (fragCompiled[0] == 1)
		{	System.out.println("fragment compilation success");
		} else
		{	System.out.println("fragment compilation failed");
			ec.printShaderLog(fShader);
		}
		//create openGL program and attach and link vertex and fragment shaders
		int vfprogram = gl.glCreateProgram();
		gl.glAttachShader(vfprogram, vShader);
		gl.glAttachShader(vfprogram, fShader);
		gl.glLinkProgram(vfprogram);
		//check for errors
		ec.checkOpenGLError();
		gl.glGetProgramiv(vfprogram, GL_LINK_STATUS, linked, 0);
		if (linked[0] == 1)
		{	System.out.println("linking succeeded");
		} else
		{	System.out.println("linking failed");
			ec.printProgramLog(vfprogram);
		}
		//remove shaders
		gl.glDeleteShader(vShader);
		gl.glDeleteShader(fShader);
		//return linked shader program
		return vfprogram;
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}

	public void setupVerticies(){
        GL4 gl = (GL4) GLContext.getCurrentGL();

        // 36 vertices of the 12 triangles making up a 2 x 2 x 2 cube centered at the origin
        float[ ] vertex_positions =
                { -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f,
                        1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f,
                        1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
                        -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f,
                        -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f,
                        -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f
                };
        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);
        gl.glGenBuffers(vbo.length, vbo, 0);
        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        FloatBuffer vertBuf = Buffers.newDirectFloatBuffer(vertex_positions);
        gl.glBufferData(GL_ARRAY_BUFFER, vertBuf.limit()*4, vertBuf, GL_STATIC_DRAW);
    }

    private Matrix3D perspective(float fovy, float aspect, float n, float f)
    { float q = 1.0f / ((float) Math.tan(Math.toRadians(0.5f * fovy)));
        float A = q / aspect;
        float B = (n + f) / (n - f);
        float C = (2.0f * n * f) / (n - f);
        Matrix3D r = new Matrix3D();
        r.setElementAt(0,0,A);
        r.setElementAt(1,1,q);
        r.setElementAt(2,2,B);
        r.setElementAt(3,2,-1.0f);
        r.setElementAt(2,3,C);
        r.setElementAt(3,3,0.0f);
        return r;
    }
}
