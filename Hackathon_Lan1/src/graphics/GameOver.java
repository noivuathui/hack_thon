package graphics;

import singleton.GameManager;
import singleton.PlayerManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by VinhNguyenDinh on 03/19/2016.
 */
public class GameOver extends Scene {
    BufferedImage image;

    public GameOver()  {
        GameManager.getInstance().stackMenuGame.push(new GamePlayScene());
        try {
            image = ImageIO.read(new File("Resources/gameover1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        while(GameManager.getInstance().stackMenuGame != null) {
            GameManager.getInstance().stackMenuGame.pop();
        }
        if(e.getKeyCode() == KeyEvent.VK_N) {
            GameManager.getInstance().stackMenuGame.push(new GamePlayScene());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
