package cs355.view;

import cs355.GUIFunctions;
import cs355.model.scene.CS355Scene;
import cs355.model.scene.Point3D;

import java.util.logging.Logger;

import static java.awt.event.KeyEvent.*;
import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * A Virtual Camera for rendering scenes.
 */
public class VirtualCamera
{
    private static final Logger LOGGER = Logger.getLogger(VirtualCamera.class.getName());
    //private static final float ASPECT = (LWJGLSandbox.DISPLAY_WIDTH / LWJGLSandbox.DISPLAY_HEIGHT);
    private static final float UNIT = 1.0f;
    private static final float FOVY = 60f, CLIPPING = FOVY / 2.0f;
    private static final float Z_NEAR = 1, Z_FAR = 200;

    private final Point3D cameraLocation;
    private double rotation;
    private final float movementAmount;
    private boolean is3DEnabled;

    public VirtualCamera()
    {
        cameraLocation = new Point3D(0, 0, 0);
        rotation = 0.0f;
        movementAmount = UNIT;
        is3DEnabled = false;
    }

    /**
     * Moves the camera based on the given key press.
     * @param key the key that was pressed.
     */
    public void keyPressed(int key)
    {
        switch (key)
        {
            case VK_W:
                moveForward();
                break;
            case VK_A:
                moveLeft();
                break;
            case VK_S:
                moveBackward();
                break;
            case VK_D:
                moveRight();
                break;
            case VK_Q:
                rotateLeft();
                break;
            case VK_E:
                rotateRight();
                break;
            case VK_R:
                moveUp();
                break;
            case VK_F:
                moveDown();
                break;
            default:
                break;
        }
    }

    private void moveForward()
    {
        LOGGER.fine("Moving Forward");
        cameraLocation.x -= movementAmount * sin(rotation);
        cameraLocation.z += movementAmount * cos(rotation);
    }

    private void moveBackward()
    {
        LOGGER.fine("Moving Backward");
        cameraLocation.x += movementAmount * sin(rotation);
        cameraLocation.z -= movementAmount * cos(rotation);
    }

    private void moveLeft()
    {
        LOGGER.fine("Moving Left");
        cameraLocation.x -= movementAmount * cos(rotation);
        cameraLocation.z -= movementAmount * sin(rotation);
    }

    private void moveRight()
    {
        LOGGER.fine("Moving Right");
        cameraLocation.x += movementAmount * cos(rotation);
        cameraLocation.z += movementAmount * sin(rotation);
    }

    private void moveUp()
    {
        LOGGER.fine("Moving Up");
        cameraLocation.y += movementAmount;
    }

    private void moveDown()
    {
        LOGGER.fine("Moving Down");
        cameraLocation.y -= movementAmount;
    }

    private void rotateRight()
    {
        LOGGER.fine("Rotating Right");
        rotation -= (movementAmount / 3.0f);
    }

    private void rotateLeft()
    {
        LOGGER.fine("Rotating Left");
        rotation += (movementAmount / 3.0f);
    }

    public boolean is3DEnabled()
    {
        return is3DEnabled;
    }

    public void toggleCameraMovementEnabled()
    {
        is3DEnabled = !is3DEnabled;
    }

    public void updateScene(CS355Scene scene)
    {
        scene.setCameraPosition(cameraLocation);
        scene.setCameraRotation(rotation);
    }
}
