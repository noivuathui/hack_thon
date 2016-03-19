package giftBox;

import fish.enemy.JellyFish;
import fish.object.FishObject;
import main.GameObject;
import singleton.FishEnemyManager;
import singleton.PlayerManager;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by VinhNguyenDinh on 03/19/2016.
 */
public class Hook extends GameObject {
    public Hook(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        try {
            sprite = ImageIO.read(new File("Resources/Hook.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void remove() {
        this.positionX = 0;
        this.positionY= 0;
        this.sprite = null;
    }
    int n = FishEnemyManager.getInstance().getVectorFishObject().size();
    int []a = new int[100];
    public boolean checkCollision() {
        Rectangle rectPlayer = new Rectangle(PlayerManager.getInstance().getPlayer().getPositionX(),
                            PlayerManager.getInstance().getPlayer().getPositionY(),
                            PlayerManager.getInstance().getPlayer().getAnimationNormal().getWidth(),
                            PlayerManager.getInstance().getPlayer().getAnimationNormal().getHeight());
        Rectangle rectHook = new Rectangle(positionX,positionY,sprite.getWidth(),sprite.getHeight());
        if(rectPlayer.intersects(rectHook)) {
            //this.remove();
            int idx = 0;
            for(FishObject fishObject : FishEnemyManager.getInstance().getVectorFishObject()) {
                a[idx] = -1;
                if(PlayerManager.getInstance().getPlayer().getLevel() > fishObject.getLevel() && !(fishObject instanceof JellyFish)) {
                      a[idx] =  FishEnemyManager.getInstance().getVectorFishObject().indexOf(fishObject);
                }
                idx++;
            }
            for(int i = 0; i < n; i++) {
                if(a[i] != -1) {
                    FishEnemyManager.getInstance().getVectorFishObject().remove(a[i]);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        //checkCollision();
        //if(checkCollision())    remove();
    }
    //boolean check = checkCollision();
    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite,positionX,positionY,null);
    }
}
