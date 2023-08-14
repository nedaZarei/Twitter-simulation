package com.example.twitter_client.Server;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager implements DataManager{
    public static long countLine(String fileName) {
        long lines = 0;
        try (InputStream is = new BufferedInputStream(Files.newInputStream(Paths.get(fileName)))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            lines = count;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public void addUser(String userName, String passWord, String fullName, String email, String phoneNumber, String country, String birthDate, String avatar, String header, String bio, String location, String website, String date) {
        String userData = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                countLine("users.txt")+1 ,userName, passWord, fullName, email, phoneNumber, country, birthDate, avatar, header, bio, location, website, date);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(userData);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public String getPassword(String userName) {
        String password = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String username = userData[1];
                String userPassword = userData[2];
                if (username.equals(userName)) {
                    password = userPassword;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return password;
    }
    public boolean findUser(String username){
        boolean find = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String userId = userData[1];
                if(username.equals(userId)){
                    find = true;
                    break;
                }
            }
        }  catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return find;
    }
    public boolean findName(String fullName){
        boolean find = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String userFullName = userData[3];
                if(fullName.equals(userFullName)){
                    find = true;
                    break;
                }
            }
        }  catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return find;
    }
    public String findUserByName(String fullName){
        String username= null;
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                username = userData[0];
                String userFullName = userData[3];
                if(userFullName.equals(fullName)){
                    break;
                }
            }
        }  catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return username;
    }
    public boolean findEmail(String email) {
        boolean find = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingEmail = userData[4];
                if (existingEmail.equals(email)) {
                    find = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return find;
    }

    public boolean findPhoneNum(String phoneNumber) {
        boolean find = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingPhoneNumber = userData[7];
                if (existingPhoneNumber.equals(phoneNumber)) {
                    find = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return find;
    }

    public void addAvatar(String username, String picPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingUsername = userData[1];
                if (existingUsername.equals(username)) {
                    userData[7] = picPath;  // Update the avatar
                }
                fileContent.append(String.join(",", userData)).append(System.lineSeparator());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void addHeader(String username, String picPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingUsername = userData[1];
                if (existingUsername.equals(username)) {
                    userData[9] = picPath;  // Update the header
                }
                fileContent.append(String.join(",", userData)).append(System.lineSeparator());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void addBio(String username, String bio) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingUsername = userData[1];
                if (existingUsername.equals(username)) {
                    userData[10] = bio;  // Update the bio
                }
                fileContent.append(String.join(",", userData)).append(System.lineSeparator());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void addLocation(String username, String location) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingUsername = userData[1];
                if (existingUsername.equals(username)) {
                    userData[11] = location;  // Update the location
                }
                fileContent.append(String.join(",", userData)).append(System.lineSeparator());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void addWebsite(String username, String website) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingUsername = userData[1];
                if (existingUsername.equals(username)) {
                    userData[12] = website;  // Update the website
                }
                fileContent.append(String.join(",", userData)).append(System.lineSeparator());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public void addWrittenTweet(String username, String written, String date) {
        String tweetData = (countLine("messages.txt")+1) + ",tweet," + username + "," + written + "," + date + "," + 0 ;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("messages.txt", true))) {
            writer.write(tweetData);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void addPhotoTweet(String username, String photoPath, String date) {
        String tweetData =(countLine("messages.txt")+1) + ",tweet," + username + "," + photoPath + "," + date + "," + 0 ;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("messages.txt", true))) {
            writer.write(tweetData);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void addVideoTweet(String username, String videoPath, String date) {
        String tweetData =(countLine("messages.txt")+1) + ",tweet," + username + "," + videoPath + "," + date + "," + 0 ;

        try {
            Files.write(Paths.get("messages.txt"), (tweetData + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void Follow(String followerId, String followingId){
        String followData = String.format("%s,%s,%s", countLine("Follow.txt")+1,followerId, followingId);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Follow.txt", true))){
            writer.write(followData);
            writer.newLine();
        }catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void UnFollow(String followerId, String followingId) {
        String input;
        StringBuilder sb = new StringBuilder();
        try(FileReader follow = new FileReader("Follow.txt"); Scanner sc = new Scanner(follow)){
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                if(!input.contains(followerId + "," + followingId)){
                    sb.append(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading to file: " + e.getMessage());
        }
        try(FileWriter fileWriter = new FileWriter("Follow.txt")){
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void createPoll(String description, String numberOfChoices, String choices, String creatorId, String date) {
        String pollData = String.format("%s,%s,%s,%s,%s,%s", countLine("Poll.txt")+1, description, numberOfChoices,
                choices, creatorId, date);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Poll.txt", true))){
            writer.write(pollData);
            writer.newLine();
        }catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public boolean findPoll(int pollId) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("Poll.txt"));
            for (String line : lines) {
                String[] messageData = line.split(",");
                int existingMessageId = Integer.parseInt(messageData[0]);
                if (pollId == existingMessageId) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false;
    }
    @Override
    public void voting(String username, int pollId, String choiceNumber, String date) {
        String voteData = String.format("%s,%s,%s,%s,%s", countLine("Vote.txt")+1, username,
                pollId, choiceNumber, date);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Vote.txt", true))){
            writer.write(voteData);
            writer.newLine();
        }catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void retweet(String reTweeter, int messageId, String date) {
        String retweetData = (countLine("messages.txt")+1) +",retweet," + reTweeter + "," + date + "," + messageId;

        try {
            Files.write(Paths.get("messages.txt"), (retweetData + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public boolean findMessageId(int messageId) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("messages.txt"));
            for (String line : lines) {
                String[] messageData = line.split(",");
                int existingMessageId = Integer.parseInt(messageData[0]);
                if (messageId == existingMessageId) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false;
    }
    public ArrayList<Integer> findHashtagInWrittenTweets(String hashtag) {
        ArrayList<Integer> ids = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("messages.txt"));
            for (String line : lines) {
                String[] tweetData = line.split(",");
                if(tweetData[1].equals("tweet") || tweetData[1].equals("retweet")
                        || tweetData[1].equals("quote") || tweetData[1].equals("reply") ){
                    String written = tweetData[4];
                    if (written.contains(hashtag)) {
                        int tweetId = Integer.parseInt(tweetData[0]);
                        ids.add(tweetId);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return ids;
    }
    public void addHashtag(String word, int messageId) {
        String hashtagData = word + "," + messageId;
        try {
            Files.write(Paths.get("hashtags.txt"), (hashtagData + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void quote(String username, String message, int targetMessageId, String date) {
        retweet(username, targetMessageId, date);
        String quoteData = (countLine("messages.txt")+1) + ",quote,"+ message + ","+ date + "," + username +  "," + targetMessageId + "," + 0;
        try {
            Files.write(Paths.get("messages.txt"), (quoteData + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Integer> findFavstar() {
        ArrayList<Integer> ids = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("messages.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] messageData = line.split(",");
                int messageId = Integer.parseInt(messageData[0]);
                int likes = Integer.parseInt(messageData[9]);
                if(likes > 10){
                    ids.add(messageId);
                }
            }
        }  catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return ids;
    }

    @Override
    public void addReply(String username, int replyingMessageId, String message, String date) {
        String replyData = (countLine("messages.txt")+1) + ",reply," + message+ ","+ date+ "," + username + ","+ replyingMessageId + "," + 0;
        try {
            Files.write(Paths.get("messages.txt"), (replyData + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void block(String blocker, String blocked) {
        String blackListData = String.format("%s,%s,%s", countLine("BlackList.txt")+1,blocker, blocked);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("BlackList.txt", true))){
            writer.write(blackListData);
            writer.newLine();
        }catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void unBlock(String blocker, String blocked) {
        String input;
        StringBuilder sb = new StringBuilder();
        try(FileReader unBlock = new FileReader("BlackList.txt"); Scanner sc = new Scanner(unBlock)){
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                if(!input.contains(blocker + "," + blocked)){
                    sb.append(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading to file: " + e.getMessage());
        }
        try(FileWriter fileWriter = new FileWriter("Follow.txt")){
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    @Override
    public boolean findFollowing(String follower, String following) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("Follow.txt"));
            for (String line : lines) {
                String[] FollowData = line.split(",");
                String followerId = FollowData[1];
                String followingId = FollowData[2];
                if (followerId.equals(follower) && followingId.equals(following)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void like(String username, int messageId) {
        String input;
        StringBuilder sb = new StringBuilder();
        int likes = 0;
        try(FileReader increasingLikes = new FileReader("messages.txt"); Scanner sc = new Scanner(increasingLikes)){
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                String[] messageData = input.split(",");
                int id = Integer.parseInt(messageData[0]);
                likes = Integer.parseInt(messageData[6]);
                if(id == messageId){
                    sb.append(input, 0, input.lastIndexOf(",") + 1).append(++likes);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading to file: " + e.getMessage());
        }
        try(FileWriter fileWriter = new FileWriter("Follow.txt")){
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
