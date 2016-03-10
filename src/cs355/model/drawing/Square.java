package cs355.model.drawing;

import cs355.view.ObjectParameters;
import cs355.view.drawing.util.ShapeUtilities;
import cs355.view.drawing.util.Transform;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your square code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Square extends Shape
{

    // The size of this Square.
    private double size;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param size   the size of the new shape.
     */
    public Square(Color color, Point2D.Double center, double size)
    {

        // Initialize the superclass.
        super(color, center);

        // Set the field.
        this.size = size;
    }

    /**
     * Getter for this Square's size.
     *
     * @return the size as a double.
     */
    public double getSize()
    {
        return size;
    }

    /**
     * Setter for this Square's size.
     *
     * @param size the new size.
     */
    public void setSize(double size)
    {
        this.size = size;
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
        if (getRotation() == NO_ROTATION)
            return ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), getSize(), getSize());
        else
            return ShapeUtilities.pointInBoundingBox(Transform.getObjectPointFromWorldPoint(worldPoint, new ObjectParameters(getCenter(), getRotation())), new Point2D.Double(0, 0), getSize(), getSize());
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

        Square square = (Square) o;

        return Double.compare(square.size, size) == 0;

    }

    @Override
    public int hashCode()
    {
        long temp = Double.doubleToLongBits(size);
        return (int) (temp ^ (temp >>> 32));
    }
}
