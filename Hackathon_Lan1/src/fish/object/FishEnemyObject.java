package fish.object;

import fish.define.Define;
import fish.enemy.JellyFish;
import singleton.FishEnemyManager;
import singleton.GameManager;

import java.awt.*;

/**
 * Created by VinhNguyenDinh on 03/18/2016.
 */
public abstract class FishEnemyObject extends FishObject {
    private int xVelocity =  1 ;
    private int yVelocity =  1 ;
    public boolean check = true;

    public FishEnemyObject(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
    }

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
//        if(checkCollision()){
//           // System.out.println("");
//        }
    }

    double s1 = 0;
    double s2 = 0;
    private boolean checkCollision() {

        //Rectangle rectPlayer = new Rectangle(this.positionX, this.positionY, animationNormal.getWidth(), animationNormal.getHeight());
        //s1 = animationNormal.getWidth() * animationNormal.getHeight();
        for (FishObject fishObject : FishEnemyManager.getInstance().getVectorFishObject()) {
            Rectangle rectFishObject = new Rectangle(fishObject.getPositionX(), fishObject.getPositionY(), fishObject.getWidth(), fishObject.getHeight());
            for (FishObject fishEnemyObject : FishEnemyManager.getInstance().getVectorFishObject()) {
                Rectangle rectFishEnemyObject = new Rectangle(fishEnemyObject.getPositionX(), fishEnemyObject.getPositionY(), fishEnemyObject.getWidth(), fishEnemyObject.getHeight());
                s1 = fishObject.getWidth() * fishObject.getHeight();
                s2 = fishEnemyObject.getWidth() * fishEnemyObject.getHeight();
                if (rectFishObject.intersects(rectFishEnemyObject) ||rectFishEnemyObject.intersects(rectFishObject) && !(fishObject instanceof JellyFish)) {
                    if (s1 >= s2) {
                        System.out.println("ok an roi");
                        FishEnemyManager.getInstance().getVectorFishObject().remove(FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishObject));
                        FishEnemyManager.getInstance().getVectorFishObject().remove(FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishEnemyObject));
                        return false;
                    } else {
                        FishEnemyManager.getInstance().getVectorFishObject().remove(FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishObject));
                        return true;
                    }
                }
            }
        }
        return false;
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
            if(count > 17) {
                count = 0;
                check = true;
            }
        }
    }
}
