package fish.enemy;

import fish.define.Define;
import fish.object.FishEnemyObject;
import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by TrungSon on 16/03/2016.
 */
public class FlashlightFish extends FishEnemyObject {
    public FlashlightFish(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        animationNormal = new Animation(Define.FLASH_LIGHT_FISH_START, Define.FLASH_LIGHT_FISH_END, 50);
        animationNormal.setFlipX(-1);
        animationFlip = new Animation(Define.FLASH_LIGHT_FISH_FLIP_START, Define.FLASH_LIGHT_FISH_FLIP_END, 50);
        animationEat = new Animation(Define.FLASH_LIGHT_FISH_EAT_START, Define.FLASH_LIGHT_FISH_EAT_END, 50);
    }

    private int count = 0;
    public void draw(Graphics g) {
        if(checkEat == false && checkFlip == false) {
            animationNormal.draw(g, positionX + GameManager.getInstance().getLocationX()
                    , positionY + GameManager.getInstance().getLocationY());
        }
        else {
            if(checkEat == true) {
                animationEat.draw(g, positionX + GameManager.getInstance().getLocationX()
                        , positionY + GameManager.getInstance().getLocationY());
            }
            else if(checkFlip == true) {
                animationFlip.draw(g, positionX + GameManager.getInstance().getLocationX()
                        , positionY + GameManager.getInstance().getLocationY());
            }
            count++;
            if(count > 17) {
                count = 0;
                //if(checkEat)
                    checkEat = false;
                //if(checkFlip)
                    checkFlip = false;
            }
        }
    }

    @Override
    public void move() {
        super.move();
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
