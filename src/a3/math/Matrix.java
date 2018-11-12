package a3.math;

import java.util.stream.IntStream;

public class Matrix {
	private static final String major = "ROW";
	private float[][] values;
	
	public Matrix() {
		this.values = new float[][] {
                {1.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 1.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 1.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 1.0f}
        };
	}

	public Matrix(float[][] matrix) {
		this.values = new float[][] {
			{matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][3]},
			{matrix[1][0], matrix[1][1], matrix[1][2], matrix[1][3]},
			{matrix[2][0], matrix[2][1], matrix[2][2], matrix[2][3]},
			{matrix[3][0], matrix[3][1], matrix[3][2], matrix[3][3]}
		};
	}

    public Matrix(float[] matrix) {
        this.values = new float[][] {
                {matrix[0],  matrix[1],  matrix[2],  matrix[3]},
                {matrix[4],  matrix[5],  matrix[6],  matrix[7]},
                {matrix[8],  matrix[9],  matrix[10], matrix[11]},
                {matrix[12], matrix[13], matrix[14], matrix[15]}
        };
    }

    public Matrix(Vector3D xVec, Vector3D yVec, Vector3D zVec) {
        this.values = new float[][] {
                {xVec.getX(), xVec.getY(), xVec.getZ(), 0},
                {yVec.getX(), yVec.getY(), yVec.getZ(), 0},
                {zVec.getX(), zVec.getY(), zVec.getZ(), 0},
                {    0,           0,           0,       1}
            };
	};

    public Matrix createRotationMatrix(float degrees, Vector3D axis) {
    	float cos = (float) Math.cos(degrees);
    	float sin = (float) Math.sin(degrees);
    	float mCT = 1.0f - cos;	//removes need 4 additional parenthesis
    	float uX = axis.getX();
    	float uY = axis.getY();
    	float uZ = axis.getZ();
    	
    	float[][] values = new float[][] {
            {(cos + uX*uX*mCT),    (uX*uY*mCT - uZ*sin), (uX*uZ*mCT + uY*sin), 0.0f},
            {(uY*uX*mCT + uZ*sin), (cos + uY*uY*mCT),    (uY*uZ*mCT - uX*sin), 0.0f},
            {(uZ*uX*mCT - uY*sin), (uZ*uY*mCT + uX*sin), (cos + uZ*uZ*mCT),    0.0f},
            {		0.0f, 				   0.0f,				 0.0f,		   1.0f}};
	    Matrix rotationMatrix = new Matrix(values);
	    return rotationMatrix;
	}

	public Matrix createRotationMatrix(float amtX, float amtY, float amtZ) {
		float cosX = (float) Math.cos(amtX);
		float sinX = (float)Math.sin(amtX);
		float cosY = (float) Math.cos(amtY);
		float sinY = (float)Math.sin(amtY);
		float cosZ = (float) Math.cos(amtZ);
		float sinZ = (float)Math.sin(amtZ);
		
        float[][] valuesX = new float[][] {
        	{1.0f, 0,0f, 0.0f, 0.0f},
        	{0.0f, cosX, -sinX, 0.0f},
        	{0.0f, sinX, cosX, 0.0f},
        	{0.0f, 0.0f, 0.0f, 1.0f}};
		float[][] valuesY = new float[][] {
			{cosY, 0.0f, sinY, 0.0f},
			{0.0f, 1.0f, 0.0f, 0.0f},
			{-sinY, 0.0f, cosY, 0.0f},
			{0.0f, 0.0f, 0.0f, 1.0f}};
		float[][] valuesZ = new float[][] {
			{cosZ, -sinZ, 0.0f, 0.0f},
			{sinZ, cosZ, 0.0f, 0.0f},
			{0.0f, 0.0f, 1.0f, 0.0f},
			{0.0f, 0.0f, 0.0f, 1.0f}};
			
		Matrix rotX = new Matrix(valuesX);
		Matrix rotY = new Matrix(valuesY);
		Matrix rotZ = new Matrix(valuesZ);
		Matrix rotationMatrix = new Matrix().mult(rotX).mult(rotY).mult(rotZ);
		return rotationMatrix;
	}

    public Matrix createScalingMatrix(float amtX, float amtY, float amtZ) {
        this.values = new float[][] {
                {amtX,  0,   0,  0},
                {  0, amtY,  0,  0},
                {  0,   0, amtZ, 0},
                {  0,   0,   0,  1}
        };
        return this;
	}

    public Matrix createTranslationMatrix(float amtX, float amtY, float amtZ) {
        this.values = new float[][] {
                {1, 0, 0, amtX},
                {0, 1, 0, amtY},
                {0, 0, 1, amtZ},
                {0, 0, 0,   1 }
        };
        return this;
    }

    public Matrix createProjectionMatrix(float fov, float width, float height, float near, float far) {
        float aspect = width/height;
        float q = 1.0f / ((float) Math.tan(Math.toRadians(0.5f * fov)));
        float A = q / aspect;
        float B = (near + far) / (near - far);
        float C = (2.0f * near * far) / (near - far);

        this.values = new float[][] {
                {  A,  0.0f,  0.0f, 0.0f},
                {0.0f,   q,   0.0f, 0.0f},
                {0.0f, 0.0f,    B,    C },
                {0.0f, 0.0f, -1.0f, 0.0f}
        };
        return this;
    }

    public Matrix createCameraMatrix(Vector3D forward, Vector3D up) {
    	Vector3D n = forward.normalize();
    	Vector3D u = up.normalize();
    	u = u.cross(n);
    	Vector3D v = n.cross(u);
    	
    	//set the vectors to the matrix now
        this.values = new float[][] {
                {u.getX(), v.getX(), n.getX(), 0},
                {u.getY(), v.getY(), n.getY(), 0},
                {u.getZ(), v.getZ(), n.getZ(), 0},
                {    0,        0,        0,    1}};
        return this;
    }

	public Matrix add(Matrix mat2) {
		float[] mat1Floats = this.getValues();
        float[] mat2Floats = mat2.getValues();
        float[] temp = new float[mat1Floats.length];
		
		if (mat1Floats.length == mat2Floats.length) {
			for (int i=0; i<mat1Floats.length; i++) {
				temp[i] = mat1Floats[i] + mat2Floats[i];
			}
		}
		else {
            try {
                throw new Exception("Length mismatch cannot add matrices of different dimensions");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
		return new Matrix(temp);
	}

    public Matrix sub(Matrix mat2) {
        float[] mat1Floats = this.getValues();
        float[] mat2Floats = mat2.getValues();
        float[] temp = new float[mat1Floats.length];

        if (mat1Floats.length == mat2Floats.length) {
            for (int i=0; i<mat1Floats.length; i++) {
                temp[i] = mat1Floats[i] - mat2Floats[i];
            }
        }
        else {
            try {
                throw new Exception("Length mismatch cannot subtract matrices of different dimensions");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return new Matrix(temp);
    }

	public float elementAt(int row, int col) {
	    return this.values[row][col];
    }

    public Vector3D getColVector(int col) {
    	return new Vector3D(this.values[0][col], this.values[1][col], this.values[2][col]);
    }

    public Vector3D getRowVector(int row) {
        return new Vector3D(this.values[row][0], this.values[row][1], this.values[row][2]);
    }
    
    public float[] getColValues(int col) {
    	return new float[] {this.values[0][col], this.values[1][col], this.values[2][col], this.values[3][col]};
    }
    
    public float[] getRowValues(int row) {
    	return new float[] {this.values[row][0], this.values[row][1], this.values[row][2],	 this.values[row][3]};
    }
    
    public Matrix transpose() {
    	float[] temp = this.getValues();
    	for(int i = 0; i < 4; i++) {
    		for(int j = 0; j < 4; j++) {
    			this.values[j][i] = temp[(i * 4 + j)];
    		}
    	}
        return this;
    }

    public Matrix setCol(int col, Vector3D vector) {
        this.values[0][col] = vector.getX();
        this.values[1][col] = vector.getY();
        this.values[2][col] = vector.getZ();
	    return null;
    }

    public Matrix setRow(int row, Vector3D vector) {
        this.values[row][0] = vector.getX();
        this.values[row][1] = vector.getY();
        this.values[row][2] = vector.getZ();
        return null;
    }

    public Matrix setElementAt(int row, int col, float value) {
        this.values[row][col] = value;
        return null;
    }
	
	public Matrix mult(Matrix mat2) {
		float[][] temp = new float[4][4];
        IntStream.range(0, 4).forEachOrdered(row -> {
            IntStream.range(0, 4).forEachOrdered(col -> {
                float[] curRow = this.getRowValues(row);
                float[] mat2Col = mat2.getColValues(col);
                for(int i=0; i<curRow.length; i++) {
                    temp[row][col] += curRow[i] * mat2Col[i];
                }
            });
        });
		return new Matrix(temp);
	}
	
	public Vector3D mult(Vector3D vec) {
        float[] temp = new float[4];
        float[] vecValues = new float[] {vec.getX(), vec.getY(), vec.getZ(), 1};
        IntStream.range(0, 4).forEachOrdered(row -> {
            float[] curRow = this.getRowValues(row);
            for(int i=0; i<curRow.length; i++) {
                temp[i] += curRow[i] * vecValues[i];
            }
        });
		return new Vector3D(temp);
	}

	public float[] getValues() {
        return new float[] {
                this.values[0][0], this.values[0][1], this.values[0][2], this.values[0][3],
        		this.values[1][0], this.values[1][1], this.values[1][2], this.values[1][3],
        		this.values[2][0], this.values[2][1], this.values[2][2], this.values[2][3],
        		this.values[3][0], this.values[3][1], this.values[3][2], this.values[3][3]
        };
    }
	
	public static String getMajor() {
		return Matrix.major;
	}
	
	public String toString() {
		return "This is a 4x4 matrix with the values: \n" +
				this.values[0][0] + " " + this.values[0][1] + " " + this.values[0][2] + " " + this.values[0][3] + "\n" +
				this.values[1][0] + " " + this.values[1][1] + " " + this.values[1][2] + " " + this.values[1][3] + "\n" +
				this.values[2][0] + " " + this.values[2][1] + " " + this.values[2][2] + " " + this.values[2][3] + "\n" +
				this.values[3][0] + " " + this.values[3][1] + " " + this.values[3][2] + " " + this.values[3][3] + "\n";
	}
}
