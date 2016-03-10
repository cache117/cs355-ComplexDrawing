package cs355.view.drawing.state;

import cs355.model.drawing.CS355Drawing;

import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 2/19/2016.
 */
public interface MouseListener
{
    /**
     * Changes the shape that can be stored in the model after clicking a mouseClicked event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    void mouseClicked(Point2D.Double point, CS355Drawing model);

    /**
     * Changes the shape that can be stored in the model after clicking a mousePressed event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    void mousePressed(Point2D.Double point, CS355Drawing model);

    /**
     * Changes the shape that can be stored in the model after clicking a mouseReleased event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    void mouseReleased(Point2D.Double point, CS355Drawing model);

    /**
     * Changes the shape that can be stored in the model after clicking a mouseDragged event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    void mouseDragged(Point2D.Double point, CS355Drawing model);
}
