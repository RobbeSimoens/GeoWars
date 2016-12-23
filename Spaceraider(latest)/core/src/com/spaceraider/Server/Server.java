package com.spaceraider.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 23/12/2016.
 */
public class Server {
    private ServerSocket serverSocket;
    private List<ClientThread> clientThreadList;
    public Server() throws IOException {
        clientThreadList = new ArrayList<>();
    }
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(666);
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
        }
    }
}
