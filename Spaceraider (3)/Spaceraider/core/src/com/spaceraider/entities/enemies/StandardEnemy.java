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
public class StandardEnemy extends SpaceObject implements Enemy {
    private EnemyType type;
    private int hitpoints;
    private boolean attacks;
    private Powerup powerup;
    private Powerdown powerdown;
    private Status status = Status.NOTHING;
    private static SpriteBatch batch;
    private static Texture texture;
    private float x,y;
    private Random rand;
    private Player player;
    private float dirX,dirY;

    public StandardEnemy(Player player) {
        this.player = player;
        setDirection();
        rand = new Random();
        x = getRandom(1920);
        y = getRandom(1080);

        batch = new SpriteBatch();
        texture = new Texture("core/assets/enemy.png");
        initialize();

    }

    public StandardEnemy(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void initialize() {
        type = EnemyType.STANDARD;
        hitpoints = 1;
        attacks = false;
        powerdown = null;
        powerup = null;
        status = Status.MOVING;


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
    @Override
    public String toString(){
        return "type : " + type + "     Hitpoints : " + hitpoints + "     Attacks: " + attacks + "       " + "    Powerup : " + powerup + "       Powerdown : " + powerdown;
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
