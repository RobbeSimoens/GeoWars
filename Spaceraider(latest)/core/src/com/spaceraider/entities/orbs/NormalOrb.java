package com.spaceraider.entities.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enums.Powerdown;
import com.spaceraider.entities.enums.Powerup;

/**
 * Created by robbe on 12/19/2016.
 */
public class NormalOrb extends SpaceObject implements Orb{

    private float x,y;
    private static SpriteBatch batch;
    private static Texture texture;
    private Rectangle rect;
    private float lifetime;
    private float timeAlive;
    private boolean expired;

    public NormalOrb(float x, float y) {
        this.x = x;
        this.y = y;
        lifetime = 5f;
        expired = false;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_normal-orb.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
        System.out.println("x :     " + x + "      y      : " + y + "   texture width   :" + texture.getWidth() + "texture height : " + texture.getHeight());

    }

    public void render(SpriteBatch batch){
        if(!expired) {
            batch.begin();
            batch.draw(texture,x,y);
            batch.end();
        }

    }

    @Override
    public Powerdown getPowerdown() {
        return null;
    }

    @Override
    public Powerup getPowerup() {

        return null;
    }


    @Override
    public Rectangle getRectangle() {
        return rect;
    }

    @Override
    public void checkLifetime(float dt) {
        if(lifetime >= timeAlive)
        {
            timeAlive += dt;
        }
        else
        {
            expired = true;
        }

    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    public Rectangle getRect(){
        return rect;
    }

    public void update(float dt)
    {
        NormalOrb b = new NormalOrb(x,y);
        b.render(batch);
    }


}
