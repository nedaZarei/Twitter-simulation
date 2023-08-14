package com.example.twitter_client.Server;


import java.util.ArrayList;

public interface DataManager {
    public void addUser(String userName, String passWord, String fullName, String email, String phoneNumber, String country, String birthDate, String avatar, String header, String bio, String location, String website, String date);

    public String getPassword(String userName);

    public boolean findUser(String username);

    public boolean findEmail(String email);

    public boolean findPhoneNum(String phoneNumber);

    public void addAvatar(String username, String picPath);

    public void addHeader(String username, String picPath);

    public void addBio(String username, String bio);

    public void addLocation(String username, String location);

    public void addWebsite(String username, String website);

    public String findUserByName(String fullName);

    public boolean findName(String fullName);

    public void Follow(String followerId, String followingId);

    public void UnFollow(String followerId, String followingId);

    public void addWrittenTweet(String username, String written, String date);

    public void addPhotoTweet(String username, String photoPath, String date);

    public void addVideoTweet(String username, String videoPath, String date);

    public void createPoll(String description, String numberOfChoices, String choices, String creatorId, String date);

    public boolean findPoll(int pollId);

    public void voting(String username, int pollId, String choiceNumber, String date);

    public void retweet(String reTweeter, int messageId, String date);

    public void quote(String username, String message, int targetMessageId, String date);

    public ArrayList<Integer> findFavstar();

    public boolean findMessageId(int messageId);

    public void addReply(String username, int replyingMessageId, String message, String date);

    public ArrayList<Integer> findHashtagInWrittenTweets(String hashtag);

    public void addHashtag(String word, int messageId);

    public void block(String blocker, String blocked);

    public void unBlock(String blocker, String blocked);

    public boolean findFollowing(String follower, String following);

     public void like(String username, int messageId);

}
