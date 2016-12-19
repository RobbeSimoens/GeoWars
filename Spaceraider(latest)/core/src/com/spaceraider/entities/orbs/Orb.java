package com.spaceraider.entities.orbs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by robbe on 12/19/2016.
 */
public interface Orb {
    void update(float dt);
    void render(SpriteBatch batch);
    Rectangle getRectangle();
}
