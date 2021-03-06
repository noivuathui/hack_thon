package fish.object;

import graphics.Animation;
import main.GameObject;

import java.awt.*;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public abstract class FishObject extends GameObject {
    protected int speed;
    protected Animation animationNormal;
    protected Animation animationFlip;
    protected Animation animationEat;
    protected int level;
    protected int delta;

    FishObject(int positionX, int positionY, int speed) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
    }

    @Override
    public void update() {

    }

    @Override
    public abstract void draw(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Animation getAnimationNormal();

    public abstract Animation getAnimationFlip();

    public abstract Animation getAnimationEat();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
