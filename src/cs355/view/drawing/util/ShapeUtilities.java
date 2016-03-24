package cs355.view.drawing.util;

import java.awt.geom.Point2D;

/**
 * Utilities to make Shape selecting and drawing easier.
 */
public class ShapeUtilities
{
    /**
     * Calculates the symmetric point that is closest to the dragged to point. This method tries to make the shape's edges
     * draw as close to the mouse as possible.
     *
     * @param startPoint   the starting point of the shape.
     * @param draggedPoint the end point of the shape.
     * @return the end point that makes the shape symmetric.
     */
    public static Point2D.Double calculateSymmetricPoint(Point2D.Double startPoint, Point2D.Double draggedPoint)
    {
        int startX = (int) startPoint.x;
        int startY = (int) startPoint.y;
        int draggedX = (int) draggedPoint.x;
        int draggedY = (int) draggedPoint.y;
        int xDistance = draggedX - startX;
        int yDistance = draggedY - startY;

        Point2D.Double symmetricPoint;
        int comparison = Integer.compare(Math.abs(xDistance), Math.abs(yDistance));

        if (xDistance < 0)
        {
            if (yDistance < 0)
                symmetricPoint = getPositiveSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);
            else
                symmetricPoint = getNegativeSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);
        } else if (xDistance > 0)
        {
            if (yDistance < 0)
                symmetricPoint = getNegativeSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);
            else
                symmetricPoint = getPositiveSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);

        } else
            symmetricPoint = draggedPoint;

        return symmetricPoint;
    }

    private static Point2D.Double getPositiveSymmetricPoint(int comparison, int startX, int draggedX, int xDistance, int startY, int draggedY, int yDistance)
    {
        Point2D.Double symmetricPoint;
        if (comparison < 0)
            symmetricPoint = new Point2D.Double(startX + yDistance, draggedY);
        else
            symmetricPoint = new Point2D.Double(draggedX, startY + xDistance);
        return symmetricPoint;
    }

    private static Point2D.Double getNegativeSymmetricPoint(int comparison, int startX, int draggedX, int xDistance, int startY, int draggedY, int yDistance)
    {
        Point2D.Double symmetricPoint;
        if (comparison < 0)
            symmetricPoint = new Point2D.Double(startX - yDistance, draggedY);
        else
            symmetricPoint = new Point2D.Double(draggedX, startY - xDistance);
        return symmetricPoint;
    }

    private static double getLowerXBoundOfBox(double centerX, double width)
    {
        return centerX - (width / 2.0);
    }

    private static double getUpperXBoundOfBox(double centerX, double width)
    {
        return centerX + (width / 2.0);
    }

    private static double getLowerYBoundOfBox(double centerY, double height)
    {
        return centerY - (height / 2.0);
    }

    private static double getUpperYBoundOfBox(double centerY, double height)
    {
        return centerY + (height / 2.0);
    }

    /**
     * Checks whether or not a point is in a bounding box.
     *
     * @param point  the point.
     * @param center the center of the bounding box
     * @param width  the width of the bounding box
     * @param height the height of the bounding box
     * @return true if the point is in the bounding box, false otherwise.
     */
    public static boolean pointInBoundingBox(Point2D.Double point, Point2D.Double center, double width, double height)
    {
        double lowerXBound = getLowerXBoundOfBox(center.x, width);
        double upperXBound = getUpperXBoundOfBox(center.x, width);
        double lowerYBound = getLowerYBoundOfBox(center.y, height);
        double upperYBound = getUpperYBoundOfBox(center.y, height);
        return ((point.x >= lowerXBound && point.x <= upperXBound) && (point.y >= lowerYBound && point.y <= upperYBound));
    }

    /**
     * Checks whether or not a point is in a bounding circle.
     *
     * @param point  the point.
     * @param center the center of the bounding circle
     * @param radius the radius of the bounding circle
     * @return true if the point is in the bounding circle, false otherwise.
     */
    public static boolean pointInBoundingCircle(Point2D.Double point, Point2D.Double center, double radius)
    {
        double distance = Point2D.distance(point.x, point.y, center.x, center.y);
        return distance <= radius;
    }

    /**
     * Checks whether or not a point is in an ellipse.
     *
     * @param point   the point.
     * @param center  the center of the ellipse
     * @param xRadius the xRadius of the ellipse
     * @param yRadius the yRadius of the ellipse
     * @return true if the point is in the ellipse, false otherwise.
     */
    public static boolean pointInEllipse(Point2D.Double point, Point2D.Double center, double xRadius, double yRadius)
    {
        return (Math.pow(((point.x - center.x) / xRadius), 2) + Math.pow(((point.y - center.y) / yRadius), 2) <= 1);
    }

    /**
     * Checks whether the given point is in a triangle by using the dot product of vectors of each side of the triangle.
     *
     * @param point the point.
     * @param a     one of the vertices of the triangle.
     * @param b     one of the vertices of the triangle.
     * @param c     one of the vertices of the triangle.
     * @return true if the point is in the triangle.
     * @see Vector2D
     */
    public static boolean pointInTriangle(Point2D.Double point, Point2D.Double a, Point2D.Double b, Point2D.Double c)
    {
        Vector2D q = new Vector2D(point);
        Vector2D p0 = new Vector2D(a);
        Vector2D p1 = new Vector2D(b);
        Vector2D p2 = new Vector2D(c);

        //(q-p0)*(p1-p0).orthog()
        double firstDotProduct = (q.subtract(p0)).dotProduct(p1.subtract(p0).orthogonalize());
        double secondDotProduct = (q.subtract(p1)).dotProduct(p2.subtract(p1).orthogonalize());
        double thirdDotProduct = (q.subtract(p2)).dotProduct(p0.subtract(p2).orthogonalize());
        return allSameSign(firstDotProduct, secondDotProduct, thirdDotProduct);
    }

    /**
     * Checks to see if the point is close enough to the line segment.
     *
     * @param point     the point.
     * @param lineStart the start of the line.
     * @param lineEnd   the end of the line.
     * @param tolerance how much tolerance to each side of the line to check in.
     * @return true if the point is close enough to the line, based on the tolerance.
     */
    public static boolean pointCloseEnoughToLine(Point2D.Double point, Point2D.Double lineStart, Point2D.Double lineEnd, double tolerance)
    {
        //q' = q + (d - q * n^)*n^
        Vector2D pointToCheck = new Vector2D(point);
        Vector2D start = new Vector2D(lineStart);
        Vector2D end = new Vector2D(lineEnd);
        Vector2D normalToLine = Vector2D.calculateNormal(start, end);
        double lineDistanceFromOrigin = start.dotProduct(normalToLine);
        Vector2D nearestPointOnLine = pointToCheck.add(normalToLine.applyScaling(lineDistanceFromOrigin - (pointToCheck.dotProduct(normalToLine))));
        double pointDistanceFromLine = nearestPointOnLine.length(pointToCheck);
        return pointDistanceFromLine <= tolerance;
        /*
        //Nicks
        double startX = lineStart.getX();		//p0
        double startY = lineStart.getY();		//p0
        double endX = lineEnd.getX();		//p1
        double endY = lineEnd.getY();		//p1
        double length = Math.sqrt((Math.pow((endX-startX), 2) + Math.pow((endY-startY), 2)));	// a2+b2 = c2

        Point2D.Double dHat = new Point2D.Double(((endX-startX)/length), ((endY-startY)/length)); //(p1 - p0 / length of line)
        double tx = (x - startX) * dHat.getX();	//(q-p0)*dHat
        double ty = (y - startY) * dHat.getY();	//(q-p0)*dHat
        double t = tx + ty;

        Point2D.Double q = new Point2D.Double((startX + t*dHat.getX()), (startY + t*dHat.getY()));	//q = p0 + t * dHat

        double distance = Math.sqrt(Math.pow((q.getX()-x), 2) + Math.pow((q.getY()-y), 2));	//distance to line -> pythagorean

        return distance <= 4;

        //Amanda
        double X0 = pt.getX();
        double Y0 = pt.getY();
        double X1 = center.getX();
        double Y1 = center.getY();
        double X2 = end.getX();
        double Y2 = end.getY();
        double slope = (Y2-Y1)/(X2-X1);
        double tangentSlope = -1/slope;
        double degree = Math.atan(tangentSlope);
        Y0 += Math.sin(degree);
        X0 += Math.cos(degree);
        if (X0 <= (Math.max(X1, X2)+tolerance) && X0 >= (Math.min(X1,X2)-tolerance) && Y0 <= (Math.max(Y1,Y2)+tolerance) && Y0 >= (Math.min(Y1, Y2)-tolerance))
        {
            return true;
        }
        return false;
        */
    }

    private static boolean allSameSign(double first, double second, double third)
    {
        return (first > 0 && second > 0 && third > 0) || (first < 0 && second < 0 && third < 0);
    }
}
