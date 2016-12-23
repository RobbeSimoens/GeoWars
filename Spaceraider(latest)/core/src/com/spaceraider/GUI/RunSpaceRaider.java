package com.spaceraider.GUI;



import javafx.application.Application;
import javafx.stage.Stage;

public class RunSpaceRaider extends Application {

    public static void main(String[] args) {

        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MusicPlayer player;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Frame frame = new Frame();
        primaryStage.hide();
        player = new MusicPlayer();
        player.playMusic();
    }
}
