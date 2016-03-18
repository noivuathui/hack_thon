package fish;

import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by TrungSon on 16/03/2016.
 */
public class FlashlightFish extends FishObject  {
    public FlashlightFish(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        animationNormal = new Animation(Define.FLASH_LIGHT_FISH_START, Define.FLASH_LIGHT_FISH_END, 50);
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

    private  int xVelocity =  1 ;
    private  int yVelocity =  1 ;

    public  void setRandomDirection ()  {
        double direction =  Math . random ()* 2.0 * Math . PI ;

        xVelocity =  ( int )  ( speed * Math . sin ( direction ));
        yVelocity =  ( int )  ( speed * Math . cos ( direction ));
        if(xVelocity > 0){
            animationNormal.setFlipX(1);
            checkFlip = true;
        } else {
            animationNormal.setFlipX(-1);
            checkFlip = true;
        }
    }
    public void move() {
        positionX += xVelocity ;
        positionY += yVelocity ;  //added
        if  ( positionX >= Define.RIGHT_WALL )  {
            positionX = Define.RIGHT_WALL;
            setRandomDirection ();
        }
        if  ( positionX <= Define.LEFT_WALL )  {
            positionX = Define.LEFT_WALL ;
            setRandomDirection ();
        }
        if  ( positionY >= Define.DOWN_WALL )  {
            positionY = Define.DOWN_WALL;
            setRandomDirection ();
        }
        if  ( positionY <= Define.UP_WALL )  {
            positionY = Define.UP_WALL;
            setRandomDirection ();
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
