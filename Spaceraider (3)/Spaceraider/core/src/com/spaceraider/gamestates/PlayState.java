package com.spaceraider.gamestates;
import com.spaceraider.entities.Player;
import com.spaceraider.managers.GameKeys;
import com.spaceraider.managers.GameStateManager;

/**
 * Created by Kevin on 9/11/2016.
 */
public class PlayState extends GameState {

    private Player player;

    public PlayState(GameStateManager gsm) throws InterruptedException {
        super(gsm);
    }

    @Override
    public void init() throws InterruptedException {

        player = new Player();
        player.spawn();
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
    }

    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {
        /*Todo Uit te breiden met schieten, etc etc*/
        player.setLeft(GameKeys.isDown(GameKeys.LEFT));
        player.setRight(GameKeys.isDown(GameKeys.RIGHT));
        player.setUp(GameKeys.isDown(GameKeys.UP));
        player.setDown(GameKeys.isDown(GameKeys.DOWN));
        player.setSpace(GameKeys.isPressed(GameKeys.SPACE));
    }

    @Override
    public void dispose() {

    }
}
