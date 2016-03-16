package fish;

import main.GameObject;
import java.awt.*;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public abstract class FishObject extends GameObject {
    protected int speed;

    FishObject(int positionX, int positionY, int speed) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite,positionX,positionY,null);
    }
}
