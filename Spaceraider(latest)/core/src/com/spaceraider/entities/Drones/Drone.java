package com.spaceraider.entities.Drones;

import com.badlogic.gdx.graphics.Texture;
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
    private Player player; // TODO : need this ????



    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_drone.png");


    public Drone(float x , float y, Player player) throws InterruptedException {

        bullets = new ArrayList<Bullet>();
        this.x = x + 20;
        this.y = y + 40;
        batch.begin();
        batch.end();
        speed = 5;
        shootSpeed = 0.8f;
        this.player = player;
    }
    public void update(float dt){
        shoot(dt);
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
        this.x = x + 20;
    }

    public void setY(float y) {
        this.y = y + 40;
    }
}
