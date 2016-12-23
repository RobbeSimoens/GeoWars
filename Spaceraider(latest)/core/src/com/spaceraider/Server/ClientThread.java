package com.spaceraider.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Kevin on 23/12/2016.
 */
public class ClientThread extends Thread{
    private final Server server;
    private Socket socket;

    private PrintWriter outputStream;
    private BufferedReader inputStream;

    public ClientThread(Socket socket,Server server) {
        this.server = server;
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            outputStream = new PrintWriter(socket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while(!isInterrupted()&&(line = inputStream.readLine()) != null){
                System.out.println("Received from gameclient: "+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToClient(String message){
        outputStream.println(message);
        outputStream.flush();
    }
}
