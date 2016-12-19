package com.spaceraider.entities.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.enums.Powerdown;

/**
 * Created by robbe on 12/19/2016.
 */
public class PowerdownOrb extends SpaceObject implements Orb { // TODO : activate powerups
    private float x,y;
    private static SpriteBatch batch;
    private static Texture texture;
    private Rectangle rect;
    private Powerdown powerdown;

    public PowerdownOrb(float x, float y, Powerdown powerdown) {
        this.x = x;
        this.y = y;
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
    public void render(SpriteBatch batch) {

        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
    }

    @Override
    public Rectangle getRectangle() {
        return rect;
    }
}
