package cs355.view.drawing.state;

import cs355.view.drawing.DrawableEllipse;
import cs355.view.drawing.DrawableShape;

import java.awt.*;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class EllipseState extends RectangularState
{
    public EllipseState(DrawingState currentState)
    {
        super(currentState);
    }

    @Override
    public DrawableShape buildDrawableShape(Color color)
    {
        return new DrawableEllipse(color);
    }
}
