package com.example.twitter_client;


import java.io.Serializable;

public class ClientRequest implements Serializable {
    private String type;
    private String jwt;
    private String content;

    public ClientRequest(String type, String jwt, String content) {
        this.type = type;
        this.jwt = jwt;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}