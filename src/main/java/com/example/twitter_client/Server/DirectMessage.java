package com.example.twitter_client.Server;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DirectMessage implements Serializable, Comparable<DirectMessage> {
    private int id;
    private String sender;
    private String reciever;
    private String message;
    private String date;

    public DirectMessage( String sender, String reciever, String message, String date) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int compareTo(DirectMessage o) {
        LocalDateTime time = LocalDateTime.parse(date);
        LocalDateTime time2 = LocalDateTime.parse(o.getDate());
        return time.compareTo(time2);
    }
}
