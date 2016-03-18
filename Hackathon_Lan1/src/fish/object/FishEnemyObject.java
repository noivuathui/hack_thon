package fish.object;

import fish.define.Define;
import fish.enemy.JellyFish;
import singleton.FishEnemyManager;

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


    private boolean checkCollisionEnemy() {
        double s1, s2;
        Rectangle rectPlayer = new Rectangle(this.positionX, this.positionY, animationNormal.getWidth(), animationNormal.getHeight());
        s1 = animationNormal.getWidth() * animationNormal.getHeight();
        for (FishObject fishObject : FishEnemyManager.getInstance().getVectorFishObject()) {
            Rectangle rectFishObject = new Rectangle(fishObject.getPositionX(), fishObject.getPositionY(), fishObject.getWidth(), fishObject.getHeight());
            s2 = fishObject.getWidth() * fishObject.getHeight();
            if(rectPlayer.intersects(rectFishObject)&& !(fishObject instanceof JellyFish)) {
                if (s1 >= s2 ) {
                    FishEnemyManager.getInstance().getVectorFishObject().remove(FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishObject));
                    return false;
                }
                else {
                    FishEnemyManager.getInstance().getVectorFishObject().remove(FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishObject));
                    return true;
                }
            }
        }
        return false;
    }
}
