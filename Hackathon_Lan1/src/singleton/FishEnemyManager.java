package singleton;

import Scene.Coral;
import fish.*;
import java.util.Vector;

/**
 * Created by Anh on 3/14/2016.
 */
public class FishEnemyManager {
    private Vector<FishObject> vectorFishObject;
    private Vector<Coral> vectorCoral;

    private static FishEnemyManager ourInstance = new FishEnemyManager();

    public static FishEnemyManager getInstance(){
        return  ourInstance;
    }

    private FishEnemyManager(){
        vectorFishObject = new Vector<>();
        vectorCoral = new Vector<>();
    }

    public Vector<FishObject> getVectorFishObject() {
        return vectorFishObject;
    }

    public Vector<Coral> getVectorCoral() {
        return vectorCoral;
    }
}
