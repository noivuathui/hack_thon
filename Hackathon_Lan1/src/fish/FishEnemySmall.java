package fish;

import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by noivu on 3/15/2016.
 */
public class FishEnemySmall extends FishObject {
    int f;

    private Animation anim;
    public FishEnemySmall(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        anim = new Animation(310,340,50);

    }
    public void draw(Graphics g) {
        anim.draw(g, getPositionX() + GameManager.getInstance().getLocationX(),
                getPositionY() + GameManager.getInstance().getLocationY());
    }
    //ham move()
    private  int xVelocity =  1 ;
    private  int yVelocity =  1 ;
    private  static  final  int RIGHT_WALL =  500 ;
    private  static  final  int UP_WALL =  1 ;
    private  static  final  int DOWN_WALL =  500 ;
    private  static  final  int LEFT_WALL =  1 ;

    public  void setRandomDirection ()  {
        double direction =  Math . random ()* 3.0 * Math . PI ;

        xVelocity =  ( int )  ( speed * Math . cos ( direction ));
        yVelocity =  ( int )  ( speed * Math . sin ( direction ));
        if(xVelocity > 0){
            anim.setFlipX(-1);
        } else {
            anim.setFlipX(1);
        }
    }
    public void move() {
       //added
            positionX += xVelocity;
            positionY += yVelocity;
            if (positionX >= RIGHT_WALL) {
                positionX = RIGHT_WALL;
                setRandomDirection();
            }
            if (positionX <= LEFT_WALL) {
                positionX = LEFT_WALL;
                setRandomDirection();
            }
            if (positionY >= DOWN_WALL) {
                positionY = DOWN_WALL;
                setRandomDirection();
            }
            if (positionY <= UP_WALL) {
                positionY = UP_WALL;
                setRandomDirection();
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
