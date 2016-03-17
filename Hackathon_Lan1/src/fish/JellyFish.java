package fish;

import graphics.Animation;
import main.GameObject;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by noivu on 3/15/2016.
 */
public class JellyFish extends FishObject {
    public JellyFish(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        animationNormal = new Animation(Define.JELLY_FISH_START,Define.JELLY_FISH_END,50);
    }
    public void draw(Graphics g) {
        animationNormal.draw(g, getPositionX() + GameManager.getInstance().getLocationX(),
                getPositionY() + GameManager.getInstance().getLocationY());
    }

    public void move() {
        setPositionY(getPositionY()- speed);
        if (this.positionY <= 0) {
            speed = -speed;
        }
        if (this.positionY >= 600) {
            speed = -speed;
        }
    }

    public void update(){
        this.move();
    }

    public int getWidth() {
        return animationNormal.getWidth();
    }

    public int getHeight() {
        return animationNormal.getHeight();
    }

    @Override
    public Animation getAnimationNormal() {
        return animationNormal;
    }

    @Override
    public Animation getAnimationFlip() {
        return null;
    }

    @Override
    public Animation getAnimationEat() {
        return null;
    }
}
