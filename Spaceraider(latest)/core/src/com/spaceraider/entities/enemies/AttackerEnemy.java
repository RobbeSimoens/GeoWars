package com.spaceraider.entities.enemies;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.Player;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enums.EnemyType;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by ERR0R on 9-11-2016.
 */
public class AttackerEnemy extends SpaceObject implements Enemy{
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
    private Rectangle rect;
    private List<EnemyBullet> bullets;
    private float timeAux;

    public AttackerEnemy(Player player) {
        initialize();
        this.player = player;
        setDirection();
        rand = new Random();
        x = getRandom(1920);
        y = getRandom(1080);
        bullets = new ArrayList<EnemyBullet>();
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_attacker.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
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
        speed = 50;
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

        if (timeAux > 1) {
            shoot(this);
            timeAux = 0;
        } else {
            timeAux += dt;
        }

        for (int i = 0; i < bullets.size(); i++) {

            EnemyBullet bullet = bullets.get(i);
            if(!bullet.isRemove())
            {
                bullet.update(dt);
                bullet.render(batch);
            }
        }
        setDirection();
        move(dt);
        AttackerEnemy b = new AttackerEnemy(x,y);
        b.render(batch);
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

    public float getRandom(int maxval)
    {
        return (float) rand.nextInt(maxval);
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void shoot(AttackerEnemy shooter){
        bullets.add(new EnemyBullet(x,y,player.getX(),player.getY(), shooter, player));
    }

    @Override
    public Rectangle getRectangle() {
        return rect;
    }

    @Override
    public void reduceHitpoints() {
        hitpoints = hitpoints - 1;
        if(hitpoints ==  0){
            player.removeEnemy(this);
        }
    }

    public void removeBullet(EnemyBullet enemyBullet) {
        bullets.remove(enemyBullet);
    }
}
