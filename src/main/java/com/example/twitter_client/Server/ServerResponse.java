package com.example.twitter_client.Server;
import java.io.Serializable;

public class ServerResponse implements Serializable {
    private String content;
    private ResponseType responseType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public boolean isOk(){
        return responseType==ResponseType.OK;
    }
}