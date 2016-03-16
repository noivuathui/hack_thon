package fish;

import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by TrungSon on 16/03/2016.
 */
public class FishEnemy1 extends FishObject  {

    private int direction;
    private int start1;
    private int end1;
    private int delta1;
    private Animation anim;

    public FishEnemy1(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();


    }
    private void initAnimation(){

        start1= 310;
        end1=353;
        delta1=100;
        anim=new Animation(start1,end1,delta1);
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
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

}
