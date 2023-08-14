package com.example.twitter_client.Server;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class DbManager {
    public static Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\Server\\Twitter.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("connect to db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addUser(String userName, String passWord, String fullName, String email, String phoneNumber, String country, String birthDate, String date) {
        String sql = "INSERT INTO User(username,password,fullname,country,phonenumber,email,birthdate,signupdate,avatar,header,bio,location,website) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, passWord);
            pstmt.setString(3, fullName);
            pstmt.setString(4, country);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, email);
            pstmt.setString(7, birthDate);
            pstmt.setString(8, date);
            pstmt.setString(9, "Null");
            pstmt.setString(10, "Null");
            pstmt.setString(11, "Null");
            pstmt.setString(12, "Null");
            pstmt.setString(13, "Null");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPassword(String userName) {
        String sql = "SELECT password FROM User WHERE username = '" + userName + "' ";
        String password = null;
        try (Connection conn = connect()) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) password = rs.getString("password");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return password;
    }

    public boolean findUser(String username) {
        String sql = "SELECT * FROM User WHERE username = '" + username + "'";
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (username.equals(rs.getString("username"))) {
                    find = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return find;
    }

    public boolean findEmail(String email) {
        String sql = "SELECT * FROM User WHERE email = '" + email + "'";
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
                if (email.equals(rs.getString("email"))) {
                    find = true;
                }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return find;
    }

    public boolean findPhoneNum(String phoneNumber) {
        String sql = "SELECT * FROM User WHERE phonenumber = '" + phoneNumber + "'";
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
                if (phoneNumber.equals(rs.getString("phonenumber"))) {
                    find = true;

                }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return find;
    }

    public void addAvatar(String username, String picPath) {
        String sql = "UPDATE User SET avatar = ? WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, picPath);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addHeader(String username, String picPath) {
        String sql = "UPDATE User SET header = ? WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, picPath);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBio(String username, String bio) {
        String sql = "UPDATE User SET bio = ? WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bio);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addLocation(String username, String location) {
        String sql = "UPDATE User SET location = ? WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, location);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addWebsite(String username, String website) {
        String sql = "UPDATE User SET website = ? WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, website);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String findUserByName(String fullName) {
        String sql = "SELECT username FROM User WHERE fullname = ' " + fullName + " '";
        String username = null;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            username = rs.getString(sql);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return username;
    }

    public boolean findName(String fullName) {
        String sql = "SELECT fullname FROM User";
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (fullName.equals(rs.getString("fullname"))) {
                    find = true;
                    break;
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return find;
    }

    public void Follow(String followerId, String followingId) {
        String sql = "INSERT INTO Follow(followerId,followingId) VALUES(?,?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, followerId);
            pstmt.setString(2, followingId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void UnFollow(String followerId, String followingId) {
        String sql = "DELETE FROM Follow WHERE followerId = '" + followerId + "' AND followingId = '" + followingId + "'";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addWrittenTweet(String username, String written, String date) {
        String sql = "INSERT INTO Message(type,sender,written,date,photo,video,likes) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "tweet");
            pstmt.setString(2, username);
            pstmt.setString(3, written);
            pstmt.setString(4, date);
            pstmt.setString(5, "Null");
            pstmt.setString(6, "Null");
            pstmt.setInt(7, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPhotoTweet(String username, String photoPath, String date, String written) {
        String sql = "INSERT INTO Message(type,sender,photo,date,written,video,likes) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "tweet");
                pstmt.setString(2, username);
                pstmt.setString(3, photoPath);
                pstmt.setString(4, date);
                pstmt.setString(5, written);
                pstmt.setString(6, "Null");
                pstmt.setInt(7, 0);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addVideoTweet(String username, String videoPath, String date, String written) {
        String sql = "INSERT INTO Message(type,sender,video,date,written,photo,likes) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "tweet");
            pstmt.setString(2, username);
            pstmt.setString(3, videoPath);
            pstmt.setString(4, date);
            pstmt.setString(5, written);
            pstmt.setString(6, "Null");
            pstmt.setInt(7, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void retweet(String reTweeter, int messageId, String date) {
        String sql = "INSERT INTO Message(type,sender,date,targetmessageid,photo,video,likes) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "retweet");
            pstmt.setString(2, reTweeter);
            pstmt.setString(3, date);
            pstmt.setInt(4, messageId);
            pstmt.setString(5, "Null");
            pstmt.setString(6, "Null");
            pstmt.setInt(7, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void quote(String username, String message, int targetMessageId, String date) {
        String sql = "INSERT INTO Message(type,written,date,sender,targetmessageid,photo,video,likes) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "quote");
            pstmt.setString(2, message);
            pstmt.setString(3, date);
            pstmt.setString(4, username);
            pstmt.setInt(5, targetMessageId);
            pstmt.setString(6, "Null");
            pstmt.setString(7, "Null");
            pstmt.setInt(8, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Message> findFavstar() {
        ArrayList<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int likes = rs.getInt("likes");
            if (likes > 10) {
                int id = rs.getInt("id");
                int quotes = quotesCount(id);
                int replies = repliesCount(id);
                int retweets = retweetsCount(id);
                String senderUsername = rs.getString("sender");
                User user = otherProfileInfo(senderUsername);
                Message message = new Message(rs.getString("id"), rs.getString("type"), rs.getString("written"), rs.getString("photo"),
                        rs.getString("video"), rs.getString("date"), rs.getString("sender"), rs.getString("reciever"),
                        rs.getString("targetMessageId"), rs.getInt("likes"), quotes, replies, retweets);
                message.setUser(user);
                messages.add(message);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public boolean findMessageId(int messageId) {
        String sql = "SELECT id FROM Message";
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (messageId == rs.getInt(messageId)) {
                    find = true;
                    break;
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return find;
    }

    public void addReply(String username, int replyingMessageId, String message, String date) {
        String sql = "INSERT INTO Message(type,written,date,sender,targetmessageid,photo,video) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "reply");
            pstmt.setString(2, message);
            pstmt.setString(3, date);
            pstmt.setString(4, username);
            pstmt.setInt(5, replyingMessageId);
            pstmt.setString(6, "Null");
            pstmt.setString(7, "Null");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> findHashtagInWrittenTweets(String hashtag) {
        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM Message WHERE written LIKE '%" + hashtag + "%'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ids;
    }

    public void addHashtag(String word, int messageId) {
        String sql = "INSERT INTO Hashtag(word,messageId) VALUES(?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.setInt(2, messageId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void block(String blocker, String blocked) {
        String sql = "INSERT INTO Blacklist(blocker,blocked) VALUES (?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, blocker);
            pstmt.setString(2, blocked);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unBlock(String blocker, String blocked) {
        String sql = "DELETE FROM Blacklist WHERE blocker = '" + blocker + "' AND blocked = '" + blocked + "'";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean findFollowing(String follower, String following) {
        String sql = "SELECT * FROM Follow WHERE followerId = '" + follower + "' AND followingId = '" + following + "' ";
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (follower.equals(rs.getString("followerId")) && following.equals(rs.getString("followingId"))) {
                find = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    public boolean findBlocked(String me, String other) {
        String sql = "SELECT * FROM Blacklist WHERE blocker = '" + me + "' AND blocked = '" + other + "' ";
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (me.equals(rs.getString("blocker")) && other.equals(rs.getString("blocked"))) {
                find = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    public boolean findLike(String username, int messageId) {
        String sql = "SELECT * FROM Like WHERE likerUsername = '" + username + "' AND messageId = '" + messageId + "' ";
        ;
        boolean find = false;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (username.equals(rs.getString("likerUsername")) && messageId == rs.getInt("messageId")) {
                find = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    public void like(String username, int messageId) {
        if (!findLike(username, messageId)) {
            String sql1 = "UPDATE Message SET likes=likes+1 WHERE id = '" + messageId + "'";
            try (Connection conn = connect()) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql1);
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            String sql = "INSERT INTO Like(messageId,likerUsername) VALUES(?,?)";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, messageId);
                pstmt.setString(2, username);
                ;
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public ArrayList<Message> timeLineHandler(ArrayList<String> followingsUsernames, String username) {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < followingsUsernames.size(); i++) {
            String sql = "SELECT * FROM Message WHERE sender = '" + followingsUsernames.get(i) + "'";
            try (Connection conn = connect()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int quotes = quotesCount(id);
                    int replies = repliesCount(id);
                    int retweets = retweetsCount(id);
                    User user = otherProfileInfo(followingsUsernames.get(i));
                    Message message = new Message(rs.getString("id"), rs.getString("type"), rs.getString("written"), rs.getString("photo"),
                            rs.getString("video"), rs.getString("date"), rs.getString("sender"), rs.getString("reciever"),
                            rs.getString("targetMessageId"), rs.getInt("likes"), quotes, replies, retweets);
                    message.setUser(user);
                    message.setHasLiked(findLike(username, Integer.parseInt(message.getMessageId())));
                    messages.add(message);
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        ArrayList<Message> favstars = findFavstar();
        messages.addAll(favstars);
        Collections.sort(messages);
        return messages;
    }

    public int quotesCount(int targetMessageId) {
        int cnt = 0;
        String sql = "SELECT COUNT(*) AS count FROM Message WHERE targetMessageId  = '" + targetMessageId + "' AND type = 'quote'";
        return cnt(cnt, sql);
    }

    public int repliesCount(int targetMessageId) {
        int cnt = 0;
        String sql = "SELECT COUNT(*) AS count FROM Message WHERE targetMessageId  = '" + targetMessageId + "' AND type = 'reply'";
        return cnt(cnt, sql);
    }

    public int retweetsCount(int targetMessageId) {
        int cnt = 0;
        String sql = "SELECT COUNT(*) AS count FROM Message WHERE targetMessageId  = '" + targetMessageId + "' AND type = 'retweet'";
        return cnt(cnt, sql);
    }

    private int cnt(int cnt, String sql) {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            cnt = rs.getInt("count");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cnt;
    }

    public ArrayList<String> getFollowingsForTimeLine(String username) {
        ArrayList<String> followings = new ArrayList<>();
        String sql = "SELECT * FROM Follow WHERE followerId = '" + username + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                followings.add(rs.getString("followingId"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return followings;
    }

    public ArrayList<User> getFollowings(String username) {
        ArrayList<String> followings = new ArrayList<>();
        String sql = "SELECT * FROM Follow WHERE followerId = '" + username + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                followings.add(rs.getString("followingId"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("getFollowings", "", "", "", "", "", ""));
        for (int i = 0; i < followings.size(); i++) {
            String sql2 = "SELECT * FROM User WHERE username = '" + followings.get(i) + "'";
            try (Connection conn = connect()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql2);
                while (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"),
                            rs.getString("phonenumber"), rs.getString("country"), rs.getString("birthdate"));
                    user.setBio(rs.getString("bio"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setHeader(rs.getString("header"));
                    user.setWebsite(rs.getString("website"));
                    user.setLocation(rs.getString("location"));
                    users.add(user);
                    rs.close();
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return users;
    }

    public ArrayList<User> getFollowers(String username) {
        ArrayList<String> followers = new ArrayList<>();
        String sql = "SELECT * FROM Follow WHERE followingId = '" + username + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                followers.add(rs.getString("followerId"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("getFollowers", "", "", "", "", "", ""));
        for (int i = 0; i < followers.size(); i++) {
            String sql2 = "SELECT * FROM User WHERE username = '" + followers.get(i) + "'";
            try (Connection conn = connect()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql2);
                while (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"),
                            rs.getString("phonenumber"), rs.getString("country"), rs.getString("birthdate"));
                    user.setBio(rs.getString("bio"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setHeader(rs.getString("header"));
                    user.setWebsite(rs.getString("website"));
                    user.setLocation(rs.getString("location"));
                    users.add(user);
                    rs.close();
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return users;
    }


    public void editPassword(String username, String newPassword) {
        String sql = "UPDATE User SET password = ? WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User profileInfo(String username) {
        String sql = "SELECT * FROM User WHERE username= '" + username + "'";
        User user = null;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"),
                    rs.getString("phonenumber"), rs.getString("country"), rs.getString("birthdate"));
            user.setBio(rs.getString("bio"));
            user.setAvatar(rs.getString("avatar"));
            user.setHeader(rs.getString("header"));
            user.setWebsite(rs.getString("website"));
            user.setLocation(rs.getString("location"));
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<Integer> followerAndFollowingNumber(String username) {
        ArrayList<Integer> followerAndFollowingNum = new ArrayList<>();
        int cnt1 = 0;
        String sql1 = "SELECT COUNT(*) AS count FROM Follow WHERE followingId  = '" + username + "'";
        int cnt2 = 0;
        String sql2 = "SELECT COUNT(*) AS count FROM Follow WHERE followerId  = '" + username + "'";
        followerAndFollowingNum.add(cnt(cnt1, sql1));
        followerAndFollowingNum.add(cnt(cnt2, sql2));
        return followerAndFollowingNum;
    }

    public ArrayList<User> searchUser(String word, String myUsername) {
        ArrayList<String> usernames = new ArrayList<>();
        String sql = "SELECT * FROM User WHERE username = '" + word + "' OR fullname = '" + word + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("searchUser", "", "", "", "", "", ""));
        for (int i = 0; i < usernames.size(); i++) {
            String sql2 = "SELECT * FROM User WHERE username = '" + usernames.get(i) + "'";
            try (Connection conn = connect()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql2);
                while (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"),
                            rs.getString("phonenumber"), rs.getString("country"), rs.getString("birthdate"));
                    user.setBio(rs.getString("bio"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setHeader(rs.getString("header"));
                    user.setWebsite(rs.getString("website"));
                    user.setLocation(rs.getString("location"));
                    if (!CheckBlock(word, myUsername)) {
                        users.add(user);
                    }
                    rs.close();
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return users;
    }

    public ArrayList<User> getBlocked(String username) {
        ArrayList<String> blocked = new ArrayList<>();
        String sql = "SELECT * FROM blackList WHERE blocker = '" + username + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                blocked.add(rs.getString("blocked"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("getBlocked", "", "", "", "", "", ""));
        for (int i = 0; i < blocked.size(); i++) {
            String sql2 = "SELECT * FROM User WHERE username = '" + blocked.get(i) + "'";
            try (Connection conn = connect()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql2);
                while (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"),
                            rs.getString("phonenumber"), rs.getString("country"), rs.getString("birthdate"));
                    user.setBio(rs.getString("bio"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setHeader(rs.getString("header"));
                    user.setWebsite(rs.getString("website"));
                    user.setLocation(rs.getString("location"));
                    users.add(user);
                    rs.close();
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return users;
    }

    public User otherProfileInfo(String username) {
        String sql = "SELECT * FROM User WHERE username = '" + username + "'";
        User user = null;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"),
                    rs.getString("phonenumber"), rs.getString("country"), rs.getString("birthdate"));
            user.setBio(rs.getString("bio"));
            user.setAvatar(rs.getString("avatar"));
            user.setHeader(rs.getString("header"));
            user.setWebsite(rs.getString("website"));
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean CheckBlock(String blocker, String blocked) {
        int cnt = 0;
        String sql = "SELECT COUNT(*) AS count FROM Blacklist WHERE blocker = '" + blocker + "' AND blocked = '" + blocked + "'";
        cnt = cnt(cnt, sql);
        if (cnt > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<Message> hashtagExplorerHandler(String username, String hashtag) {
        ArrayList<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message WHERE written LIKE '%" + hashtag + "%'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int quotes = quotesCount(id);
                int replies = repliesCount(id);
                int retweets = retweetsCount(id);
                String senderUsername = rs.getString("sender");
                User user = otherProfileInfo(senderUsername);
                Message message = new Message(rs.getString("id"), rs.getString("type"), rs.getString("written"), rs.getString("photo"),
                        rs.getString("video"), rs.getString("date"), rs.getString("sender"), rs.getString("reciever"),
                        rs.getString("targetMessageId"), rs.getInt("likes"), quotes, replies, retweets);
                message.setUser(user);
                message.setHasLiked(findLike(username, Integer.parseInt(message.getMessageId())));
                messages.add(message);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(messages);
        return messages;
    }

    public void createPoll(String description, String numberOfChoices, String choices, String creatorId, String date) {
        String sql = "INSERT INTO Poll(description,numberOfChoices,choices,creatorId,creationTime) VALUES (?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.setString(2, numberOfChoices);
            pstmt.setString(3, choices);
            pstmt.setString(4, creatorId);
            pstmt.setString(5, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<String> findAllFollowings(String username) {
        ArrayList<String> followings = new ArrayList<>();
        followings.add(username);
        String sql = "SELECT * FROM Follow WHERE followerId = '" + username + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                followings.add(rs.getString("followingId"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return followings;
    }

    public void addDirectMessage(String sender, String reciever, String message, String date) {
        String sql = "INSERT INTO Direct(sender,reciever,message,date) VALUES (?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sender);
            pstmt.setString(2, reciever);
            pstmt.setString(3, message);
            pstmt.setString(4, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<DirectMessage> getDirectMessages(String sender, String reciever) {
        ArrayList<DirectMessage> messages = new ArrayList<>();
        String sql = "SELECT * FROM Direct WHERE (sender = '" + sender + "' AND reciever = '" + reciever + "') OR (sender = '" + reciever + "' AND reciever = '" + sender + "')";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DirectMessage message = new DirectMessage(rs.getString("sender"), rs.getString("reciever"), rs.getString("message"), rs.getString("date"));
                message.setId(rs.getInt("id"));
                messages.add(message);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(messages);
        return messages;
    }

    public ArrayList<User> getDirectUsers(ArrayList<String> followings) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("directUsers", "", "", "", "", "", ""));
        for (int i = 0; i < followings.size(); i++) {
            String sql2 = "SELECT * FROM User WHERE username = '" + followings.get(i) + "'";
            try (Connection conn = connect()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql2);
                while (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"),
                            rs.getString("phonenumber"), rs.getString("country"), rs.getString("birthdate"));
                    user.setBio(rs.getString("bio"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setHeader(rs.getString("header"));
                    user.setWebsite(rs.getString("website"));
                    user.setLocation(rs.getString("location"));
                    users.add(user);
                    rs.close();
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return users;
    }

    public ArrayList<String> getFollowingsForDirect(String username) {
        ArrayList<String> followings = new ArrayList<>();
        String sql = "SELECT * FROM Follow WHERE followerId = '" + username + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String user1 = rs.getString("followingId");
                if (isFollowed(user1, username)) {
                    followings.add(user1);
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return followings;
    }

    public boolean isFollowed(String user1, String user2) {
        boolean flag = false;
        String sql = "SELECT * FROM Follow WHERE followerId = '" + user1 + "' AND followingId = '" + user2 + "'";
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                flag = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

}

