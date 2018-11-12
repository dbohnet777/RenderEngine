package a3.math;


public class Test {
    public static void main(String[] args) {
        float[][] values = new float[][] {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}};
        Vector3D vec1 = new Vector3D(1.0f, 1.0f, 1.0f);
        Vector3D vec2 = new Vector3D(1.0f, 2.0f, 2.0f);
        Vector3D vec3 = new Vector3D(2.0f, 2.0f, 2.0f);
        Matrix mat1 = new Matrix(values);
        Matrix mat2 = new Matrix(vec1, vec2, vec3);
        
        System.out.println(mat1);
        System.out.println(mat2);
        Matrix mat3 = mat1.mult(mat2);
        System.out.println(mat3);
        Matrix mat4 = mat1.createRotationMatrix(45, vec2);
        System.out.println(mat4);
        
        Vector3D vec4 = new Vector3D(324.482f, 4882.32f, 7899.3f);
        vec4 = vec4.normalize();
        System.out.println(vec4);
        float degrees = (float) Math.toRadians(34.75);
        Matrix mat5 = mat1.createRotationMatrix(degrees, vec4);
        System.out.println(mat5);
       
    }
}