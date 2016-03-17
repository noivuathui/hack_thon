package fish;

import graphics.Animation;
import main.GameObject;
import singleton.FishEnemyManager;
import singleton.PlayerManager;

import java.awt.*;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public abstract class FishObject extends GameObject {
    protected int speed;
    protected Animation animationNormal;
    protected Animation animationFlip;
    protected Animation animationEat;

    FishObject(int positionX, int positionY, int speed) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite,positionX,positionY,null);
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Animation getAnimationNormal();

    public abstract Animation getAnimationFlip();

    public abstract Animation getAnimationEat();
}
