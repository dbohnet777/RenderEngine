package a3.math;

import a3.engine.Camera;
import com.jogamp.opengl.awt.GLCanvas;

public class Transform {
    private Vector3D scale, rotation, translation;
    private float width, height;
    private static float fov = 60.0f, near = 0.1f, far = 1000.0f;
    private static Camera cam;
    private Matrix transform;

    public Transform(GLCanvas c, Vector3D scale, Vector3D rotation, Vector3D translation){
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
        this.width = c.getWidth();
        this.height = c.getHeight();
        cam.g
        Matrix m = createModelMatrix();
        Matrix v = getViewMatrix();
        Matrix p = createProjectionMatrix();
        transform = new Matrix().mult(p).mult(v).mult(m);
    }

    private Matrix createModelMatrix(){
        Matrix mMatrix = new Matrix();
        mMatrix.mult(scale);
        mMatrix.mult(rotation);
        mMatrix.mult(translation);
        return mMatrix;
    }
    private Matrix getViewMatrix(){
        return cam.getViewMatrix();
    }

    private Matrix createProjectionMatrix(){
        Matrix projMatrix = new Matrix().createProjectionMatrix(fov, width, height, near, far);
        return projMatrix;
    }
}
