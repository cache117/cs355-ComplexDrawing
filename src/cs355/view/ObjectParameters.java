package cs355.view;

import cs355.view.drawing.DrawableShape;

import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 2/18/2016.
 */
public class ObjectParameters
{
    public final Point2D.Double center;
    public final double rotation;

    public ObjectParameters(Point2D.Double center, double rotation)
    {
        this.center = center;
        this.rotation = rotation;
    }

    public ObjectParameters(DrawableShape drawableShape)
    {
        this.center = drawableShape.getCenterPoint();
        this.rotation = drawableShape.getRotation();
    }
}
