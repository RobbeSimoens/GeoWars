package com.spaceraider.entities.Drones;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.entities.Bullet;
import com.spaceraider.entities.Player;
import com.spaceraider.entities.SpaceObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quinten on 19/12/2016.
 */
public class Drone extends SpaceObject{
    private List<Bullet> bullets;
    private int speed;
    private float shootSpeed;
    private float timeBullet;
    private float x,y;
    private float angle = 2;
    private Player player; // TODO : need this ????
    private Sprite playerSprite;



    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_drone.png");



    public Drone(float x , float y, Player player, Sprite playerSprite) throws InterruptedException {

        bullets = new ArrayList<Bullet>();
        this.x = x;
        this.y = y;
        batch.begin();
        batch.end();
        speed = 5;
        shootSpeed = 0.8f;
        this.player = player;
        this.playerSprite = playerSprite;

    }
    public void update(float dt){
        orbitDrone();
        shoot(dt);
    }

    public void orbitDrone(){
        float angle = getAngle();
        float centerX = playerSprite.getX() + (playerSprite.getWidth() / 2);
        float centerY = playerSprite.getY() + (playerSprite.getHeight() / 2);
        float speed = 10f;
        float rate = 5f;
        float circleX = (float) (Math.cos(getAngle()) *
                (playerSprite.getWidth()) + centerX);
        float circleY = (float) (Math.sin(getAngle()) *
                (playerSprite.getHeight()) + centerY);
        angle = getAngle() + (speed * (rate/1000)) % 360;
        if (angle >= 360) {
            angle = 0;
        }
        setAngle(angle);
        setX(circleX);
        setY(circleY);
        System.out.println(centerX + " " + centerY);
    }


    public void render (SpriteBatch batch){
        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
    }


    public void shoot(float dt){

        if(timeBullet> shootSpeed){
            player.addDroneBullet(x,y);
            timeBullet = 0; // laat alles automatich shieten
        }else{
            timeBullet +=dt;
        }
    }

    public void setX(float x) {
        this.x = x -15;
    }

    public void setY(float y) {
        this.y = y -15;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }
}
