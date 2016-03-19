package Plant;

import fish.enemy.FishEnemy;
import fish.enemy.FishEnemySmall;
import fish.object.FishObject;
import main.GameObject;
import singleton.FishEnemyManager;
import singleton.PlayerManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by noivu on 3/19/2016.
 */
public class LuoiCau extends GameObject {

    public LuoiCau(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
        try {
            this.sprite = ImageIO.read(new File("Resources/cau.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void move(){

    }
    public boolean check = true;
    @Override
    public void update() {
        this.move();
        if(check = true) {
            if (checkCollision()) {
                System.out.println("okok");
                for (FishObject fishObject : FishEnemyManager.getInstance().getVectorFishObject()) {
                    FishEnemyManager.getInstance().getVectorFishObject().clear();
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(sprite,700,400,null);
    }

    public boolean checkCollision(){
        Rectangle rectLuoiCau = new Rectangle(positionX,positionY,sprite.getWidth(),sprite.getHeight());

        Rectangle rectPlay =
                new Rectangle(PlayerManager.getInstance().getPlayer().getPositionX()
                        ,PlayerManager.getInstance().getPlayer().getPositionY()
                        ,PlayerManager.getInstance().getPlayer().getWidth()
                        ,PlayerManager.getInstance().getPlayer().getHeight());

        return rectLuoiCau.intersects(rectPlay);

    }

}
