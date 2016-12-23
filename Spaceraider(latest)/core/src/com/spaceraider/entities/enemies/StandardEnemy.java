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
import com.spaceraider.entities.orbs.NormalOrb;

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
    private int speed;
    private Rectangle rect ;

    public StandardEnemy(Player player) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        x = getRandom(1920);
        y = getRandom(1080);

        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_standard.png");

        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
    }

    public StandardEnemy (Player player, String side) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        if(side.equals("left"))
        {
            x = getRandom(960);
            y = getRandom(540);
        }
        else{
            x = getRandom(960) + 960;
            y = getRandom(540) + 540;
        }

        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_standard.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
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
        speed = 200;

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
        player.addOrb(new NormalOrb(x,y));
    }
}
