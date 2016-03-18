package graphics;

import singleton.GameManager;
import singleton.PlayerManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by hungtran on 3/17/16.
 */
public class GamePlayScene extends Scene {
    private int count = 0;

    @Override
    public void draw(Graphics g) {
        if(PlayerManager.getInstance().getPlayer().getCheckEat() == false) {
            PlayerManager.getInstance().getPlayer().getAnimationNormal().draw(g, PlayerManager.getInstance().getPlayer().getPositionX() + GameManager.getInstance().getLocationX()
                    , PlayerManager.getInstance().getPlayer().getPositionY() + GameManager.getInstance().getLocationY());
        }
        else {
            PlayerManager.getInstance().getPlayer().getAnimationEat().draw(g, PlayerManager.getInstance().getPlayer().getPositionX() + GameManager.getInstance().getLocationX()
                    , PlayerManager.getInstance().getPlayer().getPositionY() + GameManager.getInstance().getLocationY());
            count++;
            if(count > 17) {
                count = 0;
                PlayerManager.getInstance().getPlayer().setCheckEat(false);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_N){
            GameManager.getInstance().getStackMenuGame().push(new ScoreScene());
        } else if(e.getKeyCode() == KeyEvent.VK_B){
            GameManager.getInstance().getStackMenuGame().pop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
