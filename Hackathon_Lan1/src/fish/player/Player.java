package fish.player;

import fish.define.Define;
import fish.enemy.JellyFish;
import fish.object.FishObject;
import fish.object.FishPlayerObject;
import graphics.Animation;
import singleton.FishEnemyManager;
import singleton.GameManager;
import sound.Music;

import java.awt.*;
/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Player extends FishPlayerObject {

    private void initAnimation() {
        if(level == 1) {
            start = Define.FISH_LEVEL1_START; end = Define.FISH_LEVEL1_END;
            start_eat = Define.FISH_LEVEL1_EAT_START; end_eat = Define.FISH_LEVEL1_EAT_END;
        }
        else if(level == 2) {
            start = Define.FISH_LEVEL2_START; end = Define.FISH_LEVEL2_END;
            start_eat = Define.FISH_LEVEL2_EAT_START; end_eat = Define.FISH_LEVEL2_EAT_END;
        }
        else if(level == 3) {
            start = Define.FISH_LEVEL3_START; end = Define.FISH_LEVEL3_END;
            start_eat = Define.FISH_LEVEL3_EAT_START; end_eat = Define.FISH_LEVEL3_EAT_END;
        }
        animationNormal  = new Animation(start,end,50);
        animationEat  = new Animation(start_eat,end_eat,50);
        switch (level) {
            case 1:
                animationFlip = new Animation(Define.FISH_LEVEL1_FLIP_START, Define.FISH_LEVEL1_FLIP_END, 50);
                break;
            case 2:
                animationFlip = new Animation(Define.FISH_LEVEL2_FLIP_START, Define.FISH_LEVEL2_FLIP_END, 50);
                break;
            case 3:
                animationFlip = new Animation(Define.FISH_LEVEL3_FLIP_START, Define.FISH_LEVEL3_FLIP_END, 50);
                break;
        }
    }

    public Player(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        this.direction = 1;
        this.level = 2;
        this.health = 100;
        initAnimation();
    }

    private int count = 0;
    public void draw(Graphics g) {
        if(checkEat == false) {
            animationNormal.draw(g, positionX + GameManager.getInstance().getLocationX()
                    , positionY + GameManager.getInstance().getLocationY());
        }
        else  {
            animationEat.draw(g, positionX + GameManager.getInstance().getLocationX()
                        , positionY + GameManager.getInstance().getLocationY());
            count++;
            if(count > 17) {
                count = 0;
                checkEat = false;
            }
        }
    }

    private int oldX;
    private int oldY;
    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        if(positionX > oldX + 10) {
            animationNormal.setFlipX(-1);
        }
        else if(positionX < oldX - 10){
            animationNormal.setFlipX(1);
        }
    }

    public void update() {
        super.update();
        this.move(this.positionX, this.positionY);
        if(checkCollisionEnemy()) {
            checkEat = true;
            Music.music("sound2");
        }
        oldX = positionX;   oldY = positionY;
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
                    return true;
                }
                else {
                    //System.out.println("Ca dich an player roi");
//
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public int getWidth() {
        return animationNormal.getWidth();
    }

    @Override
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
        return animationEat;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }
}
