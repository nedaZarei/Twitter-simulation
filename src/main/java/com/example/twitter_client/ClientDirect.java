package com.example.twitter_client;

import com.example.twitter_client.Server.DirectMessage;
import javafx.geometry.NodeOrientation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientDirect implements Runnable {
    private Socket client;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientDirect(Socket client) throws IOException {
        this.client = client;
        this.output = new ObjectOutputStream(client.getOutputStream());
        this.input = new ObjectInputStream(client.getInputStream());
    }


    @Override
    public void run() {
        try {
            while (client.isConnected()) {
                DirectMessage message = (DirectMessage) input.readObject();
                ArrayList<DirectMessage> messages = new ArrayList<>();
                messages.add(message);
                setDirectMessages(messages);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDirectMessages(ArrayList<DirectMessage> messages) {
        for (DirectMessage message : messages) {
            Main.controller.msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            if (message.getSender().equals(Main.controller.username)) {
                Main.controller.msgRoom.appendText("Me : " + message.getMessage() + "\n");
            } else {
                Main.controller.msgRoom.appendText(message.getSender() + " : " + message.getMessage() + "\n");
            }
        }
    }


    public Socket getClient() {
        return client;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }
}
