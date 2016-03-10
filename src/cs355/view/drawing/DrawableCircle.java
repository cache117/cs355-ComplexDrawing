package cs355.view.drawing;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;
import cs355.view.drawing.util.ShapeUtilities;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents a circle. This takes care of all of the drawing and manipulation of circles on the screen.
 */
public class DrawableCircle extends DrawableEllipse
{
    /**
     * Creates the Drawable Circle from the given circle.
     * @param circle the circle to initialize from.
     */
    public DrawableCircle(Shape circle)
    {
        super(circle);
    }

    /**
     * Creates a basic DrawableCircle from a color.
     * @param color the color to initialize from.
     */
    public DrawableCircle(Color color)
    {
        super(color);
    }

    @Override
    public Shape getModelShape()
    {
        return new Circle(getColor(), calculateCenterFromUpperLeft(), getHeight() / 2);
    }

    @Override
    public void setEndPoint(Point2D.Double endPoint)
    {
        Point2D.Double symmetricEndPoint = ShapeUtilities.calculateSymmetricPoint(getStartPoint(), endPoint);
        super.setEndPoint(symmetricEndPoint);
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Circle circle = (Circle) shape;
        double diameter = 2.0 * circle.getRadius();
        setStartPoint(calculateUpperLeftFromCenter(circle.getCenter(), diameter, diameter));
        setEndPoint(calculateLowerRightFromCenter(circle.getCenter(), diameter, diameter));
    }

    @Override
    protected void drawShapeHandle(Graphics2D graphics2D)
    {

    }
}
