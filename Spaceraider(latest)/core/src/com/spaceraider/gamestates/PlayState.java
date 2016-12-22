package com.spaceraider.gamestates;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spaceraider.game.MultiPlayerGame;
import com.spaceraider.game.SinglePlayerGame;
import com.spaceraider.managers.GameKeys;
import com.spaceraider.managers.GameStateManager;
import com.spaceraider.game.Game;

/**
 * Created by Kevin on 9/11/2016.
 */
public class PlayState extends GameState {
    private float timeAux;
    private String gameMode;
    private Game game;
    private String username;
    private int id;

    public PlayState(GameStateManager gsm, String gameMode, LwjglApplicationConfiguration config, String username , int id) throws InterruptedException {
        super(gsm, gameMode, config,  username , id);
        this.id = id;
        this.username = username;
        this.gameMode = gameMode;
        this.gsm = gsm;
    }

    @Override
    public void init(String gameMode, LwjglApplicationConfiguration config, String username, int id) throws InterruptedException {
        System.out.println(gameMode);
        if(gameMode.equals("singleplayer"))
        {
            System.out.println(username + " " + id);
            game = new SinglePlayerGame(config, username, id);
        }
        else
        {
            game = new MultiPlayerGame();
        }


    }

    @Override
    public void update(float dt) {
            handleInput();
            game.update(dt);
    }




    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {
        if(gameMode.equals("singleplayer"))
        {
            game.setLeft(GameKeys.isDown(GameKeys.LEFT));
            game.setRight(GameKeys.isDown(GameKeys.RIGHT));
            game.setUp(GameKeys.isDown(GameKeys.UP));
            game.setDown(GameKeys.isDown(GameKeys.DOWN));
        }

    }

    @Override
    public void dispose() {

    }

}
