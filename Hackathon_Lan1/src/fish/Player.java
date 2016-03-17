package fish;

import graphics.Animation;
import singleton.FishEnemyManager;
import singleton.GameManager;
import sound.Music;

import java.awt.*;
/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Player extends FishObject {
    private int level;
    private int direction; // 1.Left - 2.Right
    private int start;
    private int end;
    private int start_eat;
    private int end_eat;
    private Animation anim;
    private Animation anim_eat;
    private boolean check = true;

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
        anim  = new Animation(start,end,50);
        anim_eat  = new Animation(start_eat,end_eat,50);
    }

    public Player(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        this.direction = 1;
        this.level = 1;
        initAnimation();
    }

    private int count = 0;
    public void draw(Graphics g) {
        if(check) {
            anim.draw(g, positionX + GameManager.getInstance().getLocationX()
                    , positionY + GameManager.getInstance().getLocationY());
        }
        else {
            anim_eat.draw(g, positionX + GameManager.getInstance().getLocationX()
                    , positionY + GameManager.getInstance().getLocationY());
            count++;
            if(count > 17) {
                count = 0;
                check = true;
            }
        }
    }

    @Override
    public int getWidth() {
        return anim.getWidth();
    }

    @Override
    public int getHeight() {
        return anim.getHeight();
    }

    public int oldX;
    public int oldY;

    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        if(getPositionX() >= oldX +5){
            anim.setFlipX(-1);
        }
        else if(getPositionX() <= oldX -5){
            anim.setFlipX(1);
        }
    }

    int kt = 1;
    public void update() {
        super.update();
        this.move(this.positionX, this.positionY);
        if(checkCollisionEnemy() == true) {
            check = false;
            Music.music("sound2");
        }
        oldX = getPositionX();
        oldY = getPositionY();
    }

    private boolean checkCollisionEnemy() {
        Rectangle rectPlayer = new Rectangle(this.positionX +20, this.positionY+20, this.anim.getWidth(), this.anim.getHeight());
        for (FishObject fishObject : FishEnemyManager.getInstance().getVectorFishObject()) {
            Rectangle rectFishObject = new Rectangle(fishObject.getPositionX(), fishObject.getPositionY(), fishObject.getWidth(), fishObject.getHeight());
            if (rectPlayer.intersects(rectFishObject)) {
                FishEnemyManager.getInstance().getVectorFishObject().remove(FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishObject));
                return true;
            }
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
