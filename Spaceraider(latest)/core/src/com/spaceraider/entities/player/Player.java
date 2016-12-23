package com.spaceraider.entities.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.Bullet;
import com.spaceraider.entities.enemies.Enemy;
import com.spaceraider.entities.orbs.Orb;

/**
 * Created by robbe on 12/21/2016.
 */
public interface Player {
    void setMultiplier();
    void removeBullet(Bullet bullet);
    void addDroneBullet(float x, float y);
    float getX();
    float getY();
    Rectangle getRect();
    void reduceHitpoints();
    void removeEnemy(Enemy enemy);
    void reduceEnemyHitpoints(Enemy enemy);
    void addOrb(Orb orb);
    Sprite getPlayerSprite();

}
