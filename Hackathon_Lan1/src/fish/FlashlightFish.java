package fish;

import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by TrungSon on 16/03/2016.
 */
public class FlashlightFish extends FishObject  {
    private Animation anim;

    public FlashlightFish(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();


    }
    private void initAnimation(){
        anim = new Animation(Define.FLASH_LIGHT_FISH_START, Define.FLASH_LIGHT_FISH_END, 50);
    }
    public void draw(Graphics g) {
        anim.draw(g, getPositionX() + GameManager.getInstance().getLocationX(),
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
            anim.setFlipX(-1);
        } else {
            anim.setFlipX(1);
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
        return anim.getWidth();
    }

    public int getHeight() {
        return anim.getHeight();
    }

}
