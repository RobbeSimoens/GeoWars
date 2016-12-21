package com.spaceraider.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.Bullet;
import com.spaceraider.entities.Drones.Drone;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enemies.Enemy;
import com.spaceraider.entities.orbs.Orb;
import com.spaceraider.game.SinglePlayerGame;
import com.spaceraider.game.Spaceraider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robbe on 12/21/2016.
 */
public class MultiPlayer extends SpaceObject implements Player{
    private int speed;

    private float timeBullet;


    private float shootSpeed;
    private int shield;



    private Rectangle rect; // STAYS
    private Drone drone;
    private int hitpoints; // STAYS


    private List<Bullet> bullets;


    SinglePlayerGame game;

    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_playership.png");
    Sprite playerSprite = new Sprite(texture);

    public MultiPlayer(SinglePlayerGame game) throws InterruptedException {
        this.game = game;
        bullets = new ArrayList<Bullet>();

        x = Spaceraider.WIDTH / 2;
        y = Spaceraider.HEIGHT / 2;
        drone = new Drone(x,y,this);
        batch.begin();
        playerSprite.setPosition(x,y);
        batch.end();
        rect = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        speed = 5;


        shootSpeed = 0.4f;
        shield = 0;
        hitpoints = 10;
    }



    public void rotateShip(){

        float angle = (float) Math.toDegrees(Math.atan2((Gdx.input.getX() - 15) - playerSprite.getX(),
                (1080 - Gdx.input.getY()) - playerSprite.getY()));
        // if (angle < 0)
        //    angle += 360;
        playerSprite.setRotation(angle * -1);
    }


    public void update(float dt){
        rotateShip();
        shoot(dt);          // IMPLEMENT Q METHOD
        handleBullets(dt);
        handleDrone(dt);

        /*Wrap it all around the screen, maw je kan door het scherm vliegen en er langs de andere kant terug uit komen :)*/
        wrap();
    }


    //<editor-fold desc="Bullets">
    private void handleBullets(float dt) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (!bullet.isRemove()) {
                bullet.update(dt);
                bullet.render(batch);
            }
        }
    }


    public void addOrb(Orb orb){
        game.addOrb(orb);
    }

    public void removeEnemy(Enemy enemy)
    {
        game.removeEnemy(enemy);
    }


    @Override
    public void reduceEnemyHitpoints(Enemy enemy){
        game.reduceHitpoints();
    }



    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    public void makeBullet(){
        bullets.add(new Bullet(x ,y ,Gdx.input.getX(),Gdx.input.getY(), this, game));
    }


// </editor-fold>

//<editor-fold desc="Orbs">


// </editor-fold>

    //<editor-fold desc="Drone">
    public void handleDrone(float dt) {
        drone.update(dt);
        drone.render(batch);
    }

    public void addDroneBullet(float x, float y){
        bullets.add(new Bullet(x,y, 1920 - Gdx.input.getX() - 20 ,1080 - Gdx.input.getY() - 40, this, game)); // Minus 20 & 40 for balancing the drone position to the spaceship
    }

    public void reduceHitpoints(){
        hitpoints--;
    }


// </editor-fold>

//<editor-fold desc="Enemies">



// </editor-fold>

    //<editor-fold desc="SinglePlayer">
    public void shoot(float dt){
        if(timeBullet> shootSpeed){
            makeBullet();
            timeBullet = 0; // laat alles automatich shieten
        }else{
            timeBullet +=dt;
        }
    }




// </editor-fold>

//<editor-fold desc="PowerUp/PowerDown">




    // </editor-fold>

    //<editor-fold desc="Generics">
    public SpriteBatch getBatch() {
        return batch;
    }

    public void setX(float x){
        this.x = x;
    }

    public int getSpeed(){
        return speed;
    }

    public Drone getDrone(){
        return drone;
    }

    public void setY(float y){
        this.y = y;
    }

    public int getHitpoints()
    {
        return hitpoints;
    }

    public void setHitpoints (int hitpoints){
        this.hitpoints = hitpoints;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public float getShootSpeed() {
        return shootSpeed;
    }

    public void setShootSpeed(float shootSpeed) {
        this.shootSpeed = shootSpeed;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }
// </editor-fold>

    }


