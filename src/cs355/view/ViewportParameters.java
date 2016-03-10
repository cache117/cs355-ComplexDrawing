package cs355.view;

import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 2/18/2016.
 */
public class ViewportParameters
{
    public final Point2D.Double upperLeft;
    public final double scalingFactor;

    public ViewportParameters(Point2D.Double upperLeft, double scalingFactor)
    {
        this.upperLeft = upperLeft;
        this.scalingFactor = scalingFactor;
    }
}
