package cs355.view.drawing;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;
import cs355.view.DrawingParameters;
import cs355.view.ObjectParameters;
import cs355.view.drawing.util.Transform;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * An objects that represents a triangle. This takes care of all of the drawing and manipulation of triangles on the screen.
 */
public class DrawableTriangle extends DrawableShape
{
    private Point2D.Double middlePoint;

    public DrawableTriangle(Shape triangle)
    {
        super(triangle);
    }

    public DrawableTriangle(Color color)
    {
        super(color);
        GUIFunctions.printf("Click for each vertex.");
    }

    @Override
    public void drawShape(DrawingParameters drawingParameters)
    {
        drawingParameters.graphics2D.fillPolygon(getXPoints(), getYPoints(), getExpectedPoints());
    }

    @Override
    public void drawShapeOutline(DrawingParameters drawingParameters)
    {
        drawingParameters.graphics2D.drawPolygon(getXPoints(), getYPoints(), getExpectedPoints());
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Triangle triangle = (Triangle) shape;

        setStartPoint(Transform.getWorldPointFromObjectPoint(triangle.getA(), new ObjectParameters(getCenterPoint(), getRotation())));
        setMiddlePoint(Transform.getWorldPointFromObjectPoint(triangle.getB(), new ObjectParameters(getCenterPoint(), getRotation())));
        setEndPoint(Transform.getWorldPointFromObjectPoint(triangle.getC(), new ObjectParameters(getCenterPoint(), getRotation())));
        setNumberOfActualPoints(3);

//        setStartPoint(triangle.getA());
//        setMiddlePoint(triangle.getB());
//        setEndPoint(triangle.getC());
    }

    @Override
    protected int getExpectedPoints()
    {
        return 3;
    }

    @Override
    public Shape getModelShape()
    {
        Triangle triangle = new Triangle(getColor(), getCenterPoint(),
                Transform.getObjectPointFromWorldPoint(getStartPoint(), new ObjectParameters(getCenterPoint(), getRotation())),
                Transform.getObjectPointFromWorldPoint(getMiddlePoint(), new ObjectParameters(getCenterPoint(), getRotation())),
                Transform.getObjectPointFromWorldPoint(getEndPoint(), new ObjectParameters(getCenterPoint(), getRotation())));

//        Triangle triangle = new Triangle(getColor(), getCenterPoint(), getStartPoint(), getMiddlePoint(), getEndPoint());
        if (getRotation() != 0.0)
            triangle.setRotation(getRotation());
        return triangle;
    }

    private double getAveragePoint(double p1, double p2, double p3)
    {
        return (p1 + p2 + p3) / 3;
    }

    private Point2D.Double calculateCenterPoint()
    {
        double x = getAveragePoint(getStartPoint().x, getMiddlePoint().x, getEndPoint().x);
        double y = getAveragePoint(getStartPoint().y, getMiddlePoint().y, getEndPoint().y);
        return new Point2D.Double(x, y);
    }

    public void addPoint(Point2D.Double point, CS355Drawing model)
    {
        switch (getNumberOfActualPoints())
        {
            case 0:
                setStartPoint(point);
                setMiddlePoint(point);
                setEndPoint(point);
                setNumberOfActualPoints(1);

                GUIFunctions.printf("Click to set second vertex.");
                break;
            case 1:
                setMiddlePoint(point);
                setNumberOfActualPoints(2);

                GUIFunctions.printf("Click to set third vertex.");
                break;
            case 2:
                setEndPoint(point);
                setCenterPoint(calculateCenterPoint());

                model.addShape(getModelShape());
                this.clearPoints();
                GUIFunctions.printf("Click to set first vertex.");
                break;
            default:
                assert false;
        }
    }

    private Point2D.Double getMiddlePoint()
    {
        return middlePoint;
    }

    private void setMiddlePoint(Point2D.Double middlePoint)
    {
        this.middlePoint = middlePoint;
    }

    private int[] getXPoints()
    {
        int[] xPoints = new int[getExpectedPoints()];
        xPoints[0] = (int) getStartPoint().x;
        xPoints[1] = (int) getMiddlePoint().x;
        xPoints[2] = (int) getEndPoint().x;
        return xPoints;
    }

    private int[] getYPoints()
    {
        int[] yPoints = new int[getExpectedPoints()];
        yPoints[0] = (int) getStartPoint().y;
        yPoints[1] = (int) getMiddlePoint().y;
        yPoints[2] = (int) getEndPoint().y;
        return yPoints;
    }

    private double getGreatestDistanceFromCenter()
    {
        //TODO this is exactly the same as Triangle$getGreatestDistanceFromCenter()
        double firstDistance = Point2D.distance(getStartPoint().x, getStartPoint().y, getCenterPoint().x, getCenterPoint().y);
        double middleDistance = Point2D.distance(getMiddlePoint().x, getMiddlePoint().y, getCenterPoint().x, getCenterPoint().y);
        double endDistance = Point2D.distance(getEndPoint().x, getEndPoint().y, getCenterPoint().x, getCenterPoint().y);
        return Math.max(firstDistance, Math.max(middleDistance, endDistance));
    }

    @Override
    protected void applyTransformationToGraphics(DrawingParameters drawingParameters)
    {
        AffineTransform affineTransform = new AffineTransform();
        drawingParameters.graphics2D.setTransform(affineTransform);
    }

    @Override
    public Point2D.Double getHandleCenterPoint()
    {
        return getStartPoint();
    }
}
