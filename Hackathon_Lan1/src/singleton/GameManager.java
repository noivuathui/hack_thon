package singleton;

import graphics.Scene;

import java.util.Stack;

/**
 * Created by VinhNguyenDinh on 03/14/2016.
 */
public class GameManager {
    private int locationX;
    private int locationY;
    private static GameManager ourInstance = new GameManager();
    public Stack<Scene> stackMenuGame;

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {
        locationX = 0;
        locationY = 0;
        stackMenuGame = new Stack<Scene>();
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
}
