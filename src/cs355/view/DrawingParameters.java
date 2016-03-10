package cs355.view;

import java.awt.*;

/**
 * A simple class to hold the information used for Drawing and viewports.
 * @see ViewportParameters
 * @see ObjectParameters
 */
public class DrawingParameters
{
    public final ViewportParameters viewportParameters;
    public final Graphics2D graphics2D;

    /**
     * Creates a DrawingParameters from the Graphics2D and viewport information.
     * @param graphics2D the graphics 2D.
     * @param viewportParameters the viewport information.
     */
    public DrawingParameters(Graphics2D graphics2D, ViewportParameters viewportParameters)
    {
        this.viewportParameters = viewportParameters;
        this.graphics2D = graphics2D;
    }
}
