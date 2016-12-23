//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spaceraider.entities.Drones;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.entities.Bullet;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.player.Player;
import com.spaceraider.game.Game;

import java.util.ArrayList;
import java.util.List;

public class Drone extends SpaceObject {
    private List<Bullet> bullets;
    private int speed;
    private float shootSpeed;
    private float timeBullet;
    private float x;
    private float y;
    private Player player;
    private Sprite playerSprite;
    private float angle;
    private Game game;
    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_drone.png");

    public Drone(float x, float y, Player player) throws InterruptedException {
        this.playerSprite = player.getPlayerSprite();
        this.bullets = new ArrayList();
        this.angle = 2.0F;
        this.x = x - 15.0F;
        this.y = y - 15.0F;
        this.batch.begin();
        this.batch.end();
        this.speed = 5;
        this.shootSpeed = 0.8F;
        this.player = player;
    }
    public Drone(float x, float y, Player player, Game game) throws InterruptedException {
        this.playerSprite = player.getPlayerSprite();
        this.bullets = new ArrayList();
        this.angle = 2.0F;
        this.x = x - 15.0F;
        this.y = y - 15.0F;
        this.batch.begin();
        this.batch.end();
        this.speed = 5;
        this.shootSpeed = 0.8F;
        this.player = player;
        this.game = game;
    }





    public void update(float dt) {
        this.shoot(dt);
        this.orbitDrone();
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(this.texture, this.x, this.y);
        batch.end();
    }

    public void shoot(float dt) {
        if(this.timeBullet > this.shootSpeed) {
            this.player.addDroneBullet(this.x, this.y);
            this.timeBullet = 0.0F;
        } else {
            this.timeBullet += dt;
        }

    }

    public void orbitDrone() {
        float angle = this.getAngle();
        float centerX = this.playerSprite.getX() + this.playerSprite.getWidth() / 2.0F;
        float centerY = this.playerSprite.getY() + this.playerSprite.getHeight() / 2.0F;
        float speed = 10.0F;
        float rate = 5.0F;
        float circleX = (float)(Math.cos((double)this.getAngle()) * (double)this.playerSprite.getWidth() + (double)centerX);
        float circleY = (float)(Math.sin((double)this.getAngle()) * (double)this.playerSprite.getHeight() + (double)centerY);
        angle = this.getAngle() + speed * (rate / 1000.0F) % 360.0F;
        if(angle >= 360.0F) {
            angle = 0.0F;
        }

        this.setAngle(angle);
        this.setX(circleX);
        this.setY(circleY);
    }

    public Game getGame() {
        return game;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setX(float x) {
        this.x = x - 15.0F;
    }

    public void setY(float y) {
        this.y = y - 15.0F;
    }
}
