package com.spaceraider.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.enemies.*;
import com.spaceraider.game.Spaceraider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kevin on 9/11/2016.
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Player extends SpaceObject{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private float timeAux;
    private int speed;
    private Random rand;
    private int spawntimer;
    private int spawnCounter;
    private Rectangle rect;

    private List<Bullet> bullets;
    private List<Enemy> enemies;

    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_playership.png");
    Sprite playerSprite = new Sprite(texture);

    public Player() throws InterruptedException {
        rand = new Random();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        x = Spaceraider.WIDTH / 2;
        y = Spaceraider.HEIGHT / 2;
        batch.begin();
        playerSprite.setPosition(x,y);
        batch.end();
        rect = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        speed = 5;
        spawntimer = 3;
        spawnCounter = 0;
    }



    public void setLeft(boolean b ){
        left = b;
    }

    public void setRight(boolean b){
        right = b;
    }

    public void setUp(boolean b ){
        up = b;
    }

    public void setDown(boolean b){
        down = b;
    }


    public void update(float dt){

        checkCollision();
            for (int i = 0; i < bullets.size(); i++) {

                Bullet bullet = bullets.get(i);
                if(!bullet.isRemove())
                {
                    bullet.update(dt);
                    bullet.render(batch);
                }
            }

        for (int i = 0; i < enemies.size(); i++) {

            Enemy enemy = enemies.get(i);

            enemy.update(dt);
            enemy.render(batch);
        }
            if (timeAux > spawntimer) { // SPAWNING
                spawn();
                timeAux = 0;
            } else {
                timeAux += dt;

        }

        if(left){
            x = x - speed;
        }else if(right){
            x = x + speed ;
        }
        if(up){
            y = y + speed;
        }
        if(down){
            y = y - speed;
        }
        batch.begin();
        playerSprite.draw(batch);
        playerSprite.setPosition(x,y);
        batch.end();
        /*Wrap it all around the screen, maw je kan door het scherm vliegen en er langs de andere kant terug uit komen :)*/
        wrap();
    }

    private void checkCollision() {
        if(enemies.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                if (rect.overlaps(enemies.get(i).getRectangle())) {
                    System.out.println("WE ARE HIT !!!!");

                }
            }
        }
    }

    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    public void shoot(){
        bullets.add(new Bullet(x,y,Gdx.input.getX(),Gdx.input.getY(), this));
    }
    public void spawn(){
        int spawn = rand.nextInt(100);
        if(spawn < 60 )
        {
            enemies.add(new StandardEnemy(this));
        }
        else if (spawn < 70)
        {
            enemies.add(new TankEnemy(this));
        }
        else if (spawn < 80)
        {
            enemies.add(new AttackerEnemy(this));
        }
        else if (spawn < 90)
        {
            enemies.add(new PowerdownEnemy(this));
        }
        else
        {
            enemies.add(new PowerupEnemy(this));
        }

        // TODO : fix this method --> adjustSpawnTimer();

    }
    public void adjustSpawnTimer(){
        spawnCounter ++;
        if(spawnCounter > 10)
        {
            if (spawntimer > 0.5)
            {
                spawntimer = (int) (spawntimer - 0.5);
            }

        }
    }



    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void reduceHitpoints(Enemy enemy) {
        enemy.reduceHitpoints();
    }
}
