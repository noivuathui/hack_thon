package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import main.GameObject;

import javax.imageio.ImageIO;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Animation extends GameObject {
    Vector<Image> imageVector;
    private int delta;
    private int countTime;
    private int index;
    private int width;
    private int height;
    private int flipX;//1 thi quay mac dinh, -1 thi quay sang ben kia
    private int flipY;//1 mac dinh,-1 thi quay

    public int getFlipX() {
        return flipX;
    }

    public void setFlipX(int flipX) {
        this.flipX = flipX;
    }

    public int getFlipY() {
        return flipY;
    }

    public void setFlipY(int flipY) {
        this.flipY = flipY;
    }

    public Animation(int begin, int end, int delta) {
        flipX = 1;
        flipY = 1;
        imageVector = new Vector<Image>();
        this.delta = delta;
        this.countTime = 0;
        this.index = 0;
        for(int i = begin; i <= end; i+=2) {
            String str = String.format("Resources/image %d.png", i);
            try {
                BufferedImage img = ImageIO.read(new File(str));
                width = img.getWidth();
                height = img.getHeight();
                imageVector.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    public void draw(Graphics g, int x, int y){
        g.drawImage(imageVector.get(index), x, y, flipX * width, flipY * height,null);
        countTime += 17;
        if (countTime >= delta) {
            countTime = 0;
            index++;
            if (index >= imageVector.size()) {
                index = 0;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
