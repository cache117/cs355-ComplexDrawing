package cs355.solution;

import cs355.GUIFunctions;
import cs355.controller.DrawingController;
import cs355.view.DrawingViewer;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355
{
    /**
     * This is where it starts.
     *
     * @param args = the command line arguments
     */
    public static void main(String[] args)
    {
        DrawingController controller = new DrawingController();
        controller.setView(new DrawingViewer(controller.getScene()));
        // Fill in the parameters below with your controller and view.
        GUIFunctions.createCS355Frame(controller, controller.getView());
        init();
    }

    @SuppressWarnings("FeatureEnvy")
    private static void init()
    {
        final int VIEWPORT_SIZE = 512, BAR_INIT_POSITION = 1024;
        GUIFunctions.setZoomText(1);

        GUIFunctions.setHScrollBarKnob(VIEWPORT_SIZE);
        GUIFunctions.setHScrollBarPosit(BAR_INIT_POSITION - (VIEWPORT_SIZE / 2));

        GUIFunctions.setVScrollBarKnob(VIEWPORT_SIZE);
        GUIFunctions.setVScrollBarPosit(BAR_INIT_POSITION - (VIEWPORT_SIZE / 2));

        GUIFunctions.refresh();
    }
}
