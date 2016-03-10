package cs355.model.drawing;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 1/18/2016.
 */
public class NullShape extends Shape
{
    /**
     * Basic constructor that sets the field.
     */
    public NullShape()
    {
        super(Color.BLACK, new Point2D.Double(0,0));
    }

    @Override
    public boolean pointInShape(Point2D.Double worldPoint)
    {
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        return false;
    }
}
