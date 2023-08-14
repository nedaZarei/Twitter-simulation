package com.example.twitter_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerInput {

    static String token = "";
    private Socket client;
    ObjectOutputStream outputStream;

    public ServerInput(Socket client) throws IOException {
        this.client = client;
        outputStream = new ObjectOutputStream(client.getOutputStream());
    }

    public void send(ClientRequest req) throws IOException {
        try {
            outputStream.writeObject(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
}