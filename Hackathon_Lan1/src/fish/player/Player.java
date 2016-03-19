package fish.player;

import com.sun.org.apache.xpath.internal.SourceTree;
import fish.define.Define;
import fish.enemy.JellyFish;
import fish.object.FishObject;
import fish.object.FishPlayerObject;
import graphics.Animation;
import graphics.GamePlayScene;
import graphics.Topic;
import singleton.FishEnemyManager;
import singleton.GameManager;
import sound.Music;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Player extends FishPlayerObject {
    protected static boolean Lose = false;
    protected static boolean Dark = false;
    private int scoreLevel1 = 10;
    private int scoreLevel2 = 20;
    private int scoreLevel3 = 30;

    private int dir; //1 quay tra 2 quay phai

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
                animationFlip = new Animation(Define.FISH_LEVEL2_FLIP_START, Define.FISH_LEVEL2_FLIP_END, 100);
                break;
            case 3:
                animationFlip = new Animation(Define.FISH_LEVEL3_FLIP_START, Define.FISH_LEVEL3_FLIP_END, 50);
                break;
        }
    }

    public Player(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        this.dir = 2;
        this.level = 2;
        this.health = 100;
        checkFlip = false;
        initAnimation();
    }

    private boolean checkFlip(){
        if(this.dir != this.oldDir){
            return true;
        }
        return false;
    }

    long timeOld = System.currentTimeMillis();
    private int count = 0;
    public void draw(Graphics g) {
        if(checkFlip == false&&Lose == false&&Dark == false) {
            animationNormal.draw(g, positionX + GameManager.getInstance().getLocationX()+animationNormal.getWidth()/2
                    , positionY + GameManager.getInstance().getLocationY());
        }
        else if(checkEat==true) {
            animationEat.draw(g, positionX + GameManager.getInstance().getLocationX()
                        , positionY + GameManager.getInstance().getLocationY());

            count++;
            if(count > 17) {
                count = 0;
                checkEat = false;
                checkFlip = false;
                Lose=false;
            }
        }
        else if(checkFlip ==true){
            animationFlip.draw(g,positionX + GameManager.getInstance().getLocationX()+animationFlip.getWidth()/2,
                    positionY + GameManager.getInstance().getLocationY());
            count++;
            if(count > 17){
                count = 0;
                checkFlip =false;
                checkEat = false;
                Lose = false;
                Dark = false;
            }
        }
        else if(Dark == true){
            long startTime = System.currentTimeMillis();
            while(System.currentTimeMillis() - startTime <= 30){
                BufferedImage image = null;
                try{
                    image = ImageIO.read(new File("Resources/dark.png"));
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(this.dir ==2){
                    g.drawImage(image,positionX-850,positionY-650,null);
                    animationNormal.draw(g, positionX + GameManager.getInstance().getLocationX()+animationNormal.getWidth()/2
                            , positionY + GameManager.getInstance().getLocationY());
                }
                else if(this.dir ==1){
                    g.drawImage(image,positionX - 950,positionY-650,null);
                    animationNormal.draw(g, positionX + GameManager.getInstance().getLocationX()+animationNormal.getWidth()/2
                            , positionY + GameManager.getInstance().getLocationY());
                }
            }
            checkFlip =false;
            checkEat = false;
            Lose =false;
        }
    }

    private int oldX;
    private int oldY;
    private int oldDir = 0;
    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        if(positionX > oldX + 3) {
            animationNormal.setFlipX(-1);
            animationFlip.setFlipX(1);
            this.dir = 1;
        }
        else if(positionX < oldX - 3){
            animationNormal.setFlipX(1);
            animationFlip.setFlipX(-1);
            this.dir = 2;
        }

    }

    public int eat = 0;
    public void update() {
        super.update();
        this.move(this.positionX, this.positionY);
        if (checkCollisionEnemy()) {
            eat++;
            switch (level) {
                case 1: {
                    if(eat > scoreLevel1) {
                        eat = 0;
                        level++;
                        initAnimation();
                    }
                    break;
                }
                case 2: {
                    if(eat > scoreLevel2) {
                        eat = 0;
                        level++;
                        initAnimation();
                    }
                    break;
                }
                case 3: {
                    if(eat > scoreLevel3) {
                        eat = 0;
                        level++;
                        initAnimation();
                    }
                    break;
                }
            }

            if (s1 > s2) {
                checkEat = true;
                Music.music("sound2");
            } else {
                Lose =true;
            }

        }
        checkJellyFish();
        if (checkFlip()) {
            checkFlip = true;
        }
        oldX = positionX;
        oldY = positionY;
        oldDir = this.dir;
    }

    public double s1, s2;
    private boolean checkCollisionEnemy() {
        Rectangle rectPlayer = new Rectangle(this.positionX, this.positionY, animationNormal.getWidth(), animationNormal.getHeight());
        s1 = animationNormal.getWidth()* animationNormal.getHeight();
        for (FishObject fishObject : FishEnemyManager.getInstance().getVectorFishObject()) {
            Rectangle rectFishObject = new Rectangle(fishObject.getPositionX(), fishObject.getPositionY(), fishObject.getWidth() / 5, fishObject.getHeight() / 5);
            s2 = fishObject.getWidth() * fishObject.getHeight();
            if (rectPlayer.intersects(rectFishObject) && !(fishObject instanceof JellyFish)) {
                if (s1 >= s2) {
                    FishEnemyManager.getInstance().getVectorFishObject().remove(FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishObject));
                }
                return true;
            }
        }
        return false;
    }
    private boolean checkJellyFish(){
        Rectangle rectPlayer = new Rectangle(this.positionX, this.positionY, animationNormal.getWidth(), animationNormal.getHeight());

        for (FishObject fishObject : FishEnemyManager.getInstance().getVectorFishObject()) {
            Rectangle rectFishObject = new Rectangle(fishObject.getPositionX(), fishObject.getPositionY(), fishObject.getWidth()/4, fishObject.getHeight()/4);
            if(rectPlayer.intersects(rectFishObject)&& (fishObject instanceof JellyFish)){
                Dark = true;
//                System.out.println(" cc nhe");
                return true;

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

    public static boolean isLose() {
        return Lose;
    }

    public static void setLose(boolean lose) {
        Lose = lose;
    }
}
