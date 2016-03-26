package cs355.view.drawing.util;

import cs355.model.scene.Point3D;

import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 3/20/2016.
 */
public class Vector3D
{
    private double x, y, z, homogeneous;

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.homogeneous = 1;
    }

    public Vector3D(double x, double y, double z, double homogeneous)
    {
        this(x, y, z);
        this.homogeneous = homogeneous;
    }

    public Vector3D(Point3D point)
    {
        this(point.x, point.y, point.z);
    }

    public Vector3D(double[] vector)
    {
        this(vector[0], vector[1], vector[2], vector[3]);
    }

    public double[] getAsArray()
    {
        return new double[]{x, y, z, homogeneous};
    }

    public Point3D getAsPoint()
    {
        return new Point3D(x, y, z);
    }

    public Point2D.Double getAs2DPoint()
    {
        return new Point2D.Double(x, y);
    }

    @Override
    public String toString()
    {
        return String.format("|%s, %s, %s, %s|", x, y, z, homogeneous);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Vector3D vector3D = (Vector3D) o;

        double precision = .00000000001;
        if (!areValuesSimilarEnough(vector3D.x, x, precision))
            return false;
        if (!areValuesSimilarEnough(vector3D.y, y, precision))
            return false;
        if (!areValuesSimilarEnough(vector3D.z, z, precision))
            return false;
        return !areValuesSimilarEnough(vector3D.homogeneous, homogeneous, precision);

    }

    private boolean areValuesSimilarEnough(double first, double second, double precision)
    {
        return first == second || Math.abs(first / second - 1) < precision;
    }

    public Vector3D normalize()
    {
        double x = this.x / this.homogeneous;
        double y = this.y / this.homogeneous;
        double z = this.z / this.homogeneous;
        return new Vector3D(x, y, z);
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(homogeneous);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public double getHomogeneous()
    {
        return homogeneous;
    }
}
