package cs355.view.drawing.util;

import cs355.model.drawing.Shape;
import cs355.view.drawing.DrawableNullShape;
import cs355.view.drawing.DrawableShape;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Factory to get the DrawableShape corresponding to a Shape.
 *
 * @see DrawableShape
 * @see Shape
 */
public class DrawableShapeFactory
{
    private static final Logger LOGGER = Logger.getLogger(DrawableShapeFactory.class.getName());

    /**
     * Determines the DrawableShape associated with the given Shape.
     *
     * @param shape the shape.
     * @return the DrawableShape associated with the given Shape.
     */
    public static DrawableShape createDrawableShape(Shape shape)
    {
        String className = shape.getClass().getSimpleName();
        try
        {
            Constructor constructor = Class.forName("cs355.view.drawing.Drawable" + className).getConstructor(Shape.class);
            LOGGER.fine("Created DrawableShape");
            return (DrawableShape) constructor.newInstance(shape);
        } catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Failed to create DrawableShape", e);
            return new DrawableNullShape(Color.WHITE);
        }
    }
}
