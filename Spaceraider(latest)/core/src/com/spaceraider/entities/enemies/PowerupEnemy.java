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
public class PowerupEnemy implements Enemy{
    private Random rand;
    private EnemyType type;
    private int hitpoints;
    private boolean attacks;
    private Powerup powerup;
    private Powerdown powerdown;
    private Status status = Status.NOTHING;

    public PowerupEnemy() {
        initialize();
    }

    @Override
    public void initialize() {
        rand = new Random();
        type = EnemyType.POWERUP;
        hitpoints = 1;
        attacks = false;
        powerdown = null;
        powerup = generateRandomPowerup();
        status = Status.MOVING;
    }

    private Powerup generateRandomPowerup(){
        int randomNumber = rand.nextInt(4);
        Powerup powerup = null;
        switch (randomNumber)
        {
            case 0:
                powerup = Powerup.CONESHOOTING;
                break;
            case 1:
                powerup = Powerup.NUKE;
                break;
            case 2:
                powerup = Powerup.RAPIDFIRE;
                break;
            case 3:
                powerup = Powerup.SHIELD;
                break;

        }
        return powerup;
    }
    @Override
    public String toString(){
        return "type : " + type + "     Hitpoints : " + hitpoints +  "     Attacks: " + attacks + "       " + "    Powerup : " + powerup + "       Powerdown : " + powerdown;
    }

    public void render(SpriteBatch batch){

    }
    public void update(float dt){

    }
}
