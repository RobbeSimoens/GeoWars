package com.spaceraider.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.entities.Player;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enums.EnemyType;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.enums.Status;

import java.util.Random;

/**
 * Created by ERR0R on 9-11-2016.
 */
public class TankEnemy extends SpaceObject implements Enemy {

    private EnemyType type;
    private int hitpoints;
    private boolean attacks;
    private Powerup powerup;
    private Powerdown powerdown;
    private Status status = Status.NOTHING;
    private Player player;
    private static SpriteBatch batch;
    private static Texture texture;
    private float x,y;
    private Random rand;
    private float dirX,dirY;

    public TankEnemy(Player player) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        x = getRandom(1920);
        y = getRandom(1080);

        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_attacker.png");
    }

    public TankEnemy(float x, float y){
        this.x = x;
        this.y = y;
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
        x = x + (dirX * 1 * dt); // TODO : 1 = speed value
        y = y + (dirY * 1 * dt); // TODO : 1 = speed value ==> make variable

    }

}
