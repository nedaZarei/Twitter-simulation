package com.example.twitter_client.Server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class UserManagement {
    public ServerResponse signup(User user) {
        ServerResponse serverResponse = new ServerResponse();
        if (Main.db.findUser(user.getUserName())) {
            serverResponse.setContent("signup " + user.getUserName() + " username-already-exist");
            serverResponse.setResponseType(ResponseType.USER_ALREADY_EXIST);
        } else if (Main.db.findEmail(user.getEmail())) {
            serverResponse.setContent("signup " + user.getUserName() + " email-already-exist");
            serverResponse.setResponseType(ResponseType.EMAIL_ALREADY_EXIST);
        } else if (Main.db.findPhoneNum(user.getPhoneNumber())) {
            serverResponse.setContent("signup " + user.getUserName() + " number-already-exist");
            serverResponse.setResponseType(ResponseType.NUMBER_ALREADY_EXIST);
        } else if (user.getPhoneNumber().equals("") && user.getEmail().equals("")) {
            serverResponse.setContent("signup " + user.getUserName() + " both-email-and-number-are-null");
            serverResponse.setResponseType(ResponseType.ONE_OF_EMAIL_OR_NUMBER_SHOULD_BE_ENTERED);
        } else {
            serverResponse.setContent("signup " + user.getUserName() + " signup-ok");
            serverResponse.setResponseType(ResponseType.OK);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            currentTime.format(formatter);
            String timeStamp = currentTime.toString();
            Main.db.addUser(user.getUserName(), user.getPassWord(), user.getFullName(), user.getEmail(), user.getPhoneNumber(), user.getCountry(), user.getBirthDate(), timeStamp);
            Main.db.Follow(user.getUserName(), user.getUserName()); //adding the person to its own followings
        }
        return serverResponse;
    }

    public ServerResponse signin(String username, String password) {  //JWT
        ServerResponse serverResponse = new ServerResponse();
        if (!Main.db.findUser(username)) {
            serverResponse.setContent("signin " + username + " user-not-found");
            serverResponse.setResponseType(ResponseType.USER_NOT_FOUND);
        } else if (!Main.db.getPassword(username).equals(password)) {
            serverResponse.setContent("signin " + password + " password-invalid");
            serverResponse.setResponseType(ResponseType.PASSWORD_INVALID);
        } else {
            serverResponse.setContent("signin " + username + " signin-ok " + JWToken.CreateJWT(username));
            serverResponse.setResponseType(ResponseType.OK);
        }
        return serverResponse;
    }

    public ArrayList<Message> tweetsInTimeLine(String username) {
        return Main.db.timeLineHandler(Main.db.getFollowingsForTimeLine(username), username);
    }

    public ArrayList<User> followers(String username) {
        return Main.db.getFollowers(username);
    }

    public ArrayList<User> followings(String username) {
        return Main.db.getFollowings(username);
    }

    public ServerResponse setAvatar(String username) throws IOException {
        ServerResponse serverResponse = new ServerResponse();
        String filename = "C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\avatars\\" + username + ".jpg";
        File file = new File(filename);
        BufferedImage bimg = ImageIO.read(file);
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        if ((width != 400) || (height != 400)) {
            serverResponse.setContent(username + " avatar-size-pixel-invalid");
            serverResponse.setResponseType(ResponseType.IMAGE_SIZE_INVALID);
        }
        long fileSizeInMB = file.length() / (1024 * 1024);
        if (fileSizeInMB > 1) {
            serverResponse.setContent(username + " avatar-size-MB-invalid");
            serverResponse.setResponseType(ResponseType.IMAGE_SIZE_INVALID);
        } else {
            serverResponse.setContent(username + " avatar-ok");
            serverResponse.setResponseType(ResponseType.OK);
            Main.db.addAvatar(username, filename);
        }
        return serverResponse;
    }

    public ServerResponse setHeader(String username) throws IOException {
        ServerResponse serverResponse = new ServerResponse();
        String filename = "C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\headers\\" + username + ".jpg";
        File file = new File(filename);
        BufferedImage bimg = ImageIO.read(file);
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        if ((width != 1500) || (height != 500)) {
            serverResponse.setContent(username + " header-size-pixel-invalid");
            serverResponse.setResponseType(ResponseType.IMAGE_SIZE_INVALID);
        }
        long fileSizeInMB = file.length() / (1024 * 1024);
        if (fileSizeInMB > 2) {
            serverResponse.setContent(username + " header-size-MB-invalid");
            serverResponse.setResponseType(ResponseType.IMAGE_SIZE_INVALID);
        } else {
            serverResponse.setContent(username + " header-ok");
            serverResponse.setResponseType(ResponseType.OK);
            Main.db.addHeader(username, filename);
        }
        return serverResponse;
    }

    public ServerResponse setBio(String username, String bio) {
        ServerResponse serverResponse = new ServerResponse();
        if (bio.length() > 160) {
            serverResponse.setContent(username + " size-bio-invalid");
            serverResponse.setResponseType(ResponseType.TEXT_SIZE_INVALID);
        } else {
            serverResponse.setContent(username + " bio-ok");
            serverResponse.setResponseType(ResponseType.OK);
            Main.db.addBio(username, bio);
        }
        return serverResponse;
    }

    public ServerResponse setLocation(String username, String location) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(username + " location-ok");
        serverResponse.setResponseType(ResponseType.OK);
        Main.db.addLocation(username, location);

        return serverResponse;
    }

    public ServerResponse setWebsite(String username, String website) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(username + " website-ok");
        serverResponse.setResponseType(ResponseType.OK);
        Main.db.addWebsite(username, website);

        return serverResponse;
    }

    public ServerResponse Follow(String followerId, String followingId) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(followerId + " follow-ok");
        serverResponse.setResponseType(ResponseType.OK);
        Main.db.Follow(followerId, followingId);
        return serverResponse;
    }

    public ServerResponse UnFollow(String followerId, String followingId) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(followerId + " unfollow-ok");
        serverResponse.setResponseType(ResponseType.OK);
        Main.db.UnFollow(followerId, followingId);
        return serverResponse;
    }

    public ServerResponse tweetWritten(String username, String written) {
        ServerResponse serverResponse = new ServerResponse();
        if (written.length() > 280) {
            serverResponse.setContent(username + " size-writtenTweet-invalid");
            serverResponse.setResponseType(ResponseType.TEXT_SIZE_INVALID);
        } else {
            serverResponse.setContent(username + " writtenTweet-ok");
            serverResponse.setResponseType(ResponseType.OK);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            currentTime.format(formatter);
            String timeStamp = currentTime.toString();
            Main.db.addWrittenTweet(username, written, timeStamp);
        }
        return serverResponse;
    }

    public ServerResponse tweetPhoto(String username, String written) throws IOException {
        ServerResponse serverResponse = new ServerResponse();
        String directoryPath = "C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\photoTweets"; //folder address
        String desired_file = desired_file(username, directoryPath);
        String filename = "C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\photoTweets\\" + desired_file;
        File file = new File(filename);
        BufferedImage bimg = ImageIO.read(file);
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        if ((width > 900) && (height > 1600)) {
            serverResponse.setContent(username + " photoTweet-size-invalid");
            serverResponse.setResponseType(ResponseType.IMAGE_SIZE_INVALID);
        } else {
            serverResponse.setContent(username + " photoTweet-ok");
            serverResponse.setResponseType(ResponseType.OK);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            currentTime.format(formatter);
            String timeStamp = currentTime.toString();
            Main.db.addPhotoTweet(username, filename, timeStamp, written);
        }
        return serverResponse;
    }

    public ServerResponse tweetVideo(String username, String written) {
        ServerResponse serverResponse = new ServerResponse();
        String directoryPath = "C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\videoTweets"; //folder address
        desired_file(username, directoryPath);
        String desired_file = desired_file(username, directoryPath);
        String filename = "C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\videoTweets\\" + desired_file + ".mp4";
        if (written.length() > 280) {
            serverResponse.setContent(username + " size-writtenTweet-invalid");
            serverResponse.setResponseType(ResponseType.TEXT_SIZE_INVALID);
        }
        serverResponse.setContent(username + " videoTweet-ok");
        serverResponse.setResponseType(ResponseType.OK);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentTime.format(formatter);
        String timeStamp = currentTime.toString();
        Main.db.addVideoTweet(username, filename, timeStamp, written);
        return serverResponse;
    }

    private String desired_file(String username, String directoryPath) {
        File dir = new File(directoryPath);
        String[] list = dir.list();
        ArrayList<String> username_list = new ArrayList<>();
        assert list != null;
        for (String s : list) {
            if (s.toLowerCase().contains(username)) {
                username_list.add(s);
            }
        }
        Collections.sort(username_list);
        return username_list.get(username_list.size() - 1);
    }

    public ServerResponse retweet(String username, int messageId) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(username + " reTweet-ok");
        serverResponse.setResponseType(ResponseType.OK);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentTime.format(formatter);
        String timeStamp = currentTime.toString();
        Main.db.retweet(username, messageId, timeStamp);

        return serverResponse;
    }

    public ServerResponse quote(String username, int targetMessageId, String message) {
        ServerResponse serverResponse = new ServerResponse();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentTime.format(formatter);
        String timeStamp = currentTime.toString();
        //Main.db.retweet(username, targetMessageId, timeStamp);
        Main.db.quote(username, message, targetMessageId, timeStamp);
        serverResponse.setContent(username + " Quote-ok");
        serverResponse.setResponseType(ResponseType.OK);

        return serverResponse;
    }

    public ServerResponse reply(String username, int replyingMessageId, String message) {
        ServerResponse serverResponse = new ServerResponse();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentTime.format(formatter);
        String timeStamp = currentTime.toString();
        Main.db.addReply(username, replyingMessageId, message, timeStamp);
        serverResponse.setContent(username + " reply-ok");
        serverResponse.setResponseType(ResponseType.OK);
        return serverResponse;
    }

    public ServerResponse hashtag(String username, String word) {
        String hashtag = "#" + word;
        ArrayList<Integer> ids; //ids of messages that their written tweet includes #word
        ids = Main.db.findHashtagInWrittenTweets(hashtag);
        for (Integer id : ids) {
            Main.db.addHashtag(word, id);
        }
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(username + " hashtag-ok");
        serverResponse.setResponseType(ResponseType.OK);
        return serverResponse;
    }

    public ArrayList<Message> searchHashtag(String username, String hashtag) {
        return Main.db.hashtagExplorerHandler(username, hashtag);
    }

    public ServerResponse block(String blocker, String blocked) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(blocker + "blocked-ok");
        serverResponse.setResponseType(ResponseType.OK);
        if (Main.db.findFollowing(blocker, blocked)) {
            Main.db.UnFollow(blocker, blocked);
        }
        if (Main.db.findFollowing(blocked, blocker)) {
            Main.db.UnFollow(blocked, blocker);
        }
        Main.db.block(blocker, blocked);
        return serverResponse;
    }

    public ServerResponse unBlock(String blocker, String blocked) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(blocker + " unblock-ok");
        serverResponse.setResponseType(ResponseType.OK);
        Main.db.unBlock(blocker, blocked);
        return serverResponse;
    }

    public ServerResponse like(String username, int messageId) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(username + " like-ok");
        serverResponse.setResponseType(ResponseType.OK);
        Main.db.like(username, messageId);
        return serverResponse;
    }


    public ServerResponse editPassword(String username, String newPassword) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setContent(username + " editPassword-ok");
        serverResponse.setResponseType(ResponseType.OK);
        Main.db.editPassword(username, newPassword);
        return serverResponse;
    }

    public ArrayList<Integer> followersNumber(String username) {
        return Main.db.followerAndFollowingNumber(username);
    }

    public ArrayList<User> searchUser(String username, String myUsername) {
        return Main.db.searchUser(username, myUsername);
    }

    public ArrayList<User> blocked(String username) {
        return Main.db.getBlocked(username);
    }

    public User userProfile(String username) {
        return Main.db.profileInfo(username);
    }

    public User otherUserProfile(String username) {
        return Main.db.otherProfileInfo(username);
    }

    public ArrayList<User> getUsersForDirect(String username) {
        return Main.db.getDirectUsers(Main.db.getFollowingsForDirect(username));
    }

    public ArrayList<DirectMessage> getDirectMessages(String sender, String reciever) {
        return Main.db.getDirectMessages(sender, reciever);
    }

    public boolean isFollowing(String myUsername, String otherUsername) {
        return Main.db.findFollowing(myUsername, otherUsername);
    }

    public boolean isBlocked(String myUsername, String otherUsername) {
        return Main.db.findBlocked(myUsername, otherUsername);
    }

    public boolean hasLiked(String username, String messageId) {
        return Main.db.findLike(username, Integer.parseInt(messageId));
    }
}
