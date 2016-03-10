package cs355.view.drawing.state;

import cs355.GUIFunctions;
import cs355.model.drawing.CS355Drawing;
import cs355.view.drawing.DrawableLine;
import cs355.view.drawing.DrawableShape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class LineState extends DrawingState
{
    public LineState(DrawingState currentState)
    {
        super(currentState);
    }

    @Override
    public void mouseClicked(Point2D.Double point, CS355Drawing model)
    {
        getDrawableShape().clearPoints();
        /* Since the shape was not completed, erase the saved shape */
        model.deleteShape(0);
    }

    @Override
    public void mousePressed(Point2D.Double point, CS355Drawing model)
    {
        DrawableShape drawableShape = getDrawableShape();
        drawableShape.setStartPoint(point);
        drawableShape.setEndPoint(point);
        drawableShape.setNumberOfActualPoints(1);
        model.addShape(drawableShape.getModelShape());
        GUIFunctions.printf("Drag line to end point.");
    }

    @Override
    public void mouseReleased(Point2D.Double point, CS355Drawing model)
    {
        DrawableShape drawableShape = getDrawableShape();
        if (drawableShape.getNumberOfActualPoints() > 0)
        {
            drawableShape.updateEndPoint(point, model);
            drawableShape.clearPoints();
        }
        GUIFunctions.printf("Click and drag from start of line to finish.");
    }

    @Override
    public void mouseDragged(Point2D.Double point, CS355Drawing model)
    {
        DrawableShape drawableShape = getDrawableShape();
        drawableShape.updateEndPoint(point, model);
        drawableShape.setNumberOfActualPoints(2);
    }

    @Override
    public DrawableShape buildDrawableShape(Color color)
    {
        return new DrawableLine(color);
    }
}
