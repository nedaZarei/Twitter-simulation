package com.example.twitter_client.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerDirect implements Runnable {
    public static HashMap<String, ArrayList<ClientHandlerDirect>> clients=new HashMap<>();

    @Override
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(1989);
            while (true) {
                System.out.println("Waiting for clients Direct...");
                socket = serverSocket.accept();
                Thread directThread = new Thread(new ClientHandlerDirect(socket));
                directThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}