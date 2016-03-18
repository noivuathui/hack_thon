package fish.object;

import fish.define.Define;
import fish.enemy.JellyFish;
import singleton.FishEnemyManager;

import java.awt.*;

import java.awt.*;

/**
 * Created by VinhNguyenDinh on 03/18/2016.
 */
public abstract class FishEnemyObject extends FishObject {
    private int xVelocity = 1;
    private int yVelocity = 1;
    public boolean check = true;
    protected boolean checkEat = false;
    protected boolean checkFlip = false;

    public FishEnemyObject(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
    }

    public void setRandomDirection() {
        double direction = Math.random() * 3.0 * Math.PI;
        xVelocity = (int) (speed * Math.cos(direction));
        yVelocity = (int) (speed * Math.sin(direction));
        if (xVelocity > 0) {
            animationNormal.setFlipX(-1);
            check = false;
        } else {
            animationNormal.setFlipX(1);
        }
    }

    public void move() {
        positionX += xVelocity;
        positionY += yVelocity;  //added
        if (positionX >= Define.RIGHT_WALL) {
            positionX = Define.RIGHT_WALL;
            setRandomDirection();
        }
        if (positionX <= Define.LEFT_WALL) {
            positionX = Define.LEFT_WALL;
            setRandomDirection();
        }
        if (positionY >= Define.DOWN_WALL) {
            positionY = Define.DOWN_WALL;
            setRandomDirection();
        }
        if (positionY <= Define.UP_WALL) {
            positionY = Define.UP_WALL;
            setRandomDirection();
        }
    }

    int size = FishEnemyManager.getInstance().getVectorFishObject().size();
    FishObject f1, f2;
    double s1, s2;

    private boolean checkCollision() {
        double s1, s2;
        for (int i = 0; i < size - 1; i++) {

            f1 = FishEnemyManager.getInstance().getVectorFishObject().get(i);
            s1 = f1.getAnimationNormal().getWidth() * f1.getAnimationNormal().getHeight();
            Rectangle rect1 = new Rectangle(f1.getPositionX(), f1.getPositionY(), f1.getAnimationNormal().getWidth(), f1.getAnimationNormal().getHeight());
            for (int j = i + 1; j < size; j++) {
                f2 = FishEnemyManager.getInstance().getVectorFishObject().get(j);
                s2 = f2.getAnimationNormal().getWidth() * f2.getAnimationNormal().getHeight();
                Rectangle rect2 = new Rectangle(f2.getPositionX(), f2.getPositionY(), f2.getAnimationNormal().getWidth(), f2.getAnimationNormal().getHeight());
                if (rect1.intersects(rect2)) {
                    if (s1 > s2) {
                        FishEnemyManager.getInstance().getVectorFishObject().remove(j);
                    } else if (s2 > s1) {
                        FishEnemyManager.getInstance().getVectorFishObject().remove(i);
                    }
                    size--;
                    return true;
                }
            }
        }
        return false;
    }
    public void update() {
        this.move();
//        if(checkCollision()) {
//
//        }
    }
}
