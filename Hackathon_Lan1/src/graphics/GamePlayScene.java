package graphics;

import Plant.Coral;
import fish.enemy.FishEnemy;
import fish.enemy.FishEnemySmall;
import fish.enemy.FlashlightFish;
import fish.enemy.JellyFish;
import fish.object.FishObject;
import singleton.FishEnemyManager;
import singleton.GameManager;
import singleton.PlayerManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by hungtran on 3/17/16.
 */
public class GamePlayScene extends Scene {
    BufferedImage background;
    Vector<FishObject> vectorFishObject;
    Vector<Coral> coralVector;
    public GamePlayScene() {
        try {
            background = ImageIO.read(new File(Topic.BACKGROUND));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initFish();
    }
    private void initFish() {
        vectorFishObject =  FishEnemyManager.getInstance().getVectorFishObject();
        coralVector = FishEnemyManager.getInstance().getVectorCoral();

        vectorFishObject.add(new FishEnemy(350,300,2));
//        vectorFishObject.add(new FishEnemy(400,200,2));
//        vectorFishObject.add(new FishEnemy(550,100,2));
//        vectorFishObject.add(new FishEnemy(350,400,2));

        vectorFishObject.add(new FishEnemySmall(200,50,3));
        vectorFishObject.add(new FishEnemySmall(120,80,2));
        vectorFishObject.add(new FishEnemySmall(50,50,3));
        vectorFishObject.add(new FishEnemySmall(70,80,2));

        vectorFishObject.add(new FlashlightFish(50,50,4));
//        vectorFishObject.add(new FlashlightFish(90,200,2));

        vectorFishObject.add(new JellyFish(30,600,2));
        vectorFishObject.add(new JellyFish(500,600,1));

        coralVector.add(new Coral(80,580,1));
        coralVector.add(new Coral(730,580,2));
        coralVector.add(new Coral(300,20,3));
        coralVector.add(new Coral(450,20,4));
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("THIS IS GAME PLAY SCENE. Press B to Menu.Press N to Score.", 300, 300);
        g.drawImage(background, 0, 0, null);
        for(FishObject fishObject : vectorFishObject) {
            fishObject.draw(g);
        }
        for(Coral coral : coralVector){
            coral.draw(g);
        }
        PlayerManager.getInstance().getPlayer().draw(g);
    }

    @Override
    public void update() {
        PlayerManager.getInstance().getPlayer().update();
        for(FishObject fishObject : vectorFishObject) {
            fishObject.update();
        }
        if(PlayerManager.getInstance().getPlayer().isLose() == true) {

            //System.out.println("an roi");
            GameManager.getInstance().stackMenuGame.push(new GameOver());
            PlayerManager.getInstance().getPlayer().setLose(false);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_B){
            GameManager.getInstance().stackMenuGame.pop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
