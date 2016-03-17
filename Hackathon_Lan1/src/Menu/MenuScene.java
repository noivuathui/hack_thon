package Menu;

import singleton.GameManager;
import singleton.PlayerManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by noivu on 3/17/2016.
 */
public class MenuScene extends Scene {
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File("Resources/image 690.png")),220,200,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PlayerManager.getInstance().getPlayer().move(e.getX(), e.getY());
        if((positionX <= e.getX()||(positionX + 132) >= e.getX()) &&(positionY <= e.getY() || positionY +132 >= e.getY())){
            GameManager.getInstance().stackMenuGame.push(new ScoreScene());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
