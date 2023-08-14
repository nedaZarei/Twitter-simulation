package com.example.twitter_client.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.twitter_client.ClientRequest;
import com.example.twitter_client.Server.Main;

class ClientHandler implements Runnable {

    private Socket connectionSocket;
    ObjectOutputStream out;
    ObjectInputStream in;

    public ClientHandler(Socket connectionSocket) throws IOException {
        this.connectionSocket = connectionSocket;
        out = new ObjectOutputStream(connectionSocket.getOutputStream());
        in = new ObjectInputStream(connectionSocket.getInputStream());
    }

    @Override
    public void run() {
        UserManagement management = new UserManagement();
        try {
            while (!connectionSocket.isClosed()) {
                ClientRequest request = (ClientRequest) in.readObject();
                String command = request.getType();
                String[] contents = request.getContent().split("\\$");
                ServerResponse response = new ServerResponse();
                ArrayList<String> otherInfo = new ArrayList<>();
                ArrayList<Message> messages = new ArrayList<>();
                ArrayList<User> followers = new ArrayList<>();
                ArrayList<User> followings = new ArrayList<>();
                ArrayList<User> blocked = new ArrayList<>();
                ArrayList<Integer> followerAndFollowingNum = new ArrayList<>();
                ArrayList<Integer> followerAndFollowingNumOther = new ArrayList<>();
                ArrayList<User> users = new ArrayList<>();
                ArrayList<Message> messagesWithHashtag = new ArrayList<>();
                ArrayList<DirectMessage> directMessages = new ArrayList<>();
                ArrayList<User> usersInDirect = new ArrayList<>();
                ArrayList<Message> replies = new ArrayList<>();
                User userInfo = null;
                String isFollowing = "";
                String isBlocked = "";
                boolean flagMessage = false;
                boolean flagFollowers = false;
                boolean flagFollowings = false;
                boolean flagUser = false;
                boolean flagInfo = false;
                boolean flagFollowersNumber = false;
                boolean flagFollowersNumberOther = false;
                boolean flagSearchUser = false;
                boolean flagBlocked = false;
                boolean flagSearchHashtag = false;
                boolean flagPoll = false;
                boolean flagDirectMessages = false;
                boolean flagDirectUsers = false;
                boolean flagIsFollowing = false;
                boolean flagIsBlocked = false;
                boolean flagReplyList = false;
                switch (command) {
                    case "signup":
                        User user = new User(contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6]);
                        response = management.signup(user);
                        break;
                    case "signin":
                        response = management.signin(contents[0], contents[1]);//username password
                        break;
                    case "timeLine":
                        flagMessage = true;
                        messages = management.tweetsInTimeLine(JWToken.VerifyJWT(request.getJwt()));
                        for (Message message : messages) {
                            String time = TimeDifferenceCalculator.calculateTimeDifference(LocalDateTime.parse(message.getDate()), LocalDateTime.now());
                            message.setDate(time);
                        }
                        messages.add(new Message("", "timelineMessage", "", "", "", "", "", "", "", 0, 0, 0, 0));
                        break;
                    case "seeFollowers":
                        flagFollowers = true;
                        followers = management.followers(JWToken.VerifyJWT(request.getJwt()));
                        break;
                    case "seeFollowings":
                        flagFollowings = true;
                        followings = management.followings(JWToken.VerifyJWT(request.getJwt()));
                        break;
                    case "avatar":
                        response = management.setAvatar(JWToken.VerifyJWT(request.getJwt()));
                        break;
                    case "header":
                        response = management.setHeader(JWToken.VerifyJWT(request.getJwt()));
                        break;
                    case "bio":
                        response = management.setBio(JWToken.VerifyJWT(request.getJwt()), contents[0]); //for now let's pretend contents[1] is bio
                        break;
                    case "location":
                        response = management.setLocation(JWToken.VerifyJWT(request.getJwt()), contents[0]);//for now let's pretend contents[1] is location
                        break;
                    case "website":
                        response = management.setWebsite(JWToken.VerifyJWT(request.getJwt()), contents[0]);//for now let's pretend contents[1] is website address
                        break;
                    case "follow":
                        response = management.Follow(JWToken.VerifyJWT(request.getJwt()), contents[0]);
                        break;
                    case "unfollow":
                        response = management.UnFollow(JWToken.VerifyJWT(request.getJwt()), contents[0]);
                        break;
                    case "tweet-written-message":
                        response = management.tweetWritten(JWToken.VerifyJWT(request.getJwt()), contents[0]);//username // written tweet
                        break;
                    case "tweet-photo":
                        response = management.tweetPhoto(JWToken.VerifyJWT(request.getJwt()), contents[0]);//username written
                        break;
                    case "tweet-video":
                        response = management.tweetVideo(JWToken.VerifyJWT(request.getJwt()), contents[0]);
                        break;
                    case "retweet":
                        response = management.retweet(JWToken.VerifyJWT(request.getJwt()), Integer.parseInt(contents[0]));//username of re tweeter , messageId
                        break;
                    case "quote":
                        response = management.quote(JWToken.VerifyJWT(request.getJwt()), Integer.parseInt(contents[0]), contents[1]);
                        break;
                    case "reply":
                        response = management.reply(JWToken.VerifyJWT(request.getJwt()), Integer.parseInt(contents[0]), contents[1]);
                        break;
                    case "#":
                        response = management.hashtag(contents[0], contents[1]);//username //the word
                        break;
                    case "block":
                        response = management.block(JWToken.VerifyJWT(request.getJwt()), contents[0]);//blocker //blocked
                        break;
                    case "unblock":
                        response = management.unBlock(JWToken.VerifyJWT(request.getJwt()), contents[0]);
                        break;
                    case "like":
                        response = management.like(JWToken.VerifyJWT(request.getJwt()), Integer.parseInt(contents[0]));
                        break;
                    case "edit-password":
                        response = management.editPassword(JWToken.VerifyJWT(request.getJwt()), contents[0]);//username //newPassword
                        break;
                    case "profile":
                        flagUser = true;
                        userInfo = management.userProfile(JWToken.VerifyJWT(request.getJwt()));
                        break;
                    case "followerAndFollowingNumber":
                        flagFollowersNumber = true;
                        followerAndFollowingNum = management.followersNumber(JWToken.VerifyJWT(request.getJwt()));
                        followerAndFollowingNum.add(-1);
                        break;
                    case "followerAndFollowingNumberOther":
                        flagFollowersNumberOther = true;
                        followerAndFollowingNumOther = management.followersNumber(contents[0]);
                        followerAndFollowingNumOther.add(-2);
                        break;
                    case "searchUser":
                        flagSearchUser = true;
                        users = management.searchUser(contents[0], JWToken.VerifyJWT(request.getJwt()));
                        break;
                    case "anotherUserProfile":
                        flagInfo = true;
                        userInfo = management.userProfile(contents[0]);
                        otherInfo.add(userInfo.getUserName());
                        otherInfo.add(userInfo.getAvatar());
                        otherInfo.add(userInfo.getHeader());
                        otherInfo.add(userInfo.getWebsite());
                        otherInfo.add(userInfo.getBio());
                        otherInfo.add(userInfo.getLocation());
                        otherInfo.add(userInfo.getFullName());
                        break;
                    case "seeBlocked":
                        flagBlocked = true;
                        blocked = management.blocked(JWToken.VerifyJWT(request.getJwt()));
                        break;
                    case "searchHashtag":
                        flagSearchHashtag = true;
                        messagesWithHashtag = management.searchHashtag(JWToken.VerifyJWT(request.getJwt()), contents[0]);// #word
                        for (Message message : messagesWithHashtag) {
                            String time = TimeDifferenceCalculator.calculateTimeDifference(LocalDateTime.parse(message.getDate()), LocalDateTime.now());
                            message.setDate(time);
                        }
                        messagesWithHashtag.add(new Message("", "hashtagMessage", "", "", "", "", "", "", "", 0, 0, 0, 0));
                        break;
                    case "usersInDirect":
                        flagDirectUsers = true;
                        usersInDirect = management.getUsersForDirect(JWToken.VerifyJWT(request.getJwt()));
                        usersInDirect.removeIf(user1 -> user1.getUserName().equals(JWToken.VerifyJWT(request.getJwt())));
                        break;
                    case "directMessages":
                        flagDirectMessages = true;
                        directMessages = management.getDirectMessages(JWToken.VerifyJWT(request.getJwt()), contents[0]);
                        break;
                    case "is-following":
                        flagIsFollowing = true;
                        boolean isfollowing = management.isFollowing(JWToken.VerifyJWT(request.getJwt()), contents[0]);
                        if (isfollowing) {
                            isFollowing = "follow@yes";
                        } else {
                            isFollowing = "follow@no";
                        }
                        break;
                    case "is-blocked":
                        flagIsBlocked = true;
                        boolean isblocked = management.isBlocked(JWToken.VerifyJWT(request.getJwt()), contents[0]);
                        if (isblocked) {
                            isBlocked = "block@yes";
                        } else {
                            isBlocked = "block@no";
                        }
                        break;
                    case "replyList":
                        flagReplyList = true;
                        replies = management.tweetsInTimeLine(JWToken.VerifyJWT(request.getJwt()));
                        for (Message message : replies) {
                            String time = TimeDifferenceCalculator.calculateTimeDifference(LocalDateTime.parse(message.getDate()), LocalDateTime.now());
                            message.setDate(time);
                        }
                        replies.add(new Message("", "replyList", "", "", "", "", "", "", "", 0, 0, 0, 0));
                        break;
                }
                if (flagMessage) {
                    out.writeObject(messages);
                } else if (flagFollowers) {
                    out.writeObject(followers);
                } else if (flagFollowings) {
                    out.writeObject(followings);
                } else if (flagUser) {
                    out.writeObject(userInfo);
                } else if (flagFollowersNumber) {
                    out.writeObject(followerAndFollowingNum);
                } else if (flagFollowersNumberOther) {
                    out.writeObject(followerAndFollowingNumOther);
                } else if (flagSearchUser) {
                    out.writeObject(users);
                } else if (flagBlocked) {
                    out.writeObject(blocked);
                } else if (flagInfo) {
                    out.writeObject(otherInfo);
                } else if (flagSearchHashtag) {
                    out.writeObject(messagesWithHashtag);
                } else if (flagDirectMessages) {
                    out.writeObject(directMessages);
                } else if (flagDirectUsers) {
                    out.writeObject(usersInDirect);
                } else if (flagIsFollowing) {
                    out.writeObject(isFollowing);
                } else if (flagIsBlocked) {
                    out.writeObject(isBlocked);
                } else if (flagReplyList) {
                    out.writeObject(replies);
                } else {
                    out.writeObject(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

