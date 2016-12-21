package com.spaceraider.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.Drones.Drone;
import com.spaceraider.entities.enemies.*;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.orbs.Orb;
import com.spaceraider.game.Spaceraider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ERR0R/Quinten on 9/11/2016.
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Player extends SpaceObject{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private int speed;
    private float timeToSpawn;
    private float timeBullet;
    private float timePowerUp;
    private float timePowerDown;
    private float shootSpeed;
    private int shield;
    private Random rand;
    private int spawntimer;
    private int spawnCounter;
    private Rectangle rect;
    private int score;
    private Drone drone;
    private int hitpoints;
    private boolean slowed;
    private boolean inverted;
    private boolean rapidFire;
    private boolean nuke;
    private boolean silenced;



    private String scoreDisplayer;
    private String hitpointsDisplayer;
    BitmapFont bitmapFontHitpoints;
    BitmapFont bitmapFontScore;

    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Orb> orbs;

    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_playership.png");
    Sprite playerSprite = new Sprite(texture);

    public Player() throws InterruptedException {
        rand = new Random();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        orbs = new ArrayList<Orb>();
        x = Spaceraider.WIDTH / 2;
        y = Spaceraider.HEIGHT / 2;
        drone = new Drone(x,y,this, playerSprite);
        batch.begin();
        playerSprite.setPosition(x,y);
        batch.end();
        rect = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        speed = 5;
        spawntimer = 3;
        spawnCounter = 0;
        shootSpeed = 0.4f;
        shield = 0;
        hitpoints = 10;
        hitpointsDisplayer = "hitpoints:   " + hitpoints;
        scoreDisplayer =  "score:   " +  score;
        silenced=false;
        nuke=false;
        slowed=false;
        inverted=false;
        rapidFire = false;
        bitmapFontHitpoints = new BitmapFont();
        bitmapFontScore = new BitmapFont();

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
        rotateShip();
        checkBulletCollision();
        checkOrbCollision();
        checkForPowerUp(dt);
        handleBullets(dt);
        handleEnemies(dt);
        handleOrbs(dt);
        handleDrone(dt);
        handleInput();
        drawComponents();


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


    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    private void makeBullet(){
        bullets.add(new Bullet(x ,y ,Gdx.input.getX(),Gdx.input.getY(), this));
    }


// </editor-fold>

//<editor-fold desc="Orbs">
private void checkBulletCollision() {
    if(enemies.size() > 0) {
        for (int i = 0; i < enemies.size(); i++) {
            if (rect.overlaps(enemies.get(i).getRectangle())) {
                enemies.get(i).dropOrb();
                enemies.remove(enemies.get(i));
                reduceHitpoints();
                System.out.println("WE ARE HIT !!!!");
                // TODO: implement een end game hier

            }
        }
    }
}
    private void checkOrbCollision() {
    if (orbs.size() > 0)
    {
        for (int i = 0; i < orbs.size(); i++)
        {
            if (rect.overlaps(orbs.get(i).getRectangle()))
            {
                if(orbs.get(i).getPowerdown() == Powerdown.INVERTED) {
                    inverted = true;
                }else if(orbs.get(i).getPowerdown() == Powerdown.SLOWED){
                    slowed= true;
                }else if (orbs.get(i).getPowerdown() == Powerdown.SILENCED){
                    silenced = true;
                }

                orbs.remove(orbs.get(i));
                score += 10;
                scoreDisplayer = "score:    " + score;
                System.out.println(score);
                // TODO : add powerup
            }
        }
    }
}

    private void handleOrbs(float dt) {
        for (int i = 0; i < orbs.size(); i++) {
            orbs.get(i).render(batch);
        }
        if (timeToSpawn > spawntimer) { // SPAWNING
            spawn();
            timeToSpawn = 0;
        } else {
            timeToSpawn += dt;
        }
    }

    public void addOrb(Orb orb){
        System.out.println("Orb spawned");
        orbs.add(orb);
    }
// </editor-fold>

//<editor-fold desc="Drone">
    public void handleDrone(float dt) {
    drone.update(dt);
    drone.render(batch);
}

    public void addDroneBullet(float x, float y){
        bullets.add(new Bullet(x,y, 1366 - Gdx.input.getX() - 20 ,768 - Gdx.input.getY() - 40, this)); // Minus 20 & 40 for balancing the drone position to the spaceship
    }




// </editor-fold>

//<editor-fold desc="Enemies">
    private void handleEnemies(float dt) {
    for (int i = 0; i < enemies.size(); i++) {

        Enemy enemy = enemies.get(i);

        enemy.update(dt);
        enemy.render(batch);
    }
}

    private void spawn(){
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

    public void adjustSpawnTimer(){ // TODO : fix me
        spawnCounter ++;
        if(spawnCounter > 10)
        {
            if (spawntimer > 0.5)
            {
                spawntimer = (int) (spawntimer - 0.5);
            }

        }
    }


    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void reduceEnemyHitpoints(Enemy enemy) {
        enemy.reduceHitpoints();
    }
// </editor-fold>

//<editor-fold desc="Player">
    public void shoot(float dt){
    if(timeBullet> shootSpeed){
        makeBullet();
        timeBullet = 0; // laat alles automatich shieten
    }else{
        timeBullet +=dt;
    }
}

    public void reduceHitpoints(){
        if(hitpoints > 1)
        {
            hitpoints--;
            hitpointsDisplayer = "hitpoints:    " + hitpoints;
        }
        else
        {
            System.out.println("Player died"); // TODO: implement dying
        }

    }

    public void handleInput() {
        if (left) {
            x = x - speed;
            rect.setX(x);
            drone.setX(x);
        } else if (right) {
            x = x + speed;
            rect.setX(x);
            drone.setX(x);
        }
        if (up) {
            y = y + speed;
            rect.setY(y);
            drone.setY(y);
        }
        if (down) {
            y = y - speed;
            rect.setY(y);
            drone.setY(y);
        }
    }

    public void rotateShip(){

        float angle = (float) Math.toDegrees(Math.atan2((Gdx.input.getX() - 15) - playerSprite.getX(),
                (742 - Gdx.input.getY()) - playerSprite.getY()));
        if (angle < 0)
            angle += 360;
        playerSprite.setRotation(angle * -1);
    }
// </editor-fold>

//<editor-fold desc="PowerUp/PowerDown">
    private void Slowed(float dt){
        if(timePowerDown <= 5){
            speed= 2;
            timePowerDown+=dt;
        }else{
            speed=5;
            timePowerDown = 0;
            slowed =false;
        }
    }


    private void checkForPowerUp(float dt) {
        if (slowed == true) {
            Slowed(dt);
        }
        if (inverted == true) {
            Inverted(dt);
        }
        if (silenced == true) {
            //Silenced(dt);
        }
    }

    private void Inverted(float dt) {
        if (timePowerDown <= 5) {
            if (left) {
                x = x + speed;
                rect.setX(x);
                drone.setX(x);
            } else if (right) {
                x = x - speed;
                rect.setX(x);
                drone.setX(x);
            }
        /*Acceleration, boost your speed*/
            if (up) {
                y = y - speed;
                rect.setY(y);
                drone.setY(y);
            }
            if (down) {
                y = y + speed;
                rect.setY(y);
                drone.setY(y);
            }
            timePowerDown +=dt;
        }else {

            if (left) {
                x = x - speed;
                rect.setX(x);
                drone.setX(x);
            } else if (right) {
                x = x + speed;
                rect.setX(x);
                drone.setX(x);
            }
            if (up) {
                y = y + speed;
                rect.setY(y);
                drone.setY(y);
            }
            if (down) {
                y = y - speed;
                rect.setY(y);
                drone.setY(y);
            }
            inverted = false;
        }
    }

    private void rapidFire(float dt){
        if(timePowerUp> shootSpeed){
            makeBullet();
            timePowerUp = 0; // laat alles automatich shieten
        }else{

            timePowerUp +=dt;
        }

    }
// </editor-fold>

//<editor-fold desc="Generics">
    public void drawComponents() {
    batch.begin();
    bitmapFontHitpoints.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    bitmapFontHitpoints.draw(batch, hitpointsDisplayer, 20, 20);
    bitmapFontScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    bitmapFontScore.draw(batch, scoreDisplayer, 150, 20);
    playerSprite.draw(batch);
    playerSprite.setPosition(x, y);
    batch.end();
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
