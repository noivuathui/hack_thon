package main;

import Plant.Coral;
import fish.enemy.FishEnemy;
import fish.enemy.FishEnemySmall;
import fish.enemy.FlashlightFish;
import fish.enemy.JellyFish;
import fish.object.FishObject;
import graphics.Topic;
import singleton.FishEnemyManager;
import singleton.GameManager;
import singleton.PlayerManager;

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
    Vector<Coral> coralVector;

    public GameWindow() {
        this.setTitle(Topic.TITLE);
        this.setSize(1000, 700);
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
        coralVector = FishEnemyManager.getInstance().getVectorCoral();

        vectorFishObject.add(new FishEnemy(350,300,2));
        vectorFishObject.add(new FishEnemy(400,200,2));
        vectorFishObject.add(new FishEnemy(550,100,2));
        vectorFishObject.add(new FishEnemy(350,400,2));

        vectorFishObject.add(new FishEnemySmall(200,50,3));
        vectorFishObject.add(new FishEnemySmall(120,80,2));
        vectorFishObject.add(new FishEnemySmall(50,50,3));
        vectorFishObject.add(new FishEnemySmall(70,80,2));

        vectorFishObject.add(new FlashlightFish(50,50,4));
        vectorFishObject.add(new FlashlightFish(90,200,2));

        vectorFishObject.add(new JellyFish(30,600,2));
        vectorFishObject.add(new JellyFish(500,600,1));

        coralVector.add(new Coral(80,580,1));
        coralVector.add(new Coral(730,580,2));
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
        for(Coral coral : coralVector){
            coral.draw(g);
        }
        PlayerManager.getInstance().getPlayer().draw(g);
    }

    @Override
    public void run() {
        while(true) {
            PlayerManager.getInstance().getPlayer().update();
            for(FishObject fishObject : vectorFishObject) {
                fishObject.update();
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
