package cs355.model.drawing;

import cs355.view.ObjectParameters;
import cs355.view.drawing.util.ShapeUtilities;
import cs355.view.drawing.util.Transform;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your triangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Triangle extends Shape
{

    // The three points of the triangle.
    private Point2D.Double a;
    private Point2D.Double b;
    private Point2D.Double c;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param a      the first point, relative to the center.
     * @param b      the second point, relative to the center.
     * @param c      the third point, relative to the center.
     */
    public Triangle(Color color, Point2D.Double center, Point2D.Double a, Point2D.Double b, Point2D.Double c)
    {
        // Initialize the superclass.
        super(color, center);

        // Set fields.
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Getter for the first point.
     *
     * @return the first point as a Java point.
     */
    public Point2D.Double getA()
    {
        return a;
    }

    /**
     * Setter for the first point.
     *
     * @param a the new first point.
     */
    public void setA(Point2D.Double a)
    {
        this.a = a;
    }

    /**
     * Getter for the second point.
     *
     * @return the second point as a Java point.
     */
    public Point2D.Double getB()
    {
        return b;
    }

    /**
     * Setter for the second point.
     *
     * @param b the new second point.
     */
    public void setB(Point2D.Double b)
    {
        this.b = b;
    }

    /**
     * Getter for the third point.
     *
     * @return the third point as a Java point.
     */
    public Point2D.Double getC()
    {
        return c;
    }

    /**
     * Setter for the third point.
     *
     * @param c the new third point.
     */
    public void setC(Point2D.Double c)
    {
        this.c = c;
    }

    /**
     * Add your code to do an intersection test
     * here. You shouldn't need the tolerance.
     *
     * @param worldPoint = the point to test against.
     * @return true if pt is in the shape,
     * false otherwise.
     */
    @Override
    public boolean pointInShape(Point2D.Double worldPoint)
    {
        double greatestDistanceFromCenter = getGreatestDistanceFromCenter();
        if (!ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), greatestDistanceFromCenter * 2, greatestDistanceFromCenter * 2))
            return false;
        if (!ShapeUtilities.pointInBoundingCircle(worldPoint, getCenter(), greatestDistanceFromCenter))
            return false;
        return ShapeUtilities.pointInTriangle(Transform.getObjectPointFromWorldPoint(worldPoint, new ObjectParameters(getCenter(), getRotation())), getA(), getB(), getC());
//        return ShapeUtilities.pointInTriangle(worldPoint, getA(), getB(), getC());
    }

    private double getGreatestDistanceFromCenter()
    {
        //TODO this is exactly the same as DrawableTriangle$getGreatestDistanceFromCenter()
        double firstDistance = Point2D.distance(a.x, a.y, getCenter().x, getCenter().y);
        double middleDistance = Point2D.distance(b.x, b.y, getCenter().x, getCenter().y);
        double endDistance = Point2D.distance(c.x, c.y, getCenter().x, getCenter().y);
        return Math.max(firstDistance, Math.max(middleDistance, endDistance));
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Triangle triangle = (Triangle) o;

        if (!a.equals(triangle.a))
            return false;
        if (!b.equals(triangle.b))
            return false;
        return c.equals(triangle.c);

    }

    @Override
    public int hashCode()
    {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        return result;
    }
}
