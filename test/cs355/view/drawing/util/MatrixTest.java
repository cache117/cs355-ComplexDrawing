package cs355.view.drawing.util;

import cs355.model.scene.Point3D;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.model.MultipleFailureException;

import java.util.Random;

import static java.lang.Math.*;
import static org.junit.Assert.*;

/**
 * Created by cstaheli on 3/22/2016.
 */
public class MatrixTest
{

    @org.junit.Test
    public void testIdentity() throws Exception
    {
        Matrix expected = new Matrix(
                1,0,0,0,
                0,1,0,0,
                0,0,1,0,
                0,0,0,1);
        Matrix actual = Matrix.identity();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void testRotation() throws Exception
    {
        double rotation = PI;
        Matrix expected = new Matrix(
                cos(rotation),0,sin(rotation),0,
                0,1,0,0,
                -sin(rotation),0,cos(rotation),0,
                0,0,0,1);
        Matrix actual = Matrix.rotation(rotation);
        Assert.assertEquals(expected, actual);

        rotation = PI/2.0;
        expected = new Matrix(
                cos(rotation),0,sin(rotation),0,
                0,1,0,0,
                -sin(rotation),0,cos(rotation),0,
                0,0,0,1);
        actual = Matrix.rotation(rotation);
        Assert.assertEquals(expected, actual);

        rotation = 2.0 * PI;
        expected = new Matrix(
                cos(rotation),0,sin(rotation),0,
                0,1,0,0,
                -sin(rotation),0,cos(rotation),0,
                0,0,0,1);
        actual = Matrix.rotation(rotation);
        Assert.assertEquals(expected, actual);

        rotation = PI / 4.0;
        expected = new Matrix(
                cos(rotation),0,sin(rotation),0,
                0,1,0,0,
                -sin(rotation),0,cos(rotation),0,
                0,0,0,1);
        actual = Matrix.rotation(rotation);
        Assert.assertEquals(expected, actual);

        rotation = 0.265 * PI;
        expected = new Matrix(
                cos(rotation),0,sin(rotation),0,
                0,1,0,0,
                -sin(rotation),0,cos(rotation),0,
                0,0,0,1);
        actual = Matrix.rotation(rotation);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void testTranslation() throws Exception
    {
        Point3D translationPoint = new Point3D(35,20,15);
        double x = translationPoint.x;
        double y = translationPoint.y;
        double z = translationPoint.z;
        Matrix expected = new Matrix(
                1,0,0,x,
                0,1,0,y,
                0,0,1,z,
                0,0,0,1);
        Matrix actual = Matrix.translation(translationPoint);
        Assert.assertEquals(expected, actual);

        translationPoint = new Point3D(-235,250,72);
        x = translationPoint.x;
        y = translationPoint.y;
        z = translationPoint.z;
        expected = new Matrix(
                1,0,0,x,
                0,1,0,y,
                0,0,1,z,
                0,0,0,1);
        actual = Matrix.translation(translationPoint);
        Assert.assertEquals(expected, actual);
        int timesToTest = 100;
        for(int i = 0; i < timesToTest; i++)
        {
            translationPoint = generateRandomPoint();
            x = translationPoint.x;
            y = translationPoint.y;
            z = translationPoint.z;
            expected = new Matrix(
                    1,0,0,x,
                    0,1,0,y,
                    0,0,1,z,
                    0,0,0,1);
            actual = Matrix.translation(translationPoint);
            Assert.assertEquals(expected, actual);
        }
    }

    private Point3D generateRandomPoint()
    {
        Random rand = new Random();
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        double z = rand.nextDouble();
        return new Point3D(x,y,z);
    }

    @org.junit.Test
    public void testClipping() throws Exception
    {

    }

    @org.junit.Test
    public void testMultiply() throws Exception
    {
        // I * I = I
        Matrix actual = Matrix.identity();
        Matrix expected = Matrix.multiply(actual, Matrix.identity());
        assertEquals(actual, expected);

        Matrix A = new Matrix(
                1,2,3,4,
                3,2,1,4,
                4,2,1,3,
                3,4,1,2);
        Matrix B = new Matrix(
                7,4,5,6,
                6,7,5,4,
                4,7,6,5,
                7,4,6,5);
        expected = new Matrix(
                59,55,57,49,
                65,49,55,51,
                65,49,54,52,
                63,55,53,49);
        actual = Matrix.multiply(A, B);
        assertEquals(expected, actual);

        //simple rotation
        Matrix reversedOrder = Matrix.multiply(B, A);
        assertNotEquals(reversedOrder, actual);
        double rotation = PI / 2;
        A = Matrix.rotation(rotation);
        expected = new Matrix(
                4,7,6,5,
                6,7,5,4,
                -7,-4,-5,-6,
                7,4,6,5);
        actual = Matrix.multiply(A, B);
        assertEquals(expected, actual);

        //complex Rotation (precision)
        rotation = 2.76 * PI;
        A = Matrix.rotation(rotation);
        B = new Matrix(
                72,41,5,16,
                36,27,5,4,
                4,7,16,51,
                7,45,6,51);
        expected = new Matrix(
                -49.7475527506268, -25.095883982777007, 7.307910557751983, 23.248404363620608,
                36,27,5,4,
                -52.203266136551306,-33.16921173502615,-15.086233568386017,-48.13015369335097,
                7,45,6,51);
        actual = Matrix.multiply(A, B);
        assertEquals(expected, actual);

        actual = Matrix.multiply(B, Matrix.identity());
        expected = B;
        assertEquals(expected, actual);
        actual = Matrix.multiply(Matrix.identity(), B);
        assertEquals(expected, actual);

        //Multiple matrices
        A = new Matrix(
            7,4,5,6,
            6,7,5,4,
            4,7,6,5,
            7,4,6,5);
        B = new Matrix(
            1,2,3,4,
            3,2,1,4,
            4,2,1,3,
            3,4,1,2);
        Matrix C = new Matrix(
                7,-4,5,-6,
                -79,7,5,4,
                4,7,6,2,
                73,4,1,5);
        expected = new Matrix(
                1302,700,852,309,
                1916,666,834,297,
                1558,620,842,252,
                1540,686,848,300);
        actual = Matrix.multiply(A, B);
        actual = Matrix.multiply(actual, C);
        assertEquals(expected, actual);
        actual = Matrix.multiply(B, C);
        actual = Matrix.multiply(A, actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testFullPipeline() throws Exception
    {
        //Full Pipeline
        Matrix clipping, cameraRotation, cameraTranslation, objectTranslation, objectRotation;
        Vector3D point = new Vector3D(150,35,240);
        clipping = Matrix.identity();
        cameraRotation = Matrix.rotation(2.456 * (PI/4.2));
        cameraTranslation = Matrix.translation(-25, -50, -30);
        objectTranslation = Matrix.translation(new Point3D(15,10,36));
        objectRotation = Matrix.rotation(PI);
        Matrix matrix = Matrix.multiply(clipping, cameraRotation);
        matrix = Matrix.multiply(matrix, cameraTranslation);
        matrix = Matrix.multiply(matrix, objectTranslation);
        matrix = Matrix.multiply(matrix, objectRotation);
        Matrix.multiply(matrix, point);
        Vector3D expected = new Vector3D(-183.64832067,-5,215.938172, 1);
        Vector3D actual = Matrix.multiply(matrix, point);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void testVectorMultiply() throws Exception
    {
        Matrix A = Matrix.identity();
        Vector3D vector = new Vector3D(7,12,-2);
        Vector3D expected = vector;
        Vector3D actual = Matrix.multiply(A, vector);
        assertEquals(expected, actual);

        A = new Matrix(
                7,4,5,6,
                6,7,5,4,
                4,7,6,5,
                7,4,6,5);
        expected = new Vector3D(93,120,105,90);
        actual = Matrix.multiply(A, vector);
        assertEquals(expected, actual);
        vector = new Vector3D(10,20,-3);
        A = Matrix.translation(vector.getAsPoint());
    }

    @Test
    public void testRandom() throws Exception
    {
        Matrix random = Matrix.random();
        Matrix random2 = Matrix.random();
        Assert.assertNotEquals(random, random2);

    }
}