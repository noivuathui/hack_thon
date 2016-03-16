package singleton;

import Scene.Coral;
import fish.FishEnemy;
import fish.FlashlightFish;
import fish.FishEnemySmall;
import fish.JellyFish;

import java.util.Vector;

/**
 * Created by Anh on 3/14/2016.
 */
public class FishEnemyManager {
    int m;

    private Vector<FishEnemy> vectorFishEnemy;
    private Vector<FlashlightFish> vectorFlashlightFish;
    private Vector<FishEnemySmall> fishEnemySmallVector;
    private  Vector<JellyFish> jellyFishVector;
    private  Vector<Coral> coralVector;

    private static FishEnemyManager ourInstance = new FishEnemyManager();

    public static FishEnemyManager getInstance(){
        return  ourInstance;
    }

    private FishEnemyManager(){
        vectorFishEnemy = new Vector<FishEnemy>();
        vectorFlashlightFish = new Vector<FlashlightFish>();
        fishEnemySmallVector = new Vector<FishEnemySmall>();
        jellyFishVector = new Vector<JellyFish>();
        coralVector = new Vector<Coral>();
    }

    public Vector<FishEnemy> getVectorFishEnemy(){
        return vectorFishEnemy;
    }
    public Vector<FlashlightFish> getVectorFlashlightFish(){return vectorFlashlightFish;}
    public Vector<FishEnemySmall> getFishEnemySmallVector(){
        return fishEnemySmallVector;
    }
    public Vector<JellyFish> getJellyFishVector(){ return jellyFishVector; }
    public Vector<Coral> getCoralVector(){ return  coralVector; }

}
