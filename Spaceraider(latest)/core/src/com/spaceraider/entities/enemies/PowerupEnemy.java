package com.spaceraider.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.entities.Player;
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
    private static SpriteBatch batch;
    private static Texture texture;
    private float x,y;
    private float dirX,dirY;
    private Player player;
    private int speed;

    public PowerupEnemy(Player player) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        x = getRandom(1920);
        y = getRandom(1080);

        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_powerup.png");

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
        speed = 500;
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
        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
    }
    public void update(float dt){
        setDirection();
        move(dt);
        StandardEnemy b = new StandardEnemy(x,y);
        b.render(batch);
    }

    public float getRandom(int maxval)
    {
        return (float) rand.nextInt(maxval);
    }

    public void setDirection(){
        dirX = player.getX() - x;
        dirY = player.getY() - y;
    }

    public void move(float dt){
        double destinationLength = Math.sqrt(dirX * dirX + dirY * dirY);
        x = (float) (x + (dirX * speed * dt) / destinationLength); // TODO : 1 = speed value
        y = (float) (y + (dirY * speed * dt) / destinationLength); // TODO : 1 = speed value ==> make variable

    }
}
