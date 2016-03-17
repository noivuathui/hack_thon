package Menu;

import singleton.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by noivu on 3/17/2016.
 */
public class ScoreScene extends Scene {
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File("Resources/image 695.png")),200,200,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if((positionX <= e.getX()||(positionX + 132) >= e.getX()) &&(positionY <= e.getY() || positionY +132 >= e.getY())){
            GameManager.getInstance().stackMenuGame.pop();
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
