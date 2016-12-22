package com.spaceraider.gamestates;

import com.spaceraider.managers.GameStateManager;

/**
 * Created by Kevin on 9/11/2016.
 */
public abstract class GameState {

    protected GameStateManager gsm;
    private java.lang.String gameMode;
    private int id;
    private String username;

    protected GameState(GameStateManager gsm, java.lang.String gameMode, String username, int id) throws InterruptedException {
        this.id = id;
        this.username = username;

        init(gameMode, username, id);
        this.gameMode = gameMode;
        this.gsm = gsm;


    }
    public abstract void init(String gameMode, String username, int id) throws InterruptedException;
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}
