package com.spaceraider.managers;

import com.spaceraider.gamestates.GameState;
import com.spaceraider.gamestates.PlayState;

/**
 * Created by Kevin on 9/11/2016.
 */
public class GameStateManager {
    private GameState gameState;

    public static final int MENU = 0;
    public static final int PLAY = 69;


    public GameStateManager(String gameMode, String username , int id) throws InterruptedException {
        setGameState(PLAY, gameMode, username, id);
    }

    public void setGameState(int state, String gameMode, String username , int id) throws InterruptedException {
        if(gameState != null) gameState.dispose();
        switch (state){
            case MENU:
                //switch to menu state
                break;
            case PLAY:
                gameState = new PlayState(this, gameMode, username, id);
                break;
        }
    }
    public void update(float dt){
        gameState.update(dt);
    }
    public void draw(){
        gameState.draw();
    }
}
