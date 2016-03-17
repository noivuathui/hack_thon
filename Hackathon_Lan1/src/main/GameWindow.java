package main;

import Plant.Coral;
import fish.*;
import graphics.Topic;
import singleton.FishEnemyManager;
import singleton.PlayerManager;
import sound.Music;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class GameWindow extends Frame implements Runnable {
    Graphics seconds;
    Image image;
    BufferedImage background;
    Vector<FishObject>  vectorFishObject;
    Vector<JellyFish> vectorJellyFish;
    Vector<Coral> coralVector;


    public GameWindow() {
        this.setTitle(Topic.TITLE);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(250,80);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        try {
            background = ImageIO.read(new File(Topic.BACKGROUND));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Khoi tao
        initFish();

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                PlayerManager.getInstance().getPlayer().move(e.getX(),e.getY());
                // Hàm ẩn chuột
                BufferedImage cursorImg = new BufferedImage(PlayerManager.getInstance().getPlayer().getPositionX(), PlayerManager.getInstance().getPlayer().getPositionY(), BufferedImage.TYPE_INT_ARGB);
                Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
                setCursor(blankCursor);
            }
        });

    }

    private void initFish() {
        vectorFishObject =  FishEnemyManager.getInstance().getVectorFishObject();
        vectorJellyFish = FishEnemyManager.getInstance().getVectorJellyFish();
        coralVector = FishEnemyManager.getInstance().getVectorCoral();

        vectorFishObject.add(new FishEnemy(350,300,2));
        vectorFishObject.add(new FishEnemy(400,200,2));
        vectorFishObject.add(new FishEnemy(550,100,2));
        vectorFishObject.add(new FishEnemy(350,400,2));
        vectorFishObject.add(new FishEnemySmall(200,50,3));
        vectorFishObject.add(new FishEnemySmall(120,80,2));
        vectorFishObject.add(new FlashlightFish(50,50,4));
        vectorFishObject.add(new FlashlightFish(90,200,2));
        vectorJellyFish.add(new JellyFish(30,600,2));
        vectorJellyFish.add(new JellyFish(500,600,1));

        coralVector.add(new Coral(80,480,1));
        coralVector.add(new Coral(630,480,2));
        coralVector.add(new Coral(300,20,3));
        coralVector.add(new Coral(450,20,4));
    }

    @Override
    public void update(Graphics g){
        if(image == null){
            image = createImage(this.getWidth(), this.getHeight());
            seconds= image.getGraphics();
        }
        seconds.setColor(getBackground());
        seconds.fillRect(0,0,getWidth(),getHeight());
        seconds.setColor(getForeground());
        paint(seconds);
        g.drawImage(image,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background,0,0,null);
        for(FishObject fishObject : vectorFishObject) {
            fishObject.draw(g);
        }
        for(JellyFish jellyFish : vectorJellyFish) {
            jellyFish.draw(g);
        }

        PlayerManager.getInstance().getPlayer().draw(g);

        for(Coral coral : coralVector){
            coral.draw(g);
        }
    }

    private boolean check = true;
    private int count = 0;
    @Override
    public void run() {
        while(true) {
            PlayerManager.getInstance().getPlayer().update();
            for(FishObject fishObject : vectorFishObject) {
                fishObject.update();
            }
            for(JellyFish jellyFish : vectorJellyFish) {
                jellyFish.update();
            }
            for(Coral coral : coralVector){
                coral.update();
            }
            if(count >= 0) {
                count++;
                if(check) {
                    Music.music("sound");
                    check = false;
                }
                if(count > 200) {
                    count = 0;
                    check = true;
                }
            }
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
