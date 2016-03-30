package cs355.model.image;

import java.awt.image.BufferedImage;

/**
 * Created by cstaheli on 3/30/2016.
 */
public class DrawingImage extends CS355Image
{
    @Override
    public BufferedImage getImage()
    {
        return null;
    }

    @Override
    public void edgeDetection()
    {
        this.notifyObservers();
    }

    @Override
    public void sharpen()
    {
        this.notifyObservers();
    }

    @Override
    public void medianBlur()
    {
        this.notifyObservers();
    }

    @Override
    public void uniformBlur()
    {
        this.notifyObservers();
    }

    @Override
    public void grayscale()
    {
        this.notifyObservers();
    }

    @Override
    public void contrast(int amount)
    {
        this.notifyObservers();
    }

    @Override
    public void brightness(int amount)
    {
        this.notifyObservers();
    }

    @Override
    public void notifyObservers()
    {
        super.setChanged();
        super.notifyObservers();
    }
}
