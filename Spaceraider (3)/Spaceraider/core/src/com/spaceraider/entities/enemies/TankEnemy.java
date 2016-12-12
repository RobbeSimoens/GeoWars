package com.spaceraider.entities.enemies;

import com.spaceraider.entities.enums.EnemyType;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.enums.Status;

/**
 * Created by ERR0R on 9-11-2016.
 */
public class TankEnemy implements Enemy {

    private EnemyType type;
    private int hitpoints;
    private boolean attacks;
    private Powerup powerup;
    private Powerdown powerdown;
    private Status status = Status.NOTHING;

    public TankEnemy() {
        initialize();
    }

    @Override
    public void initialize() {
        type = EnemyType.TANK;
        hitpoints = 4;
        attacks = false;
        powerdown = null;
        powerup = null;
        status = Status.MOVING;
    }
    @Override
    public String toString(){
        return "type : " + type + "     Hitpoints : " + hitpoints + "     Attacks: " + attacks + "       " + "    Powerup : " + powerup + "       Powerdown : " + powerdown;
    }
}