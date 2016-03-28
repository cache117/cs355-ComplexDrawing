package cs355.view.drawing;


import cs355.model.scene.Point3D;
import cs355.view.drawing.util.Matrix;
import cs355.view.drawing.util.Transform;
import cs355.view.drawing.util.Vector3D;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 3/27/2016.
 */
public class Drawable3DLine
{
    private final Color color;
    private Point2D.Double start;
    private Point2D.Double end;
    private boolean drawable;
//    private

    public Drawable3DLine(Color color, Point3D start, Point3D end, Matrix objectMatrix, Matrix cameraMatrix)
    {
        this.color = color;
        build2DPointsFrom3DPoints(start, end, objectMatrix, cameraMatrix);
    }

    private void build2DPointsFrom3DPoints(Point3D startPoint, Point3D endPoint, Matrix objectMatrix, Matrix cameraMatrix)
    {
        Vector3D start = clipVector(Transform.getCullingVectorFromObjectPoint(startPoint, cameraMatrix, objectMatrix));
        Vector3D end = clipVector(Transform.getCullingVectorFromObjectPoint(endPoint, cameraMatrix, objectMatrix));
        this.drawable = true; //TODO figure out hwo to throw away bad lines
        this.start = Transform.getScreenSpaceCoordinate(start.normalize().getAs2DPoint());
        this.end = Transform.getScreenSpaceCoordinate(end.normalize().getAs2DPoint());
    }

    private Vector3D clipVector(Vector3D vector)
    {
        double w = vector.getHomogeneous();
        double x = clipValue(vector.getX(), w);
        double y = clipValue(vector.getY(), w);
        double z = clipValue(vector.getZ(), w);
        return new Vector3D(x, y, z, w);
    }

    private double clipValue(double value, double w)
    {
        if (value < -w)
            return -w;
        else if (value > w)
            return w;
        else
            return value;
    }

    public void draw(Graphics2D graphics)
    {
        if (drawable)
        {
            graphics.setColor(color);
            graphics.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
        }
    }
}
