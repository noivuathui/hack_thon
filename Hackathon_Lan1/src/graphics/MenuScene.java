package graphics;

import singleton.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by hungtran on 3/17/16.
 */
public class MenuScene extends Scene {
    BufferedImage backgroundMenu;
    public MenuScene() {
        try {
            backgroundMenu = ImageIO.read(new File("Resources/backgroundMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics g) {
       g.drawImage(backgroundMenu, 0, 0, null);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_N){
            GameManager.getInstance().stackMenuGame.push(new GamePlayScene());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
