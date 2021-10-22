package com.mygdx.main.levels;

import com.mygdx.main.actors.ships.TrogonHarrier;
import com.mygdx.main.managers.EnemySpawnManager;

import java.util.ArrayList;

public class Level01 {

    /**
     * This class create all the spawns for Level 01
     **/

    //ATTRIBUTES
    public static EnemySpawnManager nodesFactory;
    /**
     * Spawn limiter
     **/
    public static float elapsedTime;

    //CONSTRUCTOR
    public Level01(ArrayList<TrogonHarrier> harrierList) {
        nodesFactory = new EnemySpawnManager(harrierList);
        elapsedTime = 0;
    }
    // METHODS

    public static void setupLevel01Spawn(float deltatime, float timer) {
        elapsedTime += deltatime;

        /** All the nodes for the level **/
        switch ((int) timer) {

            case 2:
            case 63:
            case 39:
            case 99:
                if (elapsedTime >= 1) {
                    nodesFactory.harrierTriplethreat();
                    elapsedTime = 0;
                }
                break;
            case 10:
            case 23:
            case 60:
            case 87:

                if (elapsedTime >= 1) {
                    nodesFactory.harrierSpearhead();
                    elapsedTime = 0;
                }
                break;

            case 20:
            case 33:
            case 48:
                if (elapsedTime >= 1) {
                    nodesFactory.harrierLine();
                    elapsedTime = 0;
                }
                break;

            case 27:
            case 36:
            case 58:
            case 65:
                if (elapsedTime >= 1) {
                    nodesFactory.harrierStrike();
                    elapsedTime = 0;
                }
                break;


        }

    }
}

