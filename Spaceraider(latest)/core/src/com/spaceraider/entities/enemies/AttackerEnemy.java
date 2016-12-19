package com.spaceraider.entities.enemies;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.entities.enums.EnemyType;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.enums.Status;
import com.spaceraider.entities.Player;

import java.util.Random;


/**
 * Created by ERR0R on 9-11-2016.
 */
public class AttackerEnemy implements Enemy{
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
    private int speed;

    public AttackerEnemy(Player player) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        x = getRandom(1920);
        y = getRandom(1080);

        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_standard.png");

    }

    public AttackerEnemy(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void initialize() {
        type = EnemyType.ATTACKER;
        hitpoints = 1;
        attacks = true;
        powerdown = null;
        powerup = null;
        status = Status.MOVING;
        speed = 750;
    }
    @Override
    public String toString(){
        return "type : " + type + "     Hitpoints : " + hitpoints +"     Attacks: " + attacks + "       " + "    Powerup : " + powerup + "       Powerdown : " + powerdown;
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
    public void setDirection(){
        dirX = player.getX() - x;
        dirY = player.getY() - y;
    }

    public void move(float dt){
        double destinationLength = Math.sqrt(dirX * dirX + dirY * dirY);
        x = (float) (x + (dirX * speed * dt) / destinationLength); // TODO : 1 = speed value
        y = (float) (y + (dirY * speed * dt)/ destinationLength); // TODO : 1 = speed value ==> make variable

    }

    public float getRandom(int maxval)
    {
        return (float) rand.nextInt(maxval);
    }
}
