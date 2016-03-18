package Scene;

import main.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by noivu on 3/15/2016.
 */
public class Coral extends GameObject {
    private int kieu;

    public Coral(int positionX, int positionY, int kieu){
        this.positionX = positionX;
        this.positionY = positionY;
        this.kieu = kieu;
        switch (kieu){
            case 1 : {
                try {
                    this.sprite = ImageIO.read(new File("Resources/image 875.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2 : {
                try {
                    this.sprite = ImageIO.read(new File("Resources/image 674.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 3 : {
                try {
                    this.sprite = ImageIO.read(new File("Resources/image 45.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4 : {
                try {
                    this.sprite = ImageIO.read(new File("Resources/image 46.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite,positionX,positionY,null);
    }
}
