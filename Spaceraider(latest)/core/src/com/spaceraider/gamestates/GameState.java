package com.spaceraider.gamestates;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spaceraider.managers.GameStateManager;

/**
 * Created by Kevin on 9/11/2016.
 */
public abstract class GameState {

    protected GameStateManager gsm;
    private java.lang.String gameMode;
    private int id;
    private String username;
    private LwjglApplicationConfiguration config;

    protected GameState(GameStateManager gsm, java.lang.String gameMode, LwjglApplicationConfiguration config, String username, int id) throws InterruptedException {
        this.id = id;
        this.username = username;
        this.config = config;
        init(gameMode, config, username, id);
        this.gameMode = gameMode;
        this.gsm = gsm;


    }
    public abstract void init(String gameMode, LwjglApplicationConfiguration config, String username, int id) throws InterruptedException;
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}
