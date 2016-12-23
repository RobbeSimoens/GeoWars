package com.spaceraider.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.player.Player;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enums.EnemyType;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.enums.Status;
import com.spaceraider.entities.orbs.PowerupOrb;

import java.util.Random;

/**
 * Created by ERR0R on 9-11-2016.
 */
public class PowerupEnemy extends SpaceObject implements Enemy{
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
    private Rectangle rect;

    public PowerupEnemy(Player player) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        x = getRandom(1920);
        y = getRandom(1080);

        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_powerup.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());

    }

    public PowerupEnemy (Player player, String side) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        if(side.equals("left"))
        {
            x = getRandom(960);
            y = getRandom(1080);
        }
        else{
            x = getRandom(960) + 960;
            y = getRandom(1080);
        }

        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_powerup.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
    }

    public PowerupEnemy(float x, float y)
    {
        this.x = x;
        this.y = y;
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
        speed = 300;
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
        PowerupEnemy b = new PowerupEnemy(x,y);
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
        x = (float) (x + (dirX * speed * dt) /destinationLength);
        rect.setX(x);
        y = (float) (y + (dirY * speed * dt) / destinationLength);
        rect.setY(y);

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public Rectangle getRectangle() {
        return rect;
    }

    @Override
    public void reduceHitpoints() {
        hitpoints = hitpoints - 1;
        if(hitpoints ==  0){
            dropOrb();
            player.removeEnemy(this);
        }
    }

    @Override
    public void dropOrb() {
        player.addOrb(new PowerupOrb(x,y, powerup));
    }
}
