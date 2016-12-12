package com.spaceraider.entities.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ERR0R on 25-10-2016.
 */
public interface Enemy {
    void initialize();
    void render(SpriteBatch batch);
    void update(float dt);
    String toString();
}
