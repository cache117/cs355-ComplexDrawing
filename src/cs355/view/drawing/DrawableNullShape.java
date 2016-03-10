package cs355.view.drawing;

import cs355.model.drawing.NullShape;
import cs355.model.drawing.Shape;
import cs355.view.DrawingParameters;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * A DrawableShape that represents null.
 */
public class DrawableNullShape extends DrawableShape
{
    /**
     * Creates a DrawableShape that does nothing. Avoids Null pointers.
     * @param color the color of the shape.
     */
    public DrawableNullShape(Color color)
    {
        super(color);
    }
    @Override
    protected void drawShape(DrawingParameters drawingParameters)
    {

    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {

    }

    @Override
    public Shape getModelShape()
    {
        return new NullShape();
    }

    @Override
    public void drawShapeOutline(DrawingParameters drawingParameters)
    {

    }

    @Override
    public Point2D.Double getHandleCenterPoint()
    {
        return new Point2D.Double(0,0);
    }

    @Override
    public void drawOutline(DrawingParameters drawingParameters)
    {

    }

    @Override
    protected void drawShapeHandle(Graphics2D graphics2D)
    {

    }
}
