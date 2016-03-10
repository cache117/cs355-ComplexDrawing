package cs355.model.drawing;

import cs355.view.ObjectParameters;
import cs355.view.drawing.util.ShapeUtilities;
import cs355.view.drawing.util.Transform;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your rectangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Rectangle extends Shape
{

    // The width of this shape.
    private double width;

    // The height of this shape.
    private double height;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param width  the width of the new shape.
     * @param height the height of the new shape.
     */
    public Rectangle(Color color, Point2D.Double center, double width, double height)
    {

        // Initialize the superclass.
        super(color, center);

        // Set fields.
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for this shape's width.
     *
     * @return this shape's width as a double.
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * Setter for this shape's width.
     *
     * @param width the new width.
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     * Getter for this shape's height.
     *
     * @return this shape's height as a double.
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Setter for this shape's height.
     *
     * @param height the new height.
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * Add your code to do an intersection test
     * here. You shouldn't need the tolerance.
     *
     * @param worldPoint = the point to test against.
     * @return true if pt is in the shape, false otherwise.
     */
    @Override
    public boolean pointInShape(Point2D.Double worldPoint)
    {
        if (getRotation() == NO_ROTATION)
            return ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), getWidth(), getHeight());
        else
        {
            /*//Expanded bounding box
            if (!ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), getWidth(), getHeight()))
            {
                return false;
            } else
            {*/
            //Check in more depth
            return ShapeUtilities.pointInBoundingBox(Transform.getObjectPointFromWorldPoint(worldPoint, new ObjectParameters(getCenter(), getRotation())), new Point2D.Double(0, 0), getWidth(), getHeight());
            //}
        }
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

        Rectangle rectangle = (Rectangle) o;

        if (Double.compare(rectangle.width, width) != 0)
            return false;
        return Double.compare(rectangle.height, height) == 0;

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
