package Menu;

import main.GameObject;
import main.GameWindow;

import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by noivu on 3/17/2016.
 */
public abstract class Scene extends GameObject implements MouseListener {
    public abstract void draw(Graphics g);

}
