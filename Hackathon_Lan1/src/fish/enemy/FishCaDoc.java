package fish.enemy;

import fish.define.Define;
import fish.object.FishEnemyObject;
import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by noivu on 3/15/2016.
 */
public class FishCaDoc extends FishEnemyObject {
    private boolean check = true;

    public FishCaDoc(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        animationNormal = new Animation(Define.FISH_CA_DOC_START,Define.FISH_CA_DOC_END,50);
        animationFlip = new Animation(Define.FISH_CA_DOC_FLIP_START,Define.FISH_CA_DOC_FLIP_END,50);
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
