package com.spaceraider.entities.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by ERR0R on 25-10-2016.
 */
public interface Enemy {
    void initialize();

    void render(SpriteBatch batch);
    void update(float dt);
    String toString();
    float getX();
    float getY();
    Rectangle getRectangle();
    void reduceHitpoints();

    void dropOrb();
}
