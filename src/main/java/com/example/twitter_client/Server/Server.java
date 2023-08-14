package com.example.twitter_client.Server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void StartServer() {
        ExecutorService pool = Executors.newCachedThreadPool();
        try (ServerSocket welcomingSocket = new ServerSocket(7661)) {
            System.out.print("Server started.\nWaiting for a client ... ");
            while (true) {
                Socket connectionSocket = welcomingSocket.accept();
                System.out.println("client accepted!");
                pool.execute(new ClientHandler(connectionSocket));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("done.");
    }
}