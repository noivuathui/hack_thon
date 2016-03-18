package fish.object;

import fish.object.FishObject;

/**
 * Created by VinhNguyenDinh on 03/18/2016.
 */
public abstract class FishPlayerObject extends FishObject {
    protected int level;
    protected int direction; // 1.Left - 2.Right
    protected int health;
    protected int start;
    protected int end;
    protected int start_eat;
    protected int end_eat;
    public FishPlayerObject(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
    }

}
