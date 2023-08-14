package com.example.twitter_client.Server;

public class Main {
    public static DbManager db = new DbManager();

    public static void main(String[] args) {
        Thread serverPic = new Thread(new ServerPic());
        Thread serverDirect = new Thread(new ServerDirect());
        serverPic.start();
        serverDirect.start();
        Server server = new Server();
        server.StartServer();
    }
}
