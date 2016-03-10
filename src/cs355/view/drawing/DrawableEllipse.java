package cs355.view.drawing;

import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Shape;
import cs355.view.DrawingParameters;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents an ellipse. This takes care of all of the drawing and manipulation of ellipses on the screen.
 */
public class DrawableEllipse extends DrawableRectangle
{
    /**
     * Creates a DrawableEllipse from an Ellipse.
     * @param ellipse the Ellipse.
     */
    @SuppressWarnings("WeakerAccess")
    public DrawableEllipse(Shape ellipse)
    {
        super(ellipse);
    }

    /**
     * Creates a basic DrawableEllipse from a color.
     * @param color the color.
     */
    public DrawableEllipse(Color color)
    {
        super(color);
    }

    @Override
    public void drawShape(DrawingParameters drawingParameters)
    {
        //Point2D.Double upperLeftPoint = calculateUpperLeftPoint();
        drawingParameters.graphics2D.fillOval((int) (-getWidth() /  2.0), (int) (-getHeight() / 2.0), (int) getWidth(), (int) getHeight());
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Ellipse ellipse = (Ellipse) shape;
        setStartPoint(calculateUpperLeftFromCenter(ellipse.getCenter(), ellipse.getWidth(), ellipse.getHeight()));
        setEndPoint(calculateLowerRightFromCenter(ellipse.getCenter(), ellipse.getWidth(), ellipse.getHeight()));
    }

    /**
     * Calculates the lower right point of the ellipse from the center, width, and height.
     * @param center the center of the ellipse.
     * @param width the width of the ellipse.
     * @param height the height of the ellipse.
     * @return the lower right point of the ellipse.
     */
    Point2D.Double calculateLowerRightFromCenter(Point2D.Double center, double width, double height)
    {
        double x = (center.x + (width / 2.0));
        double y = (center.y + (height / 2.0));
        return new Point2D.Double(x, y);
    }

    @Override
    public Shape getModelShape()
    {
        Ellipse ellipse = new Ellipse(getColor(), calculateCenterFromUpperLeft(), getWidth(), getHeight());
        if (getRotation() != 0.0)
            ellipse.setRotation(getRotation());
        return ellipse;
    }
}
