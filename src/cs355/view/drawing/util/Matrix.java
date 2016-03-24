package cs355.view.drawing.util;

import cs355.model.scene.Point3D;

import java.util.Arrays;
import java.util.Random;

/**
 * A 4x4 matrix used for 3D Rendering.
 */
public class Matrix
{
    private double[][] matrix;
    private static final int MATRIX_DIMENSION = 4;

    private Matrix()
    {
        matrix = new double[MATRIX_DIMENSION][MATRIX_DIMENSION];
        setM00(0);
        setM01(0);
        setM02(0);
        setM03(0);
        setM10(0);
        setM11(0);
        setM12(0);
        setM13(0);
        setM20(0);
        setM21(0);
        setM22(0);
        setM23(0);
        setM30(0);
        setM31(0);
        setM32(0);
        setM33(0);
    }

    public Matrix(double[][] matrix)
    {
        this.matrix = matrix;
    }

    public static Matrix random() {
        double[][] C = new double[Matrix.MATRIX_DIMENSION][Matrix.MATRIX_DIMENSION];
        Random rand = new Random();
        for (int i = 0; i < MATRIX_DIMENSION; i++)
            for (int j = 0; j < MATRIX_DIMENSION; j++)
                C[i][j] = -300 + (300 - (-300)) * rand.nextDouble();
        return new Matrix(C);
    }

    public static Matrix identity()
    {
        Matrix identityMatrix = new Matrix();
        identityMatrix.setM00(1);
        identityMatrix.setM11(1);
        identityMatrix.setM22(1);
        identityMatrix.setM33(1);
        return identityMatrix;
    }

    public static Matrix rotation(double rotationAngle)
    {
        Matrix rotationMatrix = identity();
        rotationMatrix.setM00(Math.cos(rotationAngle));
        rotationMatrix.setM02(Math.sin(rotationAngle));
        rotationMatrix.setM20(-Math.sin(rotationAngle));
        rotationMatrix.setM22(Math.cos(rotationAngle));
        return rotationMatrix;
    }

    public static Matrix translation(double x, double y, double z)
    {
        Matrix translationMatrix = identity();
        translationMatrix.setM03(x);
        translationMatrix.setM13(y);
        translationMatrix.setM23(z);
        return translationMatrix;
    }

    public static Matrix translation(Point3D point)
    {
        return translation(point.x, point.y, point.z);
    }

    public static Matrix clipping(double zoomX, double zoomY, double zNear, double zFar)
    {
        Matrix clippingMatrix = identity();
        clippingMatrix.setM00(zoomX);
        clippingMatrix.setM11(zoomY);
        clippingMatrix.setM22((zFar + zNear)/(zFar - zNear));
        clippingMatrix.setM23((-2*zNear*zFar)/(zFar - zNear));
        clippingMatrix.setM32(1);
        clippingMatrix.setM33(0);
        return clippingMatrix;
    }

    public Matrix(double m00, double m01, double m02, double m03,
                  double m10, double m11, double m12, double m13,
                  double m20, double m21, double m22, double m23)
    {
        this();
        setM00(m00);
        setM01(m01);
        setM02(m02);
        setM03(m03);
        setM10(m10);
        setM11(m11);
        setM12(m12);
        setM13(m13);
        setM20(m20);
        setM21(m21);
        setM22(m22);
        setM23(m23);
    }

    public Matrix(double m00, double m01, double m02, double m03,
                  double m10, double m11, double m12, double m13,
                  double m20, double m21, double m22, double m23,
                  double m30, double m31, double m32, double m33)
    {
        this(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23);
        setM30(m30);
        setM31(m31);
        setM32(m32);
        setM33(m33);
    }

    /**
     * Multiplies this matrix with another matrix.
     *
     * @param other the other matrix.
     * @return the resulting matrix from the multiplication.
     */
    public Matrix multiply(Matrix other)
    {
        return Matrix.multiply(this, other);
    }

    /**
     * Multiplies the given matrices together.
     *
     * @param A the first matrix.
     * @param B the second matrix.
     * @return the resulting matrix from the multiplication.
     */
    public static Matrix multiply(Matrix A, Matrix B)
    {
        Matrix C = new Matrix();
        for (int i = 0; i < MATRIX_DIMENSION; i++)
        {
            for (int j = 0; j < MATRIX_DIMENSION; j++)
            {
                for (int k = 0; k < MATRIX_DIMENSION; k++)
                {
                    C.matrix[i][j] += A.matrix[i][k] * B.matrix[k][j];
                }
            }
        }
        return C;
    }

    public static Vector3D multiply(Matrix A, Vector3D B)
    {
        double[] vector = B.getAsArray();
        double[] result = new double[4];
        for (int i = 0; i < MATRIX_DIMENSION; i++)
        {
            for (int j = 0; j < MATRIX_DIMENSION; j++)
            {
                result[i] += A.matrix[i][j] * vector[j];
            }
        }
        return new Vector3D(result);
    }

    public double getM00()
    {
        return matrix[0][0];
    }

    public void setM00(double m00)
    {
        this.matrix[0][0] = m00;
    }

    public double getM01()
    {
        return matrix[0][1];
    }

    public void setM01(double m01)
    {
        this.matrix[0][1] = m01;
    }

    public double getM02()
    {
        return matrix[0][2];
    }

    public void setM02(double m02)
    {
        this.matrix[0][2] = m02;
    }

    public double getM03()
    {
        return matrix[0][3];
    }

    public void setM03(double m03)
    {
        this.matrix[0][3] = m03;
    }

    public double getM10()
    {
        return matrix[1][0];
    }

    public void setM10(double m10)
    {
        this.matrix[1][0] = m10;
    }

    public double getM11()
    {
        return matrix[1][1];
    }

    public void setM11(double m11)
    {
        this.matrix[1][1] = m11;
    }

    public double getM12()
    {
        return matrix[1][2];
    }

    public void setM12(double m12)
    {
        this.matrix[1][2] = m12;
    }

    public double getM13()
    {
        return matrix[1][3];
    }

    public void setM13(double m13)
    {
        this.matrix[1][3] = m13;
    }

    public double getM20()
    {
        return matrix[2][0];
    }

    public void setM20(double m20)
    {
        this.matrix[2][0] = m20;
    }

    public double getM21()
    {
        return matrix[2][1];
    }

    public void setM21(double m21)
    {
        this.matrix[2][1] = m21;
    }

    public double getM22()
    {
        return matrix[2][2];
    }

    public void setM22(double m22)
    {
        this.matrix[2][2] = m22;
    }

    public double getM23()
    {
        return matrix[2][3];
    }

    public void setM23(double m23)
    {
        this.matrix[2][3] = m23;
    }

    public double getM30()
    {
        return matrix[3][0];
    }

    public void setM30(double m30)
    {
        this.matrix[3][0] = m30;
    }

    public double getM31()
    {
        return matrix[3][1];
    }

    public void setM31(double m31)
    {
        this.matrix[3][1] = m31;
    }

    public double getM32()
    {
        return matrix[3][2];
    }

    public void setM32(double m32)
    {
        this.matrix[3][2] = m32;
    }

    public double getM33()
    {
        return matrix[3][3];
    }

    public void setM33(double m33)
    {
        this.matrix[3][3] = m33;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Matrix other = (Matrix) o;
        return areMatricesSimilarEnough(this.matrix, other.matrix);
    }

    private boolean areMatricesSimilarEnough(double[][] first, double[][] second)
    {
        if (first == null)
        {
            return second == null;
        }
        if (second == null)
        {
            return false;
        }

        double precision = 0.00000001;
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[i].length; j++)
            {
                if (first[i][j] != second[i][j])
                {
                    if (!areValuesSimilarEnough(first[i][j], second[i][j], precision))
                        return false;
                }
            }
        }
        return true;
    }

    private boolean areValuesSimilarEnough(double first, double second, double precision)
    {
        return Math.abs(first / second - 1) < precision;
    }

    @Override
    public int hashCode()
    {
        return Arrays.deepHashCode(matrix);
    }

    @Override
    public String toString()
    {
        return String.format(
                "|%s %s %s %s|\n" +
                "|%s %s %s %s|\n" +
                "|%s %s %s %s|\n" +
                "|%s %s %s %s|\n",
                getM00(), getM01(), getM02(), getM03(),
                getM10(), getM11(), getM12(), getM13(),
                getM20(), getM21(), getM22(), getM23(),
                getM30(), getM31(), getM32(), getM33());
    }
}
