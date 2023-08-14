package com.example.twitter_client.Server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class ClientHandlerDirect implements Runnable {
    public Socket client;
    public ObjectInputStream input;
    public ObjectOutputStream output;

    public String username;

    public String key;

    public ClientHandlerDirect(Socket client) throws IOException {
        this.client = client;
        this.output = new ObjectOutputStream(client.getOutputStream());
        this.input = new ObjectInputStream(client.getInputStream());
    }


    @Override
    public void run() {
        try {
            DirectMessage message = (DirectMessage) input.readObject();
            ArrayList<String> keyComponent = new ArrayList<>();
            keyComponent.add(message.getSender());
            keyComponent.add(message.getReciever());
            Collections.sort(keyComponent);
            String key = keyComponent.get(0) + " " + keyComponent.get(1);
            username = message.getSender();
            if (!ServerDirect.clients.containsKey(key)) {
                ServerDirect.clients.put(key, new ArrayList<>());
            }
            this.key = key;
            boolean flag = true;
            for (ClientHandlerDirect client : ServerDirect.clients.get(key)) {
                if (client.equals(this)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ServerDirect.clients.get(key).add(this);
            }
            while (client.isConnected()) {
                message = (DirectMessage) input.readObject();
                ArrayList<ClientHandlerDirect> clients = ServerDirect.clients.get(key);
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                currentTime.format(formatter);
                String timeStamp = currentTime.toString();
                Main.db.addDirectMessage(message.getSender(), message.getReciever(), message.getMessage(), timeStamp);
                for (ClientHandlerDirect client : clients) {
                    if (!client.equals(this)) {
                        ObjectOutputStream otherClient = client.getOutput();
                        otherClient.writeObject(message);
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandlerDirect direct = (ClientHandlerDirect) o;
        return Objects.equals(username, direct.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}