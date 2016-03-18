package fish.enemy;

import fish.define.Define;
import fish.object.FishEnemyObject;
import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by noivu on 3/15/2016.
 */
public class FishEnemySmall extends FishEnemyObject {
    private boolean check = true;

    public FishEnemySmall(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        animationNormal = new Animation(Define.FISH_ENEMY_SMALL_START,Define.FISH_ENEMY_SMALL_END,50);
        animationFlip = new Animation(Define.FISH_ENEMY_SMALL_FLIP_START,Define.FISH_ENEMY_SMALL_FLIP_END,50);
    }

    private int count = 0;
    public void draw(Graphics g) {
        if(check) {
            animationNormal.draw(g, getPositionX() + GameManager.getInstance().getLocationX(),
                    getPositionY() + GameManager.getInstance().getLocationY());
        }
        else {
            animationFlip.draw(g, positionX + GameManager.getInstance().getLocationX()
                    , positionY + GameManager.getInstance().getLocationY());
            count++;
            if(count > 20) {
                count = 0;
                check = true;
            }
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
        return animationFlip;
    }

    @Override
    public Animation getAnimationEat() {
        return null;
    }
}
