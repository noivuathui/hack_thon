package singleton;

import fish.Player;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class PlayerManager {
    private Player player;
    private static PlayerManager ourInstance = new PlayerManager();

    public static PlayerManager getInstance() {
        return ourInstance;
    }

    private PlayerManager() {
        player = new Player(100,100,2);
    }

    public Player getPlayer() {
        return player;
    }
}
