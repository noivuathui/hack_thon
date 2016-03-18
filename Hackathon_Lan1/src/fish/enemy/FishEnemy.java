package fish.enemy;

import fish.define.Define;
import fish.object.FishEnemyObject;
import graphics.Animation;
import singleton.FishEnemyManager;
import singleton.GameManager;
import singleton.PlayerManager;

import java.awt.*;

/**
 * Created by Anh on 3/14/2016.
 */
public class FishEnemy extends FishEnemyObject {
    private int direction; // 1.Left - 2.Right

    private boolean check = true;


    public FishEnemy(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        delta = 50;
        animationNormal = new Animation(Define.FISH_ENEMY_START,Define.FISH_ENEMY_END,delta);
        animationNormal.setFlipX(-1);
        animationFlip = new Animation(Define.FISH_ENEMY_FLIP_START,Define.FISH_ENEMY_FLIP_END,delta);
    }

//    public boolean checkCollision() {
//        Rectangle rectEnemy = new Rectangle(positionX, positionY, sprite.getWidth(), sprite.getHeight());
//
//        Rectangle rectPlay =
//                new Rectangle(PlayerManager.getInstance().getPlayer().getPositionX()
//                        , PlayerManager.getInstance().getPlayer().getPositionY()
//                        , PlayerManager.getInstance().getPlayer().getWidth()
//                        , PlayerManager.getInstance().getPlayer().getHeight());
//        return rectEnemy.intersects(rectPlay);
//    }


    @Override
    public int getWidth() {
        return animationNormal.getWidth();
    }

    @Override
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
