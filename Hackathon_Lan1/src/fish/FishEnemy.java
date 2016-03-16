package fish;

import graphics.Animation;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by Anh on 3/14/2016.
 */
public class FishEnemy extends FishObject{
    private int direction; // 1.Left - 2.Right
    private int start;
    private int end;
    private int delta;
    private Animation anim;
    private Animation anim_flip;
    private boolean check = true;


    public FishEnemy(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        initAnimation();
    }

    private void initAnimation(){
        delta = 50;

        start = Define.FISH_ENEMY_1_START;
        end = Define.FISH_ENEMY_1_END;
        anim = new Animation(start,end,delta);

        start = Define.FISH_ENEMY_1_FLIP_START;
        end = Define.FISH_ENEMY_1_FLIP_END;
        anim_flip = new Animation(start,end,delta);
    }

    private int count = 0;
    public void draw(Graphics g) {
        if(check) {
            anim.draw(g, getPositionX() + GameManager.getInstance().getLocationX(),
                    getPositionY() + GameManager.getInstance().getLocationY());
        }
        else {
            anim_flip.draw(g, positionX + GameManager.getInstance().getLocationX()
                    , positionY + GameManager.getInstance().getLocationY());
            count++;
            if(count > 20) {
                count = 0;
                check = true;
            }
        }
    }

    //ham move()

    private int xVelocity =  1 ;
    private int yVelocity =  1 ;
    private static  final  int RIGHT_WALL =  500 ;
    private static  final  int UP_WALL =  1 ;
    private static  final  int DOWN_WALL =  500 ;
    private static  final  int LEFT_WALL =  1 ;

    public  void setRandomDirection ()  {
        double direction =  Math . random ()* 3.0 * Math . PI ;
        xVelocity =  ( int )  ( speed * Math . cos ( direction ));
        yVelocity =  ( int )  ( speed * Math . sin ( direction ));
        if(xVelocity > 0){
            anim.setFlipX(-1);
            check = false;
        } else {
            anim.setFlipX(1);
        }
    }
    public void move() {
        positionX += xVelocity ;
        positionY += yVelocity ;  //added
        if  ( positionX >= RIGHT_WALL )  {
              positionX = RIGHT_WALL ;
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
