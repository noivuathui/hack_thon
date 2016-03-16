package main;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();

    }
}
