package com.spaceraider.entities.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.entities.enums.EnemyType;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.enums.Status;

import java.util.Random;

/**
 * Created by ERR0R on 9-11-2016.
 */
public class PowerdownEnemy implements Enemy{
    private Random rand;
    private EnemyType type;
    private int hitpoints;
    private boolean attacks;
    private Powerup powerup;
    private Powerdown powerdown;
    private Status status = Status.NOTHING;

    public PowerdownEnemy() {
        initialize();
    }

    @Override
    public void initialize() {
        rand = new Random();
        type = EnemyType.POWERDOWN;
        attacks = false;
        powerup = null;
        powerdown = generateRandomPowerdown();
        status = Status.MOVING;
    }

    private Powerdown generateRandomPowerdown(){
        int randomNumber = rand.nextInt(3);
        Powerdown powerdown = null;
        switch (randomNumber)
        {
            case 0:
                powerdown = Powerdown.INVERTED;
                break;
            case 1:
                powerdown = Powerdown.SILENCED;
                break;
            case 2:
                powerdown =  Powerdown.SLOWED;
                break;
        }
        return powerdown;
    }

    @Override
    public String toString(){
        return "type : " + "     Hitpoints : " + hitpoints + type + "     Attacks: " + attacks + "       " + "    Powerup : " + powerup + "       Powerdown : " + powerdown;
    }

    public void render(SpriteBatch batch){

    }
    public void update(float dt){

    }
}
