package cs355.view.drawing.util;

import java.awt.geom.Point2D;

/**
 * Represents a linear algebra vector with two or three entries.
 */
public class Vector2D
{
    private final double x, y;

    private Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Initializes a Vector2D from a 2D point.
     *
     * @param point the point to represent as a Vector2D.
     */
    public Vector2D(Point2D.Double point)
    {
        this(point.x, point.y);
    }

    /**
     * Calculates the dot-product of this Vector2D with the given Vector2D.
     *
     * @param vector the Vector2D to calculate the dot-product with.
     * @return the result of the dot-product operation.
     */
    public double dotProduct(Vector2D vector)
    {
        return (this.x * vector.x) + (this.y * vector.y);
    }

    /**
     * Subtracts the given Vector2D from this Vector2D
     *
     * @param vector the Vector2D to subtract
     * @return the result of the subtraction operation.
     */
    public Vector2D subtract(Vector2D vector)
    {
        double x = this.x - vector.x;
        double y = this.y - vector.y;
        return new Vector2D(x, y);
    }

    /**
     * Adds the given Vector2D to this Vector2D.
     *
     * @param vector the Vector2D to add.
     * @return the result of the addition operation.
     */
    public Vector2D add(Vector2D vector)
    {
        double x = this.x + vector.x;
        double y = this.y + vector.y;
        return new Vector2D(x, y);
    }

    /**
     * Returns a Vector2D that represents the orthogonal version of this Vector2D.
     *
     * @return the orthogonal version of this Vector2D.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public Vector2D orthogonalize()
    {
        double x = -y;
        double y = this.x;
        return new Vector2D(x, y);
    }

    /**
     * Gets the length of the vector from the origin. This is equivalent to calling length(new Vector2D(0, 0);
     *
     * @return the length of the vector from the origin.
     */
    public double length()
    {
        return length(new Vector2D(0, 0));
    }

    /**
     * The length or distance of the Vector2D from the given vector.
     *
     * @param start the other vector.
     * @return the length or distance between two vectors.
     */
    public double length(Vector2D start)
    {
        return Math.sqrt(square(this.x - start.x) + square(this.y - start.y));
    }

    /**
     * Calculates the normal of two Vectors.
     *
     * @param p1 the first Vector2D.
     * @param p2 the second Vector2D.
     * @return tje normal of the two Vectors.
     */
    public static Vector2D calculateNormal(Vector2D p1, Vector2D p2)
    {
        Vector2D vectorDifference = p2.subtract(p1);
        Vector2D result = vectorDifference.orthogonalize();
        double length = vectorDifference.length();
        result.applyScaling(1 / length);
        return result;
    }

    private double square(double number)
    {
        return Math.pow(number, 2);
    }

    /**
     * Scales the Vector2D by the given constant.
     *
     * @param scalingAmount the amount to scale by.
     * @return the Vector2D after the scaling operation.
     */
    public Vector2D applyScaling(double scalingAmount)
    {
        double x = this.x * scalingAmount;
        double y = this.y * scalingAmount;
        return new Vector2D(x, y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Vector2D vector = (Vector2D) o;

        if (Double.compare(vector.x, x) != 0)
            return false;
        return Double.compare(vector.y, y) == 0;

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
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("[%s]\n[%s]", x, y);
    }
}
