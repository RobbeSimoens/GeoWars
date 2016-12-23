package com.spaceraider.game;

import com.spaceraider.entities.player.MultiPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robbe on 12/21/2016.
 */
public class MultiPlayerGame extends Game{

    private List<MultiPlayer> players ;


    public MultiPlayerGame() throws InterruptedException {
        super();
        init();
    }

    public void init() throws InterruptedException {
        players = new ArrayList<MultiPlayer>();
        players.add(new MultiPlayer(this, 200 , 200, "left"));
        players.add(new MultiPlayer(this , 1600 , 1000, "right"));
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        updatePlayers(dt);
    }

    @Override
    public MultiPlayer getPlayer1(){
        return players.get(0);
    }
    @Override
    public MultiPlayer getPlayer2(){
        return players.get(1);
    }

    public void updatePlayers(float dt){
        for (int i = 0; i < players.size(); i++)
        {
            players.get(i).update(dt);
        }
    }

    public void spawn(String enemy, String side){
        MultiPlayer playerToAdd = null;

        if(side.equals("left")){playerToAdd = getPlayer2();}
        else {playerToAdd = getPlayer1();}

        switch(enemy)
        {
            case "standard":
                playerToAdd.addToSpawnList("standard");
                break;

            case "tank":
                playerToAdd.addToSpawnList("tank");
                break;

            case "attacker":
                playerToAdd.addToSpawnList("attacker");
                break;

            case "powerup":
                playerToAdd.addToSpawnList("powerup");
                break;

            case "powerdown":
                playerToAdd.addToSpawnList("powerdown");
                break;

            case "slow":
                playerToAdd.setSlowed(true);
                break;

            case "inverted":
                playerToAdd.setInverted(true);
                break;
            case "silenced":
                playerToAdd.setSilenced(true);
                break;
        }
    }

    @Override
    public boolean isMultiplayer() {
        return true;
    }
}