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

    public PlayState(GameStateManager gsm, String gameMode) throws InterruptedException {
        super(gsm, gameMode);
        this.gameMode = gameMode;
        this.gsm = gsm;
    }

    @Override
    public void init(String gameMode) throws InterruptedException {
        System.out.println(gameMode);
        if(gameMode.equals("singleplayer"))
        {
            game = new SinglePlayerGame();
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
