package fish;

import graphics.Animation;
import singleton.FishEnemyManager;
import singleton.GameManager;
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
        this.level = 2;
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


    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void update() {
        super.update();
        this.move(this.positionX, this.positionY);
        if(checkCollisionEnemy()) {
            check = false;
        }
    }

    private boolean checkCollisionEnemy() {
        Rectangle rectPlayer = new Rectangle(positionX, positionY, anim.getWidth(), anim.getHeight());
        for (FishEnemy fishEnemy : FishEnemyManager.getInstance().getVectorFishEnemy()) {
            Rectangle rectFishEnemy = new Rectangle(fishEnemy.getPositionX(), fishEnemy.getPositionY(), fishEnemy.getWidth(), fishEnemy.getHeight());
            if (rectPlayer.intersects(rectFishEnemy)) {
                FishEnemyManager.getInstance().getVectorFishEnemy().remove(FishEnemyManager.getInstance().getVectorFishEnemy().indexOf(fishEnemy));
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

    public boolean getCheck() {
        return check;
    }
}
