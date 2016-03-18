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
        animationNormal.setFlipX(-1);
        animationFlip = new Animation(Define.FISH_ENEMY_SMALL_FLIP_START,Define.FISH_ENEMY_SMALL_FLIP_END,50);
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
