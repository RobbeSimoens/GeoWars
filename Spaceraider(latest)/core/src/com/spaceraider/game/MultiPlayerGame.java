package com.spaceraider.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by robbe on 12/21/2016.
 */
public class MultiPlayerGame extends Game{
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public MultiPlayerGame() throws IOException {
        super();
        socket = new Socket("localhost",666);
        writer = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }
    private void startMultiplayerThread(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String line;
                try{
                    while((line =reader.readLine()) != null){

                        //sendToServer();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }


}
