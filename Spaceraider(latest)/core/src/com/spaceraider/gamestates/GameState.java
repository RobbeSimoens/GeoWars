package com.spaceraider.gamestates;

import com.spaceraider.managers.GameStateManager;

/**
 * Created by Kevin on 9/11/2016.
 */
public abstract class GameState {

    protected GameStateManager gsm;
    protected GameState(GameStateManager gsm) throws InterruptedException {
        this.gsm = gsm;
        init();
    }
    public abstract void init() throws InterruptedException;
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}
