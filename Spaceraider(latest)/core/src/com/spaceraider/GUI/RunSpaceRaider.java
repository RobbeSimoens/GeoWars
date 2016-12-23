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
    public static Frame frame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        frame = new Frame();
        primaryStage.hide();
        player = new MusicPlayer();
        player.playMusic();
    }
}
