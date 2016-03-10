package cs355.view.drawing.state;

import cs355.controller.DrawingController;
import cs355.model.drawing.CS355Drawing;

/**
 * State interface.
 */
public interface State
{
    /**
     * @param model
     */
    void stateChanged(CS355Drawing model);

    /**
     * @param controller
     */
    void lineButtonHit(DrawingController controller);

    void squareButtonHit(DrawingController controller);

    void rectangleButtonHit(DrawingController controller);

    void circleButtonHit(DrawingController controller);

    void ellipseButtonHit(DrawingController controller);

    void triangleButtonHit(DrawingController controller);

    void selectButtonHit(DrawingController controller);

    void deleteShape(CS355Drawing model);

    void moveShapeForward(CS355Drawing model);

    void moveShapeBackward(CS355Drawing model);

    void moveShapeToFront(CS355Drawing model);

    void moveShapeToBack(CS355Drawing model);
}
