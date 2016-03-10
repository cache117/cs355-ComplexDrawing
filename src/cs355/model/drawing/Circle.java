package cs355.model.drawing;

import cs355.view.drawing.util.ShapeUtilities;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your circle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Circle extends Shape
{

    // The radius.
    private double radius;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param radius the radius of the new shape.
     */
    public Circle(Color color, Point2D.Double center, double radius)
    {
        // Initialize the superclass.
        super(color, center);

        // Set the field.
        this.radius = radius;
    }

    /**
     * Getter for this Circle's radius.
     *
     * @return the radius of this Circle as a double.
     */
    public double getRadius()
    {
        return radius;
    }

    /**
     * Setter for this Circle's radius.
     *
     * @param radius the new radius of this Circle.
     */
    public void setRadius(double radius)
    {
        this.radius = radius;
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
        return ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), getRadius() * 2, getRadius() * 2) && ShapeUtilities.pointInBoundingCircle(worldPoint, getCenter(), getRadius());
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

        Circle circle = (Circle) o;

        return Double.compare(circle.radius, radius) == 0;

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
