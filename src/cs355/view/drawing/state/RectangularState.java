package cs355.view.drawing.state;

import cs355.GUIFunctions;
import cs355.model.drawing.CS355Drawing;
import cs355.view.drawing.DrawableRectangle;
import cs355.view.drawing.DrawableShape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class RectangularState extends DrawingState
{
    public RectangularState(DrawingState currentState)
    {
        super(currentState);
    }

    @Override
    public void mouseClicked(Point2D.Double point, CS355Drawing model)
    {
        DrawableShape drawableShape = getDrawableShape();
        drawableShape.clearPoints();
        model.deleteShape(0);
        GUIFunctions.printf("Click and drag from corner to corner.");
    }

    @Override
    public void mousePressed(Point2D.Double point, CS355Drawing model)
    {
        DrawableShape drawableShape = getDrawableShape();
        drawableShape.setStartPoint(point);
        drawableShape.setEndPoint(point);
        drawableShape.setNumberOfActualPoints(1);
        model.addShape(getDrawableShape().getModelShape());
        GUIFunctions.printf("Drag shape to corner.");
    }

    @Override
    public void mouseReleased(Point2D.Double point, CS355Drawing model)
    {
        DrawableShape drawableShape = getDrawableShape();
        if (drawableShape.getNumberOfActualPoints() > 0)
        {
            drawableShape.updateEndPoint(point, model);
            drawableShape.clearPoints();
            GUIFunctions.printf("Click and drag from corner to corner.");
        }
    }

    @Override
    public void mouseDragged(Point2D.Double point, CS355Drawing model)
    {
        DrawableShape drawableShape = getDrawableShape();
        drawableShape.setNumberOfActualPoints(2);
        drawableShape.updateEndPoint(point, model);
    }

    @Override
    public DrawableShape buildDrawableShape(Color color)
    {
        return new DrawableRectangle(color);
    }
}
