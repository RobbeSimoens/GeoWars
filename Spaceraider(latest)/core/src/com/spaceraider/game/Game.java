package com.spaceraider.game;

import com.spaceraider.entities.enemies.Enemy;
import com.spaceraider.entities.player.MultiPlayer;

import java.util.List;

/**
 * Created by robbe on 12/21/2016.
 */
public abstract class Game {
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;






    public void update(float dt) {
    }

    public List<Enemy> getEnemies() {
        return null;
    }


    public MultiPlayer getPlayer1() {
        return null;
    }

    public MultiPlayer getPlayer2() {
        return null;
    }

    public boolean isMultiplayer() {
        return false;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }


}
