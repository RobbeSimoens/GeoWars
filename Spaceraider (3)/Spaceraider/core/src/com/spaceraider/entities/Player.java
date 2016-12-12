package com.spaceraider.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.spaceraider.entities.enemies.StandardEnemy;
import com.spaceraider.game.Spaceraider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin on 9/11/2016.
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Player extends SpaceObject{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean space;
    private List<Bullet> bullets;
    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private List<StandardEnemy> enemies; // TODO ; fix to interface !!!!!

    SpriteBatch batch = new SpriteBatch();
    Texture img = new Texture("core/assets/playership.png");
    Sprite playerSprite = new Sprite(img);
    //Bullet bullet = new Bullet(x,y, Gdx.input.getX(),Gdx.input.getY());
    //Bullet bullet = new Bullet(900,500, 900,500);

    public Player() throws InterruptedException {
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<StandardEnemy>();
        x = Spaceraider.WIDTH / 2;
        y = Spaceraider.HEIGHT / 2;
        /*TODO fix this line of code because the angle is not correct when starting a new game*/
        radians = MathUtils.PI / 2;
        batch.begin();
        playerSprite.setPosition(x,y);
        batch.end();
        maxSpeed = 500;
        acceleration = 500;
        deceleration = 250;
        rotationSpeed = 300;
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

    public void setSpace(boolean b){space = b; }

    public void update(float dt){
            for (int i = 0; i < bullets.size(); i++) {

                Bullet bullet = bullets.get(i);
                if(!bullet.isRemove())
                {
                    bullet.update(dt);
                    bullet.render(batch);
                }
            }

        for (int i = 0; i < enemies.size(); i++) {

            StandardEnemy enemy = enemies.get(i);

            enemy.update(dt);
            enemy.render(batch);
        }


        // System.out.println(x + ";" + y);
        //System.out.println(Gdx.input.getX()+ ";"+ Gdx.input.getY());
        /*Met graden de rotatie naar links of rechts gaan bepalen*/
        if(left){
            x = x - 10;
        }else if(right){
            x = x + 10;
        }
        /*Acceleration, boost your speed*/
        if(up){
           y = y + 10;
        }
        if(down){
            y = y - 10;
        }
        if(space){
            bullets.add(new Bullet(x,y,Gdx.input.getX(),Gdx.input.getY(), this));
        }
        /*This is for slowing down over time*/
        float vec = (float) Math.sqrt(dx*dx+dy*dy);
        if(vec > 0){
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        /*Buzz Lightyear is the only one allowed to use lightspeed, so if you are at max speed, this wont go make you faster*/
        if(vec > maxSpeed){
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }
        /*Calculating the position*/
        x += dx * dt;
        y += dy * dt;
        batch.begin();
        playerSprite.draw(batch);
        playerSprite.setPosition(x,y);
        playerSprite.setRotation(radians);
        batch.end();
        /*Wrap it all around the screen, maw je kan door het scherm vliegen en er langs de andere kant terug uit komen :)*/
        wrap();
    }

    public void removeBullet(Bullet bullet){
        System.out.println(bullets);
        bullets.remove(bullet);
        System.out.println(bullets);
    }


    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }


}
