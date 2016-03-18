package graphics;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by hungtran on 3/17/16.
 */
public abstract class Scene implements KeyListener{
    BufferedImage bufferedImage;
    public abstract void draw(Graphics g);
}
