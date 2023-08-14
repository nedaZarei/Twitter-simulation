package com.example.twitter_client.Server;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable, Comparable<Message> {
    private String messageId;
    private String type;
    private String written;
    private String photoPath;
    private String videoPath;
    private String date;
    private User user;
    private String senderUsername;
    private String receiverUsername;
    private String targetMassageId;
    private int likesNum;
    private int quotesNum;
    private int repliesNum;
    private int retweetsNum;
    private boolean hasLiked;

    public Message(String messageId, String type, String written, String photoPath, String videoPath, String date, String senderUsername, String receiverUsername, String targetMassageId, int likesNum, int quotesNum, int repliesNum, int retweetsNum) {
        this.messageId = messageId;
        this.type = type;
        this.written = written;
        this.photoPath = photoPath;
        this.videoPath = videoPath;
        this.date = date;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.targetMassageId = targetMassageId;
        this.likesNum = likesNum;
        this.quotesNum = quotesNum;
        this.repliesNum = repliesNum;
        this.retweetsNum = retweetsNum;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWritten(String written) {
        this.written = written;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public void setTargetMassageId(String targetMassageId) {
        this.targetMassageId = targetMassageId;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    public void setQuotesNum(int quotesNum) {
        this.quotesNum = quotesNum;
    }

    public void setRepliesNum(int repliesNum) {
        this.repliesNum = repliesNum;
    }

    public void setRetweetsNum(int retweetsNum) {
        this.retweetsNum = retweetsNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getType() {
        return type;
    }

    public String getWritten() {
        return written;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public String getDate() {
        return date;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public String getTargetMassageId() {
        return targetMassageId;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public int getQuotesNum() {
        return quotesNum;
    }

    public int getRepliesNum() {
        return repliesNum;
    }

    public int getRetweetsNum() {
        return retweetsNum;
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }
    @Override
    public int compareTo(Message o) {
        LocalDateTime time = LocalDateTime.parse(date);
        LocalDateTime time2 = LocalDateTime.parse(o.getDate());
        return time2.compareTo(time);
    }
}
