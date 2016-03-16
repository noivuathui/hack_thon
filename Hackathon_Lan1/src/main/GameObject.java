package main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public abstract class GameObject {
    protected int positionX;
    protected int positionY;
    protected BufferedImage sprite;

    public abstract void update();

    public abstract void draw(Graphics g);

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
}
