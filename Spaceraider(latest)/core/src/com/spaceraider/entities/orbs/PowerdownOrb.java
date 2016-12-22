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
public class PowerdownOrb extends SpaceObject implements Orb { // TODO : activate powerups
    private float x,y;
    private static SpriteBatch batch;
    private static Texture texture;
    private Rectangle rect;
    private Powerdown powerdown;

    private float timeAlive;
    private float lifetime;
    private boolean expired;

    public PowerdownOrb(float x, float y, Powerdown powerdown) {
        this.x = x;
        this.y = y;
        lifetime = 5;
        expired = false;
        this.powerdown = powerdown;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_powerdown-orb.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
    }

    @Override
    public void update(float dt) {
        PowerdownOrb b = new PowerdownOrb(x,y, powerdown);
        b.render(batch);
    }

    @Override
    public Powerup getPowerup() {
        return null;
    }

    @Override
    public Powerdown getPowerdown() {
        return powerdown;
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
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
}
