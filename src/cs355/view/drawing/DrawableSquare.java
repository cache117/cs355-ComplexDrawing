package cs355.view.drawing;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.view.drawing.util.ShapeUtilities;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents a square. This takes care of all of the drawing and manipulation of squares on the screen.
 */
public class DrawableSquare extends DrawableRectangle
{
    public DrawableSquare(Shape square)
    {
        super(square);
    }

    public DrawableSquare(Color color)
    {
        super(color);
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Square square = (Square) shape;
        double size = square.getSize();
        Point2D.Double upperLeft = calculateUpperLeftFromCenter(getCenterPoint(), size, size);
        setStartPoint(upperLeft);
        setEndPoint(calculateLowerRightFromUpperLeft(upperLeft, size, size));
    }

    @Override
    public Shape getModelShape()
    {
        Square square = new Square(getColor(), calculateCenterFromUpperLeft(), getWidth());
        if (getRotation() != 0.0)
            square.setRotation(getRotation());
        return square;
    }

    @Override
    public void setEndPoint(Point2D.Double endPoint)
    {
        Point2D.Double symmetricEndPoint = ShapeUtilities.calculateSymmetricPoint(getStartPoint(), endPoint);
        super.setEndPoint(symmetricEndPoint);
    }
}
