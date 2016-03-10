package cs355.view.drawing;

import cs355.GUIFunctions;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.view.DrawingParameters;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents a rectangle. This takes care of all of the drawing and manipulation of rectangles on the screen.
 */
public class DrawableRectangle extends DrawableShape
{
    @SuppressWarnings("WeakerAccess")
    public DrawableRectangle(Shape rectangle)
    {
        super(rectangle);
    }

    public DrawableRectangle(Color color)
    {
        super(color);
        GUIFunctions.printf("Click and drag from corner to corner.");
    }

    @Override
    public void drawShape(DrawingParameters drawingParameters)
    {
        /*
        Point2D.Double upperLeft = calculateUpperLeftPoint();
        graphics.fillRect((int) upperLeft.getX(), (int) upperLeft.getY(), (int) getWidth(), (int) getHeight());
        */
        drawingParameters.graphics2D.fillRect((int) (-getWidth() / 2.0), (int) (-getHeight() / 2.0), (int) getWidth(), (int) getHeight());
    }

    @Override
    public void drawShapeOutline(DrawingParameters drawingParameters)
    {
        drawingParameters.graphics2D.drawRect((int) (-getWidth() / 2) - 2, (int) (-getHeight() / 2) - 2, (int) (getWidth() + 3), (int) (getHeight() + 3));
        GUIFunctions.printf("Drawing outline");
    }

    @Override
    protected void drawShapeHandle(Graphics2D graphics2D)
    {
        graphics2D.drawOval(-HANDLE_RADIUS, (int) ((-getHeight() / 2) - HANDLE_DISTANCE_FROM_OUTLINE), HANDLE_DIAMETER, HANDLE_DIAMETER);
    }

    @Override
    public Point2D.Double getHandleCenterPoint()
    {
        return new Point2D.Double(0, (-getHeight() / 2) - HANDLE_CENTER_DISTANCE_FROM_OUTLINE);
    }

    private Point2D.Double calculateUpperLeftPoint()
    {
        Point2D.Double startPoint = getStartPoint();
        Point2D.Double endPoint = getEndPoint();
        double startX = startPoint.x;
        double startY = startPoint.y;
        double endX = endPoint.x;
        double endY = endPoint.y;

        if (startX > endX)
        {
            if (startY >= endY)
                return endPoint;
            else
                return new Point2D.Double(endX, startY);
        } else if (startX == endX)
        {
            if (startY > endY)
                return endPoint;
            else
                return startPoint;
        } else
        {
            if (startY > endY)
                return new Point2D.Double(startX, endY);
            else
                return startPoint;
        }
    }

    Point2D.Double calculateUpperLeftFromCenter(Point2D.Double center, double width, double height)
    {
        double x = (center.x - (width / 2.0));
        double y = (center.y - (height / 2.0));
        return new Point2D.Double(x, y);
    }

    Point2D.Double calculateLowerRightFromUpperLeft(Point2D.Double upperLeft, double width, double height)
    {
        double lowerRightX = (upperLeft.x + width);
        double lowerRightY = (upperLeft.y + height);
        return new Point2D.Double(lowerRightX, lowerRightY);
    }

    Point2D.Double calculateCenterFromUpperLeft()
    {
        Point2D.Double upperLeft = calculateUpperLeftPoint();
        double x =  (upperLeft.x + getWidth() / 2.0);
        double y =  (upperLeft.y + getHeight() / 2.0);
        return new Point2D.Double(x, y);
    }

    double getWidth()
    {
        Point2D.Double startPoint = getStartPoint();
        double startX = startPoint.x;
        double startY = startPoint.y;
        double endX = getEndPoint().x;

        return Math.abs(Point2D.distance(startX, startY, endX, startY));
    }

    double getHeight()
    {
        Point2D.Double startPoint = getStartPoint();
        double startX = startPoint.x;
        double startY = startPoint.y;
        double endY = getEndPoint().y;

        return Math.abs(Point2D.distance(startX, startY, startX, endY));
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Rectangle rectangle = (Rectangle) shape;
        double width = rectangle.getWidth();
        double height = rectangle.getHeight();
        Point2D.Double upperLeft = calculateUpperLeftFromCenter(getCenterPoint(), width, height);
        setStartPoint(upperLeft);
        setEndPoint(calculateLowerRightFromUpperLeft(upperLeft, width, height));
    }

    @Override
    public Shape getModelShape()
    {
        Rectangle rectangle = new Rectangle(getColor(), calculateCenterFromUpperLeft(), getWidth(), getHeight());
        if (getRotation() != 0.0)
            rectangle.setRotation(getRotation());
        return rectangle;
    }

}
