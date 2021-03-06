package singleton;

import fish.player.Player;

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
        player = new Player(600,600,2);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
