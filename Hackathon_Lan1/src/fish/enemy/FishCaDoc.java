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

    //ham move()
    private  int xVelocity =  1 ;
    private  int yVelocity =  1 ;
    public  void setRandomDirection ()  {
        double direction =  Math . random ()* 3.0 * Math . PI ;
        xVelocity =  ( int )  ( speed * Math . cos ( direction ));
        yVelocity =  ( int )  ( speed * Math . sin ( direction ));
        if(xVelocity > 0){
            animationNormal.setFlipX(-1);
            check = false;
        } else {
            animationNormal.setFlipX(1);
        }
    }

    public void move() {
        positionX += xVelocity ;
        positionY += yVelocity ;  //added
        if  ( positionX >= Define.RIGHT_WALL )  {
            positionX = Define.RIGHT_WALL ;
            setRandomDirection ();
        }
        if  ( positionX <= Define.LEFT_WALL )  {
            positionX = Define.LEFT_WALL ;
            setRandomDirection ();
        }
        if  ( positionY >= Define.DOWN_WALL )  {
            positionY = Define.DOWN_WALL ;
            setRandomDirection ();
        }
        if  ( positionY <= Define.UP_WALL )  {
            positionY = Define.UP_WALL ;
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
        return animationFlip;
    }

    @Override
    public Animation getAnimationEat() {
        return null;
    }
}
