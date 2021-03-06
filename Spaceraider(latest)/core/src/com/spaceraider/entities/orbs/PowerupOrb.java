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
public class PowerupOrb extends SpaceObject implements Orb {
    private float x,y;
    private static SpriteBatch batch;
    private static Texture texture;
    private Rectangle rect;
    private Powerup powerup;

    private float timeAlive;
    private float lifetime;
    private boolean expired;

    public PowerupOrb(float x, float y, Powerup powerup) {
        this.x = x;
        this.y = y;
        lifetime  = 5;
        expired = false;
        this.powerup = powerup;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_powerup-orb.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
    }

    @Override
    public Powerdown getPowerdown() {
        return null;
    }

    @Override
    public Powerup getPowerup() {
        return powerup;
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


    public void update(float dt)
    {
        PowerupOrb b = new PowerupOrb(x,y, powerup);
        b.render(batch);
    }
}
