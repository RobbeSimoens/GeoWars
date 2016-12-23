package com.spaceraider.GUI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MusicPlayer {


    private Media testMusic = new Media(Paths.get("core/src/com/spaceraider/desktop/resources/arcade.mp3").toUri().toString());
    private MediaPlayer testPlayer;

    public void playMusic() {

        testPlayer = new MediaPlayer(testMusic);
        testPlayer.setAutoPlay(true);
        testPlayer.setCycleCount(testPlayer.INDEFINITE);
        testPlayer.play();

    }

}
