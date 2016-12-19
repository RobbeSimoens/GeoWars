package com.spaceraider.entities.Drones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.Bullet;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enemies.Enemy;
import com.spaceraider.game.Spaceraider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Quinten on 19/12/2016.
 */
public class Drone extends SpaceObject{



    private List<Bullet> bullets;
    private int speed;
    private float shootSpeed;
    private float timeBullet;






    SpriteBatch batch = new SpriteBatch();
    Texture texture = new Texture("core/assets/rsz_drone.png");
    Sprite playerSprite = new Sprite(texture);


    public Drone() throws InterruptedException {

        bullets = new ArrayList<Bullet>();

        x = Spaceraider.WIDTH / 2;
        y = Spaceraider.HEIGHT / 2;
        batch.begin();
        playerSprite.setPosition(x,y);
        batch.end();
        speed = 5;
        shootSpeed = 0.4f;

    }



    public void shoot(float dt){

        if(timeBullet> shootSpeed){
            makeBullet();
            timeBullet = 0; // laat alles automatich shieten
        }else{
            timeBullet +=dt;
        }
    }
    public void makeBullet(){


        bullets.add(new Bullet(x ,y , Gdx.input.getX(),Gdx.input.getY(), this));

    }
}
