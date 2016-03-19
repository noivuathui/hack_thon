package fish.object;

import fish.object.FishObject;

/**
 * Created by VinhNguyenDinh on 03/18/2016.
 */
public abstract class FishPlayerObject extends FishObject {
    //protected int level;
    protected int health;
    protected int start;
    protected int end;
    protected int start_eat;
    protected int end_eat;
    protected boolean checkEat = false;
    protected boolean checkFlip = false;
    public FishPlayerObject(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
    }

}
