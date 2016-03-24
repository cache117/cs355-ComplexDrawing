package cs355.view;

import cs355.GUIFunctions;
import cs355.controller.DrawingController;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;
import cs355.model.scene.CS355Scene;
import cs355.view.drawing.DrawableNullShape;
import cs355.view.drawing.DrawableShape;
import cs355.view.drawing.util.DrawableShapeFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * The View in the MVC model. Takes care of all the drawing when the Model changes.
 *
 * @see Observer
 * @see Observable
 * @see DrawingModel
 * @see DrawingController
 */
public class DrawingViewer implements ViewRefresher
{
    private static final Logger LOGGER = Logger.getLogger(DrawingViewer.class.getName());

    private DrawingModel model;

    private Point2D.Double viewportUpperLeft;
    private double scalingFactor;
    private int hBarPosition;
    private int vBarPosition;
    private CS355Scene scene;
    private static final double VIEWPORT_SIZE = 512, HALF_WORLD_SIZE = 1024, MAX_SCALING_FACTOR = 4, MIN_SCALING_FACTOR = .25;
    private VirtualCamera camera;
    // private List<Shape> specificUpdatedShapes;

    /**
     * Creates a new Viewer.
     * @param scene the 3D scene to render the viewer with.
     */
    public DrawingViewer(CS355Scene scene)
    {
        model = new DrawingModel();
        scalingFactor = 1.0;
        viewportUpperLeft = new Point2D.Double(HALF_WORLD_SIZE, HALF_WORLD_SIZE);
        this.scene = scene;
        camera = new VirtualCamera();
    }

    /* begin ViewRefresher methods */
    @Override
    public void refreshView(Graphics2D graphics2D)
    {
        //Draw selection handles last
        List<Shape> shapes = model.getShapesReversed();

        DrawableShape selectedShape = new DrawableNullShape(Color.WHITE);
        DrawingParameters drawingParameters = new DrawingParameters(graphics2D, new ViewportParameters(viewportUpperLeft, scalingFactor));
        for (Shape shape : shapes)
        {
            DrawableShape drawableShape = DrawableShapeFactory.createDrawableShape(shape);
            if (shape.isSelected())
            {
                selectedShape = drawableShape;
            }
            drawableShape.draw(drawingParameters);
        }
        selectedShape.drawOutline(drawingParameters);

    }

    /* end ViewRefresher methods */

    /* begin Observer methods */
    @Override
    public void update(Observable o, Object specificShapes)
    {
        model = (DrawingModel) o;
        GUIFunctions.refresh();
    }
    /* end Observer methods */

    public void zoomInButtonHit()
    {
        if (scalingFactor < MAX_SCALING_FACTOR)
        {
            scalingFactor *= 2.0;
            Point2D.Double oldUpperLeft = (Point2D.Double) viewportUpperLeft.clone();
            viewportUpperLeft = new Point2D.Double(oldUpperLeft.x + (VIEWPORT_SIZE / scalingFactor), oldUpperLeft.y + (VIEWPORT_SIZE / scalingFactor));
            doZoom();
        }
    }

    public void zoomOutButtonHit()
    {
        if (scalingFactor > MIN_SCALING_FACTOR)
        {
            scalingFactor /= 2.0;
            Point2D.Double oldUpperLeft = (Point2D.Double) viewportUpperLeft.clone();
            viewportUpperLeft = new Point2D.Double(oldUpperLeft.x - (VIEWPORT_SIZE / scalingFactor), oldUpperLeft.y - (VIEWPORT_SIZE / scalingFactor));
            doZoom();
        }
    }

    public void hScrollbarChanged(int value)
    {
        if (value != 0)
            viewportUpperLeft.x = value;
        GUIFunctions.printf("hScroll: %d", value);
        GUIFunctions.refresh();
    }

    public void vScrollbarChanged(int value)
    {
        if (value != 0)
            viewportUpperLeft.y = value;
        GUIFunctions.printf("vScroll: %d", value);
        GUIFunctions.refresh();
    }

    private void doZoom()
    {
        GUIFunctions.setZoomText(scalingFactor);
        int hBarSize = (int) (VIEWPORT_SIZE / scalingFactor);
        GUIFunctions.setHScrollBarKnob(hBarSize);
        GUIFunctions.setHScrollBarPosit((int) viewportUpperLeft.x);

        int vBarSize = (int) (VIEWPORT_SIZE / scalingFactor);
        GUIFunctions.setVScrollBarKnob(vBarSize);
        GUIFunctions.setVScrollBarPosit((int) viewportUpperLeft.y);

        GUIFunctions.refresh();
    }

    public Point2D.Double getViewportUpperLeft()
    {
        return viewportUpperLeft;
    }

    public double getScalingFactor()
    {
        return scalingFactor;
    }

    public void keyPressed(Iterator<Integer> iterator)
    {
        if (camera.isCameraMovementEnabled())
        {
            while (iterator.hasNext())
            {
                camera.keyPressed(iterator.next());
            }
            camera.updateScene(scene);
        }
    }

    public void toggle3DModelDisplay()
    {
        camera.toggleCameraMovementEnabled();
    }
}
