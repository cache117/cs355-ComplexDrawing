package cs355.view.drawing.state;

import cs355.view.drawing.DrawableCircle;
import cs355.view.drawing.DrawableShape;

import java.awt.*;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class CircleState extends EllipseState
{
    public CircleState(DrawingState currentState)
    {
        super(currentState);
    }

    @Override
    public DrawableShape buildDrawableShape(Color color)
    {
        return new DrawableCircle(color);
    }
}
