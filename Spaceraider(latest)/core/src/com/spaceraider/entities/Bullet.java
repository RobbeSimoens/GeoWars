package com.spaceraider.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.Drones.Drone;
import com.spaceraider.entities.enemies.Enemy;
import com.spaceraider.entities.player.Player;
import com.spaceraider.game.Game;

import java.util.List;

/**
 * Created by ERROR/Quinten on 10/11/2016.
 */
public class Bullet extends SpaceObject {
    private float speedX = 1000;
    private float x, y;
    private float mx, my;
    private static SpriteBatch batch;
    private static Texture texture;
    private boolean remove;
    //private float timeSpent;
    float dirX;
    float dirY;
    private Player player;
    private Rectangle rect;
    private Game game;
    private String side;

    private float speed = 40;

    public Bullet(float x, float y, float mouseX, float mouseY, Player player, Game game){ // x,y = player
        this.game = game;
        this.x = x;
        this.y = y;
        this.mx = mouseX;
        this.my = 1080 - mouseY;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_green.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
        this.player = player;
        setDirection();
        side = null;
    }

    public Bullet(float x, float y, float mouseX, float mouseY, Drone drone) { // x,y = drone
        this.game = drone.getGame();

        this.x = x;
        this.y = y;
        this.mx = mouseX;
        this.my = 1080 - mouseY;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_green.png");
        rect = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        setDirection();
        side = null;

    }
    public Bullet(float x, float y, float mouseX, float mouseY, Player player, Game game, String side){ // x,y = player
        this.game = game;
        this.side = side;
        this.x = x;
        this.y = y;
        this.mx = mouseX;
        this.my = 1080 - mouseY;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_green.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
        this.player = player;
        setDirection();
    }


    public void setDirection(){
        dirX = mx - x;
        dirY = my - y;
    }

    public void bulletMove(float dt){
            double destinationLength = Math.sqrt(dirX * dirX + dirY * dirY);

            x += (dirX * speed *dt ) / destinationLength *10;
            rect.setX(x);
            y += (dirY * speed *dt) / destinationLength *10;
            rect.setY(y);
    }

    public void update(float dt){
        checkCollision();
        if(side != null)
        {
            bulletMove(dt);
            if(side.equals("left"))
            {
                if (x > Gdx.graphics.getWidth() / 2 - 27 || y > Gdx.graphics.getHeight()) {
                    remove = true;
                    player.removeBullet(this);
                }
            }
            else
            {
                if (x <  Gdx.graphics.getWidth() / 2  || y > Gdx.graphics.getHeight()) {
                    remove = true;
                    player.removeBullet(this);
                }
            }
        }
        else{
            if(!remove) {
                bulletMove(dt);

                if (x > Gdx.graphics.getWidth() || y > Gdx.graphics.getHeight()) {
                    remove = true;
                    player.removeBullet(this);
                }
            }
        }

    }

    public void update(float dt, String side)
    {

    }

    private void checkCollision() {

        if (!game.isMultiplayer())
        {
            loopOverEnemies(game.getEnemies());
        }
        else { // SORRY FOR THIS
            loopOverEnemies(game.getPlayer1().getEnemies());
            loopOverEnemies(game.getPlayer2().getEnemies());
        }
    }

    public void loopOverEnemies(List<Enemy> enemies){
        if(enemies.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                if (rect.overlaps(enemies.get(i).getRectangle())) {
                    player.removeBullet(this);
                    player.reduceEnemyHitpoints(enemies.get(i));
                }
            }
        }
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }


    public boolean isRemove() {
        return remove;
    }
}
