package cs355.view.drawing.state;

import cs355.view.drawing.DrawableShape;
import cs355.view.drawing.DrawableSquare;

import java.awt.*;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class SquareState extends RectangularState
{
    public SquareState(DrawingState currentState)
    {
        super(currentState);
    }

    @Override
    public DrawableShape buildDrawableShape(Color color)
    {
        return new DrawableSquare(color);
    }
}
