package Plant;

import main.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by noivu on 3/18/2016.
 */
public abstract class PlantObject extends GameObject{

    private int width;
    private int height;

    public PlantObject(String a,int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        int i = 0;
        String str = String.format("Resources/image %d.png", i);
        try {
            BufferedImage img = ImageIO.read(new File(str));
            width = img.getWidth();
            height = img.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(sprite, x, y, null);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
