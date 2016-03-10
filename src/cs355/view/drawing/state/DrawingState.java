package cs355.view.drawing.state;

import cs355.controller.DrawingController;
import cs355.model.drawing.CS355Drawing;
import cs355.view.drawing.DrawableNullShape;
import cs355.view.drawing.DrawableShape;

import java.awt.*;

/**
 * The Basic state for drawing. All states that have the same implementation use the implementation here.
 * Also gives empty method bodies for some methods to specify that nothing happens usually.
 */
public abstract class DrawingState implements MouseListener, State
{
    private DrawableShape drawableShape;
    private boolean shapeSelected;

    /**
     * Creates a default DrawingState.
     */
    DrawingState()
    {
        super();
        shapeSelected = false;
        drawableShape = new DrawableNullShape(Color.WHITE);
    }

    /**
     * Copies the relevant info from the currentState. Used to change state.
     *
     * @param currentState the currentState/previous state before this.
     */
    DrawingState(DrawingState currentState)
    {
        this();
        this.drawableShape = buildDrawableShape(currentState.getColor());
    }

    /**
     * @param controller
     */
    @Override
    public void lineButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new LineState(this));
    }

    public void squareButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new SquareState(this));
    }

    @Override
    public void rectangleButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new RectangularState(this));
    }

    @Override
    public void circleButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new CircleState(this));
    }

    @Override
    public void ellipseButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new EllipseState(this));
    }

    @Override
    public void triangleButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new TriangleState(this));
    }

    @Override
    public void selectButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new SelectionState());
    }

    @Override
    public void deleteShape(CS355Drawing model)
    {

    }

    @Override
    public void moveShapeForward(CS355Drawing model)
    {

    }

    @Override
    public void moveShapeBackward(CS355Drawing model)
    {

    }

    @Override
    public void moveShapeToFront(CS355Drawing model)
    {

    }

    @Override
    public void moveShapeToBack(CS355Drawing model)
    {

    }

    @Override
    public void stateChanged(CS355Drawing model)
    {

    }

    public Color getColor()
    {
        return drawableShape.getColor();
    }

    /**
     * Sets the color of the model, and, if a shape is selected, change the color of the selected state.
     * @param color the color to change to.
     * @param model the model where shapes are stored.
     */
    public void setColor(Color color, CS355Drawing model)
    {
        drawableShape.setColor(color);
    }

    public DrawableShape getDrawableShape()
    {
        return drawableShape;
    }

    public void setDrawableShape(DrawableShape drawableShape)
    {
        this.drawableShape = drawableShape;
    }

    public boolean isShapeSelected()
    {
        return shapeSelected;
    }

    public void setIsShapeSelected(boolean shapeSelected)
    {
        this.shapeSelected = shapeSelected;
    }

    protected abstract DrawableShape buildDrawableShape(Color color);
}
