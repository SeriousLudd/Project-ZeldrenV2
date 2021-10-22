package com.mygdx.main.managers;

import com.mygdx.main.Game;
import com.mygdx.main.actors.ships.TrogonHarrier;

import java.util.ArrayList;

public class EnemySpawnManager {

    /** This class will allow us to easily spawn waves of enemies **/

    // ATTRIBUTES
    public ArrayList<TrogonHarrier> harrierList;

    private float x = Game.WIDTH;
    private float y = Game.HEIGHT;
    // CONSTRUCTOR

    public EnemySpawnManager(ArrayList<TrogonHarrier> harrierList) {
        this.harrierList = harrierList;
    }

    // METHODS
    /** setup enemies formations **/

    // Pattern 1 - Line formation
    public void harrierLine() {
        harrierList.add(new TrogonHarrier(x * 0.2f, y));
        harrierList.add(new TrogonHarrier(x * 0.3f, y + 50));
        harrierList.add(new TrogonHarrier(x * 0.4f, y + 100));
        harrierList.add(new TrogonHarrier(x * 0.5f, y + 150));
        harrierList.add(new TrogonHarrier(x * 0.6f, y + 200));
    }

    // Pattern 2 - Spearhead formation
    public void harrierSpearhead() {
        harrierList.add(new TrogonHarrier(x * 0.5f, y));
        harrierList.add(new TrogonHarrier(x * 0.6f, y+50));
        harrierList.add(new TrogonHarrier(x * 0.7f, y+100));
        harrierList.add(new TrogonHarrier(x * 0.8f, y+150));

        harrierList.add(new TrogonHarrier(x * 0.4f, y+50));
        harrierList.add(new TrogonHarrier(x * 0.3f, y+100));
        harrierList.add(new TrogonHarrier(x * 0.2f, y+150));
    }

    // Pattern 3 - Triplethreat formation
    public void harrierTriplethreat() {
        harrierList.add(new TrogonHarrier(x * 0.45f, y));
        harrierList.add(new TrogonHarrier(x * 0.40f, y+30));
        harrierList.add(new TrogonHarrier(x * 0.50f, y+30));

        harrierList.add(new TrogonHarrier(x * 0.15f, y));
        harrierList.add(new TrogonHarrier(x * 0.10f, y+30));
        harrierList.add(new TrogonHarrier(x * 0.20f, y+30));

        harrierList.add(new TrogonHarrier(x * 0.75f, y));
        harrierList.add(new TrogonHarrier(x * 0.70f, y+30));
        harrierList.add(new TrogonHarrier(x * 0.80f, y+30));
    }

    // Pattern 4 - Strike formation
    public void harrierStrike() {
        harrierList.add(new TrogonHarrier(x * 0.45f, y));
        harrierList.add(new TrogonHarrier(x * 0.40f, y+30));
        harrierList.add(new TrogonHarrier(x * 0.50f, y+30));

        harrierList.add(new TrogonHarrier(x * 0.80f, y+30));
        harrierList.add(new TrogonHarrier(x * 0.90f, y+30));

        harrierList.add(new TrogonHarrier(x * 0f, y+30));
        harrierList.add(new TrogonHarrier(x * 0.1f, y+30));
    }
}
