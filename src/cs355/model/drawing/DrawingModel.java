package cs355.model.drawing;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cstaheli on 1/7/2016.
 */
public class DrawingModel extends CS355Drawing
{
    private List<Shape> shapes;

    public DrawingModel()
    {
        this.shapes = new LinkedList<>();
    }

    /* begin CS355Drawing methods */
    @Override
    public Shape getShape(int index)
    {
        if (index < shapes.size())
            return shapes.get(index);
        return new NullShape();
    }

    public int getIndexOfShape(Shape shape)
    {
        int shapeIndex = -1;
        for (int i = 0; i <shapes.size(); ++i)
        {
            if (shapes.get(i).equals(shape))
            {
                shapeIndex = i;
                break;
            }
        }
        return shapeIndex;
    }

    /**
     * Sets the shape that the given index.
     *
     * @param index the index of the shape to set.
     * @param shape the new shape.
     */
    public void setShape(int index, Shape shape)
    {
        shapes.set(index, shape);
        this.notifyObservers();
    }

    @Override
    public int addShape(Shape s)
    {
        shapes.add(0, s);
        this.notifyObservers();
        return 0;
    }

    @Override
    public void deleteShape(int index)
    {
        if (index != -1)
        {
            shapes.remove(index);
            this.notifyObservers();
        }
    }

    @Override
    public void moveToFront(int index)
    {
        Shape shape = shapes.remove(index);
        shapes.add(0, shape);
        this.notifyObservers();
    }

    @Override
    public void movetoBack(int index)
    {
        Shape shape = shapes.remove(index);
        shapes.add(shape);
        this.notifyObservers();
    }

    @Override
    public void moveForward(int index)
    {
        if (index != 0)
        {
            Shape shape = shapes.remove(index);
            shapes.add(index - 1, shape);
            this.notifyObservers();
        }

    }

    @Override
    public void moveBackward(int index)
    {
        if (index != shapes.size() - 1)
        {
            Shape shape = shapes.remove(index);
            shapes.add(index + 1, shape);
            this.notifyObservers();
        }
    }

    @Override
    public List<Shape> getShapes()
    {
        return shapes;
    }

    @Override
    public List<Shape> getShapesReversed()
    {
        List<Shape> reversedShapes = new ArrayList<>(this.shapes);
        Collections.reverse(reversedShapes);
        return reversedShapes;
    }

    @Override
    public void setShapes(List<Shape> shapes)
    {
        this.shapes = shapes;
        this.notifyObservers();
    }
    /* end CS355Drawing methods */

    @Override
    public void notifyObservers(Object object)
    {
        this.setChanged();
        super.notifyObservers(object);
    }

    @Override
    public void notifyObservers()
    {
        this.notifyObservers(null);
    }
}
