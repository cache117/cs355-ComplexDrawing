package cs355.model.drawing;

import cs355.view.ObjectParameters;
import cs355.view.drawing.util.ShapeUtilities;
import cs355.view.drawing.util.Transform;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your line code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Line extends Shape
{

    // The ending point of the line.
    private Point2D.Double end;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color the color for the new shape.
     * @param start the starting point.
     * @param end   the ending point.
     */
    public Line(Color color, Point2D.Double start, Point2D.Double end)
    {

        // Initialize the superclass.
        super(color, start);

        // Set the field.
        this.end = end;
    }

    /**
     * Getter for this Line's ending point.
     *
     * @return the ending point as a Java point.
     */
    public Point2D.Double getEnd()
    {
        return end;
    }

    /**
     * Setter for this Line's ending point.
     *
     * @param end the new ending point for the Line.
     */
    public void setEnd(Point2D.Double end)
    {
        this.end = end;
    }

    /**
     * Add your code to do an intersection test
     * here. You <i>will</i> need the tolerance.
     *
     * @param worldPoint = the point to test against.
     * @return true if pt is in the shape, false otherwise.
     */
    @Override
    public boolean pointInShape(Point2D.Double worldPoint)
    {
        return ShapeUtilities.pointCloseEnoughToLine(Transform.getObjectPointFromWorldPoint(worldPoint, new ObjectParameters(getCenter(), getRotation())), getCenter(), getEnd(), getTolerance());
    }

    private double getTolerance()
    {
        return 4.0;
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

        Line line = (Line) o;

        return end.equals(line.end);

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
