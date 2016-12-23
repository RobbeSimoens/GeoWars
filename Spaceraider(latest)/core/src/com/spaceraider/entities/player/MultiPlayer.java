package com.spaceraider.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.Bullet;
import com.spaceraider.entities.Drones.Drone;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enemies.*;
import com.spaceraider.entities.orbs.Orb;
import com.spaceraider.game.MultiPlayerGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by robbe on 12/21/2016.
 */
public class MultiPlayer extends SpaceObject implements Player{
    // CONTROLS

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private String side;

    private int speed;
    private float timeBullet;


    private float shootSpeed;
    private int shield;


    private Random rand;
    private Rectangle rect; // STAYS
    private Drone drone;
    private int hitpoints; // STAYS

    private int spawntimer;
    private int spawnCounter;
    private float timeToSpawn;

    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Orb> orbs;


    MultiPlayerGame game;

    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_playership.png");
    Sprite playerSprite = new Sprite(texture);

    private String hitpointsDisplayer;
    BitmapFont bitmapFontHitpoints;

    public MultiPlayer(MultiPlayerGame game, float x , float y, String side) throws InterruptedException {
        this.game = game;
        this.side = side;
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        orbs = new ArrayList<Orb>();

        rand = new Random();

        this.x = x;
        this.y = y;
        drone = new Drone(x,y,this);
        batch.begin();
        playerSprite.setPosition(x,y);
        batch.end();
        rect = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        speed = 5;

        bitmapFontHitpoints = new BitmapFont();
        hitpointsDisplayer = "hitpoints:   " + hitpoints;

        spawntimer = 3;
        spawnCounter = 0;

        shootSpeed = 0.4f;
        shield = 0;
        hitpoints = 10;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void update(float dt)
    {
        drawComponents();
        handleInput();
        shoot(dt);
        handleBullets(dt);
        rotateShip();
        handleSpawn(dt);
        handleEnemies(dt);
        wrap();
    }

    private void handleEnemies(float dt) {
        if (true) { // !nuke
            for (int i = 0; i < enemies.size(); i++) {

                Enemy enemy = enemies.get(i);

                enemy.update(dt);
                enemy.render(batch);
            }
        }
    }

    public void handleSpawn(float dt){
        if (timeToSpawn > spawntimer) { // SPAWNING
            spawn();
            timeToSpawn = 0;
        } else {
            timeToSpawn += dt;
        }
    }
    private void handleBullets(float dt) {
        for (int i = 0; i < bullets.size(); i++) {

            Bullet bullet = bullets.get(i);
            if (!bullet.isRemove()) {
                bullet.update(dt);
                bullet.render(batch);
            }
        }
    }

    public void spawn(){
        int spawn = rand.nextInt(100);
        if(spawn < 60 )
        {
            enemies.add(new StandardEnemy(this, side));
        }
        else if (spawn < 70)
        {
            enemies.add(new TankEnemy(this, side));
        }
        else if (spawn < 80)
        {
            enemies.add(new AttackerEnemy(this, side));
        }
        else if (spawn < 90)
        {
            enemies.add(new PowerdownEnemy(this, side));
        }
        else
        {
            enemies.add(new PowerupEnemy(this, side));
        }
    }

    public void handleInput(){
        if (true) { //TODO :  IMPLEMENT INVERTED HERE
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
    }

    public void shoot(float dt) {
        if (true) { // !rapidfire
            if (true) { // !silenced
                if (timeBullet > shootSpeed) {
                    makeBullet();
                    timeBullet = 0; // laat alles automatich shieten
                } else {
                    timeBullet += dt;
                }
            }
        }

    }

    public void drawComponents(){
        batch.begin();
        playerSprite.draw(batch);
        playerSprite.setPosition(x, y);
        batch.end();
    }

    private void makeBullet() {
        bullets.add(new Bullet(x, y, Gdx.input.getX(), Gdx.input.getY(), this, game, side));
    }

    public void rotateShip() {

        float angle = (float) Math.toDegrees(Math.atan2((Gdx.input.getX() - 15) - playerSprite.getX(),
                (1080 - Gdx.input.getY()) - playerSprite.getY()));
        // if (angle < 0)
        //    angle += 360;
        playerSprite.setRotation(angle * -1);
    }

    @Override
    public void removeBullet(Bullet bullet) {

    }

    @Override
    public void addDroneBullet(float x, float y) {

    }

    @Override
    public float getX() { // FIXED
        return x;
    }

    @Override
    public float getY() { // FIXED
        return y;
    }

    @Override
    public Rectangle getRect() { // FIXED
        return rect;
    }

    @Override
    public void reduceHitpoints() {
        if (hitpoints > 1) {
            hitpoints--;
            hitpointsDisplayer = "hitpoints:    " + hitpoints;
        } else {
            System.out.println("Player died"); // TODO: implement dying
        }

    }

    @Override
    public void removeEnemy(Enemy enemy) { // FIXED
        enemies.remove(enemy);
    }

    @Override
    public void reduceEnemyHitpoints(Enemy enemy) { // FIXED
        enemy.reduceHitpoints();
    }

    @Override
    public void addOrb(Orb orb) { // TODO: needs tweaking
        orbs.add(orb);
    }

    @Override
    public Sprite getPlayerSprite() {
        return null;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }


    // TODO : refactor under here



     /*

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

        // Wrap it all around the screen, maw je kan door het scherm vliegen en er langs de andere kant terug uit komen :)
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
    */

// </editor-fold>

    }


