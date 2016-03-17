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
    }
    public void draw(Graphics g) {
        animationNormal.draw(g, getPositionX() + GameManager.getInstance().getLocationX(),
                getPositionY() + GameManager.getInstance().getLocationY());


    }
    private  int xVelocity =  1 ;
    private  int yVelocity =  1 ;
    private  static  final  int RIGHT_WALL1 =  500 ;
    private  static  final  int UP_WALL =  1 ;
    private  static  final  int DOWN_WALL =  500 ;
    private  static  final  int LEFT_WALL =  1 ;

    public  void setRandomDirection ()  {
        double direction =  Math . random ()* 2.0 * Math . PI ;

        xVelocity =  ( int )  ( speed * Math . sin ( direction ));
        yVelocity =  ( int )  ( speed * Math . cos ( direction ));
        if(xVelocity > 0){
            animationNormal.setFlipX(-1);
        } else {
            animationNormal.setFlipX(1);
        }
    }
    public void move() {
        positionX += xVelocity ;
        positionY += yVelocity ;  //added
        if  ( positionX >= RIGHT_WALL1 )  {
            positionX = RIGHT_WALL1 ;
            setRandomDirection ();
        }
        if  ( positionX <= LEFT_WALL )  {
            positionX = LEFT_WALL ;
            setRandomDirection ();
        }
        if  ( positionY >= DOWN_WALL )  {
            positionY = DOWN_WALL ;
            setRandomDirection ();
        }
        if  ( positionY <= UP_WALL )  {
            positionY = UP_WALL ;
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
