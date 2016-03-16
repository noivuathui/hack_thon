package main;

import Scene.Coral;
import fish.FishEnemy;
import fish.FishEnemy1;
import fish.FishEnemySmall;
import fish.JellyFish;
import singleton.FishEnemyManager;
import singleton.PlayerManager;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class GameWindow extends Frame implements Runnable {
    int a;
    Graphics seconds;
    Image image;
    BufferedImage background;
    Vector<FishEnemy> vectorFishEnemy;
    Vector<FishEnemySmall> fishEnemySmallVector;
    Vector<FishEnemy1> fishEnemy1Vector;
    Vector<JellyFish> jellyFishVector;
    Vector<Coral> coralVector;


    public GameWindow() {

        this.setTitle("FEEDING FRENZY");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(250,80);
        music();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        try {
            background = ImageIO.read(new File("Resources/image 672.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        vectorFishEnemy =  FishEnemyManager.getInstance().getVectorFishEnemy();
        fishEnemy1Vector = FishEnemyManager.getInstance().getVectorFishEnemy1();
        fishEnemySmallVector = FishEnemyManager.getInstance().getFishEnemySmallVector();
        jellyFishVector = FishEnemyManager.getInstance().getJellyFishVector();
        coralVector = FishEnemyManager.getInstance().getCoralVector();

        vectorFishEnemy.add(new FishEnemy(50,300,2));
        vectorFishEnemy.add(new FishEnemy(20,200,2));
//        vectorFishEnemy.add(new FishEnemy(150,100,2));
//        vectorFishEnemy.add(new FishEnemy(350,400,2));

        fishEnemySmallVector.add(new FishEnemySmall(200,50,3));
        fishEnemySmallVector.add(new FishEnemySmall(120,80,2));

        fishEnemy1Vector.add(new FishEnemy1(50,50,4));
        fishEnemy1Vector.add(new FishEnemy1(90,200,2));

        jellyFishVector.add(new JellyFish(30,600,2));
        jellyFishVector.add(new JellyFish(500,600,1));

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
        for(FishEnemy fishEnemy : vectorFishEnemy){
            fishEnemy.draw(g);
        }
        for(FishEnemySmall fishEnemySmall : fishEnemySmallVector){
            fishEnemySmall.draw(g);
        }
        for(FishEnemy1 fishEnemy1 : fishEnemy1Vector){
            fishEnemy1.draw(g);
        }
        for(JellyFish jellyFish : jellyFishVector){
            jellyFish.draw(g);
        }

        PlayerManager.getInstance().getPlayer().draw(g);

        for(Coral coral : coralVector){
            coral.draw(g);
        }
    }
    public void music ()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD ;
        ContinuousAudioDataStream loop = null;
        try {
            BGM = new AudioStream (new FileInputStream("sound.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
            AudioPlayer.player.start(BGM);
        } catch (IOException error) {
            MGP.start(loop);
            System.out.println("Error");
        }

    }
    @Override
    public void run() {

        while(true) {
            PlayerManager.getInstance().getPlayer().update();
            for(FishEnemy fishEnemy : vectorFishEnemy){
                fishEnemy.update();
            }
            for(FishEnemySmall fishEnemySmall : fishEnemySmallVector){
                fishEnemySmall.update();
            }
            for(FishEnemy1 fishEnemy1 : fishEnemy1Vector){
                fishEnemy1.update();
            }
            for(JellyFish jellyFish : jellyFishVector){
                jellyFish.update();
            }
            for(Coral coral : coralVector){
                coral.update();
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
