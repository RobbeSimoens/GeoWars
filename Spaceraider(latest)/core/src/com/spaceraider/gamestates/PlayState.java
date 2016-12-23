package com.spaceraider.gamestates;

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

    public PlayState(GameStateManager gsm, String gameMode, String username , int id) throws InterruptedException {
        super(gsm, gameMode, username , id);
        this.id = id;
        this.username = username;
        this.gameMode = gameMode;
        this.gsm = gsm;
    }

    @Override
    public void init(String gameMode, String username, int id) throws InterruptedException {
        if(gameMode.equals("singleplayer"))
        {

            game = new SinglePlayerGame(username, id);

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
        else if(gameMode.equals("multiplayer"))
        {
            game.getPlayer1().setLeft(GameKeys.isDown(GameKeys.LEFT));
            game.getPlayer1().setRight(GameKeys.isDown(GameKeys.RIGHT));
            game.getPlayer1().setUp(GameKeys.isDown(GameKeys.UP));
            game.getPlayer1().setDown(GameKeys.isDown(GameKeys.DOWN));

            game.getPlayer2().setLeft(GameKeys.isDown(GameKeys.Q));
            game.getPlayer2().setRight(GameKeys.isDown(GameKeys.D));
            game.getPlayer2().setDown(GameKeys.isDown(GameKeys.S));
            game.getPlayer2().setUp(GameKeys.isDown(GameKeys.Z));
        }

    }

    @Override
    public void dispose() {

    }

}
