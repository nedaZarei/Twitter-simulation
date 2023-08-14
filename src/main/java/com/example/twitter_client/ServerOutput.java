package com.example.twitter_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.twitter_client.Server.DirectMessage;
import com.example.twitter_client.Server.Message;
import com.example.twitter_client.Server.User;
import com.example.twitter_client.Server.ServerResponse;
import javafx.scene.Scene;

public class ServerOutput implements Runnable {
    Socket client;
    ObjectInputStream inputStream;

    public ServerOutput(Socket client) throws IOException {
        this.client = client;
        inputStream = new ObjectInputStream(client.getInputStream());
    }

    public static String token;

    @Override
    public void run() {
        while (client.isConnected()) {
            try {
                Object object = inputStream.readObject();
                if (object instanceof ServerResponse response) {
                    String[] parts = response.getContent().split(" ");
                    switch (parts[0]) {
                        case "signup":
                            switch (response.getResponseType()) {
                                case EMAIL_ALREADY_EXIST -> Main.controller.alertRepeatedEmail();
                                case USER_ALREADY_EXIST -> Main.controller.alertRepeatedUsername();
                                case NUMBER_ALREADY_EXIST -> Main.controller.alertRepeatedNumber();
                                case ONE_OF_EMAIL_OR_NUMBER_SHOULD_BE_ENTERED ->
                                        Main.controller.alertOneOfEmailOrNumber();
                                case OK -> Main.controller.signupOK();
                            }
                            break;
                        case "signin":
                            switch (response.getResponseType()) {
                                case PASSWORD_INVALID -> Main.controller.alertWrongPassword();
                                case USER_NOT_FOUND -> Main.controller.alertUserNotFound();
                                case OK -> {
                                    token = parts[3];
                                    Main.controller.signinOK();
                                }
                            }
                            break;
                        case "tweet-written-message":
                            switch (response.getResponseType()) {
                                case TEXT_SIZE_INVALID -> Main.controller.alertWrittenInvalid();
                                case OK -> Main.controller.SimpleTweetOK();
                            }
                            break;
                        case "photo-tweet":
                            switch (response.getResponseType()) {
                                case IMAGE_SIZE_INVALID -> Main.controller.alertPhotoInvalid();
                                case OK -> Main.controller.photoTweetOK();
                            }
                            break;
                    }
                } else if (object instanceof List<?>) {
                    ArrayList<?> list = (ArrayList<?>) object;

                    if (!list.isEmpty() && list.get(0) instanceof DirectMessage) {
                        ArrayList<DirectMessage> messages = (ArrayList<DirectMessage>) list;
                        Controller.setDirectMessages(messages);
                    }

                    if (!list.isEmpty() && list.get(0) instanceof Message) {
                        ArrayList<Message> messages = (ArrayList<Message>) list;
                        if (messages.get(messages.size() - 1).getType().equals("timelineMessage")) {
                            messages.remove(messages.size() - 1);
                            Main.controller.setTweetList(messages);
                        } else if (messages.get(messages.size() - 1).getType().equals("hashtagMessage")) {
                            messages.remove(messages.size() - 1);
                            Main.controller.setHashtagList(messages);
                        } else if (messages.get(messages.size() - 1).getType().equals("replyList")) {
                            messages.remove(messages.size() - 1);
                            Main.controller.setReplyList(messages);
                        }
                    }
                    if (!list.isEmpty() && list.get(0) instanceof Integer) {
                        ArrayList<Integer> numbers = (ArrayList<Integer>) list;
                        if (numbers.get(2) == -1) {
                            numbers.remove(2);
                            Main.controller.setNumbers(numbers);
                        } else if (numbers.get(2) == -2) {
                            numbers.remove(2);
                            Main.controller.setNumbersOther(numbers);
                        }
                    }
                    if (!list.isEmpty() && list.get(0) instanceof String) {
                        ArrayList<String> otherUserInfo = (ArrayList<String>) list;
                        Main.controller.setProfileOther(otherUserInfo.get(0), otherUserInfo.get(2), otherUserInfo.get(1), otherUserInfo.get(4), otherUserInfo.get(5), otherUserInfo.get(3), otherUserInfo.get(6));
                    }
                    if (!list.isEmpty() && list.get(0) instanceof User) {
                        ArrayList<User> users = (ArrayList<User>) list;
                        if (users.get(0).getUserName().equals("searchUser")) {
                            users.remove(0);
                            Main.controller.setUserInExplorer(users);
                        } else if (users.get(0).getUserName().equals("getFollowers")) {
                            users.remove(0);
                            Main.controller.setFollowersInFollowersList(users);
                        } else if (users.get(0).getUserName().equals("getFollowings")) {
                            users.remove(0);
                            Main.controller.setFollowingsInFollowingsList(users);
                        } else if (users.get(0).getUserName().equals("getBlocked")) {
                            users.remove(0);
                            Main.controller.setBlackList(users);
                        } else if (users.get(0).getUserName().equals("directUsers")) {
                            users.remove(0);
                            Main.controller.setUsersInDirect(users);
                        }
                    }
                } else if (object instanceof User) {
                    User user = (User) object;
                    Main.controller.setProfile(user);
                } else if (object instanceof String) {
                    String is = (String) object;
                    String[] part = is.split("@");
                    if (part[0].equals("follow")) {
                        if (part[1].equals("yes")) {
                            Main.controller.is_following = true;
                        } else {
                            Main.controller.is_following = false;
                        }
                    } else if (part[0].equals("block")) {
                        if (part[1].equals("yes")) {
                            Main.controller.is_blocked = true;
                        } else {
                            Main.controller.is_blocked = false;
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}