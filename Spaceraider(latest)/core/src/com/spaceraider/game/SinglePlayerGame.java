package com.spaceraider.game;


import com.spaceraider.desktop.*;
import com.spaceraider.Database.*;
import com.spaceraider.GUI.AfterGamePanel;
import com.spaceraider.GUI.Frame;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.entities.enemies.*;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;
import com.spaceraider.entities.orbs.Orb;
import com.spaceraider.entities.player.SinglePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by robbe on 12/21/2016.
 */
public class SinglePlayerGame extends Game {
    private SinglePlayer player;
    private Database database;
    private AfterGamePanel afterGamePanel;


    private List<Enemy> enemies;
    private List<Orb> orbs;

    private SpriteBatch batch;
    private Random rand;
    private int score;


    private boolean slowed;
    private boolean inverted;
    private boolean rapidFire;
    private boolean nuke;
    private boolean silenced;
    private float timePowerDown;
    private float timePowerUp;

    private String scoreDisplayer;
    private String hitpointsDisplayer;
    private String showUser;
    private BitmapFont bitmapFontHitpoints;
    private BitmapFont bitmapFontScore;
    private BitmapFont bitmapFontUser;
    private String username;
    private Frame frame ;
    private int id;

    private float timeToSpawn;
    private float spawntimer;
    private int spawnCounter;
    private int shield;


    private Sprite playerSprite;

    public SinglePlayerGame(String username, int id) throws InterruptedException {
        super();
        this.id = id;
        this.username = username;
        init();

    }

    private void init() throws InterruptedException {
        player = new SinglePlayer(this);
        enemies = new ArrayList<Enemy>();
        orbs = new ArrayList<Orb>();

        batch = player.getBatch();
        playerSprite = player.getPlayerSprite();
        rand = new Random();

        silenced=false;
        nuke=false;
        slowed=false;
        inverted=false;
        rapidFire = false;

        hitpointsDisplayer = "hitpoints:   " + player.getHitpoints();
        scoreDisplayer =  "score:   " +  score;
        showUser = "com.spaceraider.User : " + username;

        bitmapFontHitpoints = new BitmapFont();
        bitmapFontScore = new BitmapFont();
        bitmapFontUser = new BitmapFont();

        spawntimer = 3f;
        spawnCounter = 0;
        shield = 0;



    }

    @Override
    public List<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void update(float dt) {

        checkForPowers(dt);
        checkOrbCollision();
        player.update(dt);
        handleEnemies(dt);
        handleInput();
        handleOrbs(dt);
        drawComponents();
        handleSpawn(dt);

    }

    private void handleEnemies(float dt) {
        for (int i = 0; i < enemies.size(); i++) {

            Enemy enemy = enemies.get(i);

            enemy.update(dt);
            enemy.render(batch);
        }
    }

    private void handleOrbs(float dt) {
        for (int i = 0; i < orbs.size(); i++) {
            orbs.get(i).checkLifetime(dt);
            if(orbs.get(i).isExpired())
            {
                orbs.remove(orbs.get(i));
                System.out.println("orb expired");
            }
            else{orbs.get(i).render(batch);}

        }
    }

    private void handleSpawn(float dt){
        if (timeToSpawn > spawntimer) { // SPAWNING
            spawn();
            timeToSpawn = 0;
        } else {
            timeToSpawn += dt;
        }
    }



    private void spawn(){
        int spawn = rand.nextInt(100);
         if(spawn < 60 )
        {
            enemies.add(new StandardEnemy(player));
        }
        else if (spawn < 70)
        {
            enemies.add(new TankEnemy(player));
        }
        else if (spawn < 80)
        {
            enemies.add(new AttackerEnemy(player));
        }
        else if (spawn < 90)
        {
            enemies.add(new PowerdownEnemy(player));
        }
        else
        {
            enemies.add(new PowerupEnemy(player));
        }

        adjustSpawnTimer();


    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void handleInput() {
        if(!inverted)
        {
            if (left) {
                player.setX(player.getX() - player.getSpeed());
                player.getRect().setX(player.getX());
                player.getDrone().setX(player.getX());

            } else if (right) {
                player.setX(player.getX() + player.getSpeed());
                player.getRect().setX(player.getX());
                player.getDrone().setX(player.getX());
            }
            if (up) {
                player.setY(player.getY() + player.getSpeed());
                player.getRect().setY(player.getY());
                player.getDrone().setY(player.getY());
            }
            if (down) {
                player.setY(player.getY() - player.getSpeed());
                player.getRect().setY(player.getY());
                player.getDrone().setY(player.getY());
            }
        }
    }

    private void checkOrbCollision() {
        if (orbs.size() > 0) {
            for (int i = 0; i < orbs.size(); i++) {
                if (player.getRect().overlaps(orbs.get(i).getRectangle())) {
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
                    }

                    orbs.remove(orbs.get(i));
                    score += 10;
                    scoreDisplayer = "score:    " + score;
                    // TODO : add powerup
                }
            }
        }
    }

    public void addOrb(Orb orb){
        System.out.println("Orb spawned");
        orbs.add(orb);
    }

    public void adjustSpawnTimer() {
        spawnCounter++;
        if (spawnCounter > 10f) {
            if (spawntimer > 1f) {
                spawntimer =  spawntimer - 0.5f;
            }

        }
    }

    public void reduceEnemyHitpoints(Enemy enemy) {
        enemy.reduceHitpoints();
    }

    public void reduceHitpoints(){
        if(player.getHitpoints() > 1)
        {
            player.reduceHitpoints();
            hitpointsDisplayer = "hitpoints:    " + player.getHitpoints();
        }
        else
        {
            database = new Database();
            if(database.getHighscoreUser(id) < score){
                database.updateHighscore(id,score);
            }
            System.out.println("game ended");

        }

    }

    private void checkForPowers(float dt) {
        if (slowed) {
            Slowed(dt);
        }
        if (inverted) {
            Inverted(dt);
        }
        if (silenced) {
            Silenced(dt);
        }
        if (rapidFire) {
            rapidFireInc(dt);
        }
        if (nuke) {
            nukeMe();
        }
        if (shield == 2) {

        }
    }

    private void Slowed(float dt){
        if(timePowerDown <= 5){
            player.setSpeed(2);
            timePowerDown+=dt;
        }else{
            player.setSpeed(5);
            timePowerDown = 0;
            slowed =false;
        }
    }

    private void nukeMe() {

        for(int i = 0; i <enemies.size();i++){
            enemies.get(i).dropOrb();
            enemies.remove(enemies.get(i));
        }
        nuke = false;
    }

    private void Inverted(float dt) {
        if (timePowerDown <= 5) {
            if (left) {
                player.setX(player.getX() + player.getSpeed());
                player.getRect().setX(player.getX());
                player.getDrone().setX(player.getX());
            } else if (right) {
                player.setX(player.getX() - player.getSpeed());
                player.getRect().setX(player.getX());
                player.getDrone().setX(player.getX());
            }

            if (up) {
                player.setY(player.getY() - player.getSpeed());
                player.getRect().setY(player.getY());
                player.getDrone().setY(player.getY());
            }
            if (down) {
                player.setY(player.getY() + player.getSpeed());
                player.getRect().setY(player.getY());
                player.getDrone().setY(player.getY());
            }
            timePowerDown +=dt;
        }else {
            handleInput();
            timePowerDown = 0;
            inverted = false;
        }
    }

    private void rapidFireInc(float dt) {
        if (timePowerUp <= 5) {
            player.setShootSpeed(0.4f);
            if (player.getTimeBullet() > player.getShootSpeed()) {

                player.makeBullet();
                player.setTimeBullet(0);
            } else {

                player.setTimeBullet(player.getTimeBullet() + dt);
            }
            timePowerUp += dt;

        } else {
            player.setShootSpeed(0.8f);
            timePowerUp = 0;
            rapidFire = false;
        }

    }
    private void Silenced(float dt) {
        if (timePowerDown <= 5) {

            timePowerDown += dt;
        } else {
            player.shoot(dt);
            timePowerDown = 0;
            silenced = false;
        }

    }

    public void drawComponents() {
        batch.begin();
        bitmapFontUser.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        bitmapFontUser.draw(batch, showUser, 20, 20);
        bitmapFontHitpoints.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        bitmapFontHitpoints.draw(batch, hitpointsDisplayer, 150, 20);
        bitmapFontScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        bitmapFontScore.draw(batch, scoreDisplayer, 320, 20);
        playerSprite.draw(batch);
        playerSprite.setPosition(player.getX(), player.getY());
        batch.end();
    }

    public boolean isRapidFire() {
        return rapidFire;
    }

    public boolean isSilenced() {
        return silenced;
    }
}
