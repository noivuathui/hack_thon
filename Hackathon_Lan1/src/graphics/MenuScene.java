package graphics;

import singleton.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by hungtran on 3/17/16.
 */
public class MenuScene extends Scene {
    public MenuScene() {
        try {
            bufferedImage = ImageIO.read(new File(Topic.NEW_GAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_N){
            GameManager.getInstance().getStackMenuGame().push(new GamePlayScene());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
