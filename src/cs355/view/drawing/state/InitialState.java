package cs355.view.drawing.state;

import cs355.model.drawing.CS355Drawing;
import cs355.view.drawing.DrawableNullShape;
import cs355.view.drawing.DrawableShape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class InitialState extends DrawingState
{
    public InitialState()
    {
        super();
    }

    @Override
    public void mouseClicked(Point2D.Double point, CS355Drawing model)
    {

    }

    @Override
    public void mousePressed(Point2D.Double point, CS355Drawing model)
    {

    }

    @Override
    public void mouseReleased(Point2D.Double point, CS355Drawing model)
    {

    }

    @Override
    public void mouseDragged(Point2D.Double point, CS355Drawing model)
    {

    }

    @Override
    public DrawableShape buildDrawableShape(Color color)
    {
        return new DrawableNullShape(color);
    }
}
