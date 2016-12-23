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
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
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
    private boolean slowed;
    private boolean inverted;
    private boolean rapidFire;
    private boolean nuke;
    private boolean silenced;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    public boolean spawnStandard;
    public boolean spawnTank;
    public boolean spawnAttacker;
    public boolean spawnPowerupenemy;
    public boolean spawnPowerdownenemy;
    public boolean spawnInverted;
    public boolean spawnSlow;
    public boolean spawnSilenced;
    private boolean spawned;

    private String side;
    private String powerDown;

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
    private List<String> spawnList;

    private BitmapFont bitmapFontScore;
    private String scoreDisplayer;
    private String hitpointsDisplayer;
    private BitmapFont bitmapFontHitpoints;
    private String shieldDisplayer;
    private String creditDisplayer;
    private String controlsDisplayer;
    private BitmapFont bitmapFontShield;
    private BitmapFont bitmapFontCredit;
    private BitmapFont bitmapFontControls;


    private int score;
    private int credit;


    MultiPlayerGame game;

    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_playership.png");
    Sprite playerSprite = new Sprite(texture);
    private float timePowerUp;
    private float timePowerDown;


    public MultiPlayer(MultiPlayerGame game, float x , float y, String side) throws InterruptedException {
        this.game = game;
        this.side = side;
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        orbs = new ArrayList<Orb>();
        spawnList = new ArrayList<String>();

        // spawnList.add("standard");


        rand = new Random();

        this.x = x;
        this.y = y;
        drone = new Drone(x,y,this, game);
        batch.begin();
        playerSprite.setPosition(x,y);
        batch.end();
        rect = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        speed = 5;

        hitpoints = 10;
        credit = 100;

        bitmapFontScore = new BitmapFont();
        hitpointsDisplayer = "hitpoints:   " + hitpoints;
        scoreDisplayer = "score:    " + score;
        shieldDisplayer = "shield:      " + shield;
        creditDisplayer = "credit:      " + credit;

        bitmapFontControls = new BitmapFont();
        bitmapFontHitpoints= new BitmapFont();
        bitmapFontCredit = new BitmapFont();
        bitmapFontShield = new BitmapFont();

        spawntimer = 3;
        spawnCounter = 0;


        silenced=false;
        nuke=false;
        slowed=false;
        inverted =false;
        rapidFire = false;

        shootSpeed = 0.4f;
        shield = 0;
        spawned = false;
        powerDown = "";

    }
    public void addToSpawnList(String enemytoSpawn){
        spawnList.add(enemytoSpawn);
    }
    public void setSpawnStandard(boolean b) {spawnStandard = b;}

    public void setSpawnTank(boolean b) {spawnTank = b;}

    public void setSpawnAttacker(boolean b) {spawnAttacker = b;}

    public void setSpawnPowerupenemy(boolean b) {spawnPowerupenemy = b;}

    public void setSpawnPowerdownenemy(boolean b) {spawnPowerdownenemy = b;}

    public void setSpawnInverted(boolean b) {spawnInverted = b;    }

    public void setSpawnSlow(boolean b) {spawnSlow = b;    }

    public void setSpawnSilenced(boolean b) {spawnSilenced = b;}

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
        handleSpawnEnemy(dt);
        handleSpawnPowerdown(dt);
        handleEnemies(dt);
        checkOrbCollision();
        handleOrbs(dt);
        checkEnemyCollision();
        handleDrone(dt);
        checkForPowers(dt);
        wrap(side);
    }

    private void checkEnemyCollision() {
        if (enemies.size() > 0) {
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

    private void handleEnemies(float dt) {
        if (!nuke) { // !nuke
            for (int i = 0; i < enemies.size(); i++) {

                Enemy enemy = enemies.get(i);

                enemy.update(dt);
                enemy.render(batch);
            }
        }
    }

    public void handleSpawnEnemy(float dt){

        if (timeToSpawn > spawntimer) { // SPAWNING
            spawned = false;
            for (String toSpawn : spawnList) {

                if(toSpawn.equals("standard"))
                {
                    enemies.add(new StandardEnemy(this, side));
                }
                else if(toSpawn.equals("tank"))
                {
                    enemies.add(new TankEnemy(this, side));
                }
                else if(toSpawn.equals("powerup"))
                {
                    enemies.add(new PowerupEnemy(this, side));
                }
                else if (toSpawn.equals("powerdown"))
                {
                    enemies.add(new PowerdownEnemy(this, side));
                }
                else
                {
                    enemies.add(new AttackerEnemy(this, side));
                }
            }
            timeToSpawn = 0;
        } else {
            timeToSpawn += dt;
        }
    }
    public void handleSpawnPowerdown(float dt){
        if (timeToSpawn > spawntimer) {
            spawned = false;
            if(powerDown.equals("slow")){
                Slowed(dt);
            }else if(powerDown.equals("silenced")){
                Silenced(dt);
            }else{
                Inverted(dt);
            }
            timeToSpawn = 0;
        }else {
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
    private void handleOrbs(float dt) {
        for (int i = 0; i < orbs.size(); i++) {
            orbs.get(i).render(batch);
        }
    }

    private void checkOrbCollision() {
        if (orbs.size() > 0) {
            for (int i = 0; i < orbs.size(); i++) {
                if (rect.overlaps(orbs.get(i).getRectangle())) {
                    if (orbs.get(i).getPowerdown() == Powerdown.INVERTED) {
                        inverted = true;
                    } else if (orbs.get(i).getPowerdown() == Powerdown.SLOWED) {
                        slowed = true;
                    } else if (orbs.get(i).getPowerdown() == Powerdown.SILENCED) {
                        silenced = true;
                    } else if (orbs.get(i).getPowerup() == Powerup.RAPIDFIRE) {
                        rapidFire = true;
                    } else if (orbs.get(i).getPowerup() == Powerup.NUKE) {
                        nuke = true;
                    } else if (orbs.get(i).getPowerup() == Powerup.SHIELD) {
                        shield = 2;
                        shieldDisplayer = "shield:  " + shield;
                    }

                    orbs.remove(orbs.get(i));
                    score += 10;
                    credit += 1;
                    creditDisplayer = "credit:      " + credit;
                    scoreDisplayer = "score:    " + score;
                    System.out.println(score);
                    // TODO : add powerup
                }
            }
        }
    }


    public void handleInput(){
        if (!inverted) { //TODO :  IMPLEMENT INVERTED HERE
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
        if(!spawned)
        {
            if(spawnAttacker && credit >= 10 ){
                credit = credit -10;
                game.spawn("attacker", side);
                spawned = true;
            }
            else if(spawnStandard && credit >= 7){
                credit = credit -7;
                game.spawn("standard", side);
                spawned = true;
            }
            else if(spawnTank && credit >= 15){
                credit = credit -15;
                game.spawn("tank", side);
                spawned = true;
            }
            else if(spawnPowerdownenemy && credit >= 13){
                credit = credit -13;
                game.spawn("powerdown", side);
                spawned = true;
            }
            else if(spawnPowerupenemy && credit >= 5){
                credit = credit - 5;
                game.spawn("powerup", side);
                spawned = true;
            }
            else if(spawnSlow && credit >= 20){
                credit = credit - 20;
                game.spawn("slow", side);
                spawned = true;
            }
            else if(spawnInverted && credit >= 20){
                credit = credit - 20;
                game.spawn("inverted", side);
                spawned = true;
            }
            else if(spawnSilenced && credit >= 20){
                credit = credit - 20;
                game.spawn("silenced", side);
                spawned = true;
            }
            creditDisplayer = "credit:      " + credit;

        }
    }

    public void shoot(float dt) {
        if (!rapidFire) { // !rapidfire
            if (!silenced) { // !silenced
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

        if(side.equals("left"))
        {
            controlsDisplayer = "Controls : \n" +
                    "ZQSD: move \n" +
                    "F1: standard \n" +
                    "F2: tank \n" +
                    "F3: attacker \n" +
                    "F4: powerdown \n" +
                    "F5: powerup \n" +
                    "F6: slow \n" +
                    "F7: invert \n" +
                    "F8: silence";
            bitmapFontControls.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontControls.draw(batch, controlsDisplayer, 10, 1050);
            bitmapFontHitpoints.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontHitpoints.draw(batch, hitpointsDisplayer, 20, 20);
            bitmapFontScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontScore.draw(batch, scoreDisplayer, 150, 20);
            bitmapFontShield.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontShield.draw(batch, shieldDisplayer, 320, 20);
            bitmapFontCredit.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontCredit.draw(batch, creditDisplayer, 480, 20);
        }
        else
        {
            controlsDisplayer = "Controls : \n" +
                    "arrow keys: move \n" +
                    "num_1: standard \n" +
                    "num_2: tank \n" +
                    "num_3: attacker \n" +
                    "num_4: powerdown \n" +
                    "num_5: powerup \n" +
                    "num_6: slow \n" +
                    "num_7: invert \n" +
                    "num_8: silence";
            bitmapFontControls.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontControls.draw(batch, controlsDisplayer, 975, 1050);
            bitmapFontHitpoints.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontHitpoints.draw(batch, hitpointsDisplayer, Gdx.graphics.getWidth()/ 2 + 20, 20);
            bitmapFontScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontScore.draw(batch, scoreDisplayer, Gdx.graphics.getWidth()/2 + 150, 20);
            bitmapFontShield.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontShield.draw(batch, shieldDisplayer, Gdx.graphics.getWidth()/ 2 + 320, 20);
            bitmapFontCredit.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            bitmapFontCredit.draw(batch, creditDisplayer, Gdx.graphics.getWidth()/ 2 + 480, 20);
        }

        batch.end();
    }

    private void makeBullet() {
        bullets.add(new Bullet(x, y, Gdx.input.getX(), Gdx.input.getY(), this, game, side));
    }
    private void Slowed(float dt) {
        if (timePowerDown <= 5) {
            speed = 2;
            timePowerDown += dt;
        } else {
            speed = 5;
            timePowerDown = 0;
            slowed = false;
        }
    }


    private void checkForPowers(float dt) {
        if (slowed == true) {
            Slowed(dt);
        }
        if (inverted == true) {
            Inverted(dt);
        }
        if (silenced == true) {
            Silenced(dt);
        }
        if (rapidFire == true) {
            rapidFireInc(dt);
        }
        if (nuke == true) {
            nukeMe();
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
            timePowerDown += dt;
        } else {
            handleInput();
            timePowerDown = 0;
            inverted = false;

        }

    }

    private void Silenced(float dt) {
        if (timePowerDown <= 5) {

            timePowerDown += dt;
        } else {
            shoot(dt);
            timePowerDown = 0;
            silenced = false;
        }

    }


    private void rapidFireInc(float dt) {
        if (timePowerUp <= 5) {
            shootSpeed = 0.2f;
            if (timeBullet > shootSpeed) {
                makeBullet();
                timeBullet = 0; // laat alles automatich shieten
            } else {

                timeBullet += dt;
            }
            timePowerUp += dt;

        } else {
            shootSpeed = 0.4f;
            timePowerUp = 0;
            rapidFire = false;
        }

    }

    private void nukeMe() {

        enemies.clear();
        nuke = false;


    }

    public void rotateShip() {

        float angle = (float) Math.toDegrees(Math.atan2((Gdx.input.getX() - 15) - playerSprite.getX(),
                (1080 - Gdx.input.getY()) - playerSprite.getY()));
        // if (angle < 0)
        //    angle += 360;
        playerSprite.setRotation(angle * -1);
    }

    public void handleDrone(float dt){
        drone.update(dt);
        drone.render(batch);
    }

    @Override
    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }

    @Override
    public void addDroneBullet(float x, float y) {
        bullets.add(new Bullet(x, y, 1920 - Gdx.input.getX() - 20, 1080 - Gdx.input.getY() - 40, this, game, side)); // Minus 20 & 40 for balancing the drone position to the spaceship
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
        if(shield == 0)
        {
            if (hitpoints > 1) {
                hitpoints--;
                hitpointsDisplayer = "hitpoints:    " + hitpoints;
            } else {
                System.out.println("Player died"); // TODO: implement dying
            }
        }
        else
        {
            shield--;
            shieldDisplayer = "shield:      " + shield;
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
        return playerSprite;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void addPowerDown(String powerdown) {
      powerDown = powerdown;
    }

    public void setSlowed(boolean slowed) {
        this.slowed = slowed;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public void setSilenced(boolean silenced) {
        this.silenced = silenced;
    }
    // TODO : refactor under here

    }


