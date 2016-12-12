package com.spaceraider.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kevin on 10/11/2016.
 */
public class Bullet extends SpaceObject {
    private float speedX = 1000;
    private float x, y;
    float mx, my;
    private static SpriteBatch batch;
    private static Texture texture;
    private boolean remove;
    float dirX;
    float dirY;
    private Player player;
        // TRYING

    private int speed = 5;

    public Bullet(float x, float y,float mouseX, float mouseY, Player player){ // x,y = player
        this.x = x;
        this.y = y;
        this.mx = mouseX;
        this.my = 1080 - mouseY;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/bullet.png");
        this.player = player;
        setDirection();

        // dit was constructor.
        // nu: om de zoveel ms moet je aan bullet vragen om zijn NIEUWE
        // pos te berekenen / interne x/y class state var updaten.
        // en laten redrawen met nieuwe pos.


    }
    public void setDirection(){
        dirX = mx - x;
        dirY = my - y;
    }

    public void move(float dt){
        double destinationLength = Math.sqrt(dirX * dirX + dirY * dirY);
        x = x + (dirX * speed * dt);
        y = y + (dirY * speed * dt);

    }

    public void update(float dt){

        if(!remove) {
            move(dt);
            // System.out.println("Bullet fired");
            // System.out.println("X is " + x + " y is " + y);
            System.out.println("MouseX is " + mx + " MouseY is " + my);
            Bullet b = new Bullet(x, y, Gdx.input.getX(), Gdx.input.getY(), player);
            b.render(batch);
            //render(,x,y);
        /*Bullets niet eindeloos in het spel laten*/
            if (x > Gdx.graphics.getWidth() || y > Gdx.graphics.getHeight()) {
                remove = true;
                player.removeBullet(this);
                System.out.println("Bullet removed");
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
