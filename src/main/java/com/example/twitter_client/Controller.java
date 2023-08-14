package com.example.twitter_client;

import animatefx.animation.FadeIn;
import com.example.twitter_client.Server.DirectMessage;
import com.example.twitter_client.Server.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import com.example.twitter_client.Server.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private Pane pnSignIn;
    @FXML
    private Pane pnSignUp;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button getStarted;
    @FXML
    private ImageView btnBack;
    @FXML
    private TextField regUserName;
    @FXML
    private TextField regPass;
    @FXML
    private TextField regEmail;
    @FXML
    private TextField regFullName;
    @FXML
    private TextField regPhoneNo;
    @FXML
    private TextField regBirth;
    @FXML
    private Label controlRegLabel;
    @FXML
    private Label success;
    @FXML
    private Label goBack;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
    @FXML
    private Label unsuccesfulEmailLabel;
    @FXML
    private Label unsuccesfulUsernameLabel;
    @FXML
    private Label unsuccesfulNumberLabel;
    @FXML
    private Label unsuccesfulOneOfLabel;
    @FXML
    private Label unsuccessfulPasswordLabel;
    @FXML
    private Label unsuccessfulUsernameLabel;
    @FXML
    private Button directBtn;

    @FXML
    private Button explorerBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button newTweetBtn;

    @FXML
    private Button profileBtn;

    public static ServerInput input;
    @FXML
    private Button photoTweetBtn;

    @FXML
    private Button poll;

    @FXML
    private Button simpleTweetBtn;

    @FXML
    private Button videoTweetBtn;
    @FXML
    private TextField writtenTextField;
    @FXML
    private TextField photoPathTextField;

    @FXML
    private TextField writtenPhotoTextField;
    @FXML
    private TextField videoPathTextField;
    @FXML
    private Button sendSimpleTweetBtn;
    @FXML
    private Button sendPhotoTweetBtn;
    @FXML
    private Button sendVideoTweetBtn;
    @FXML
    private TextField writtenVideoTextField;

    @FXML
    private TextField messageIdTextField;

    @FXML
    private Button sendQuoteBtn;
    @FXML
    private TextField QuoteTextField;
    @FXML
    private TextField retweetMessageIdTextField;

    @FXML
    private Button sendRetweetBtn;
    @FXML
    private TextField ReplyTextField;

    @FXML
    private TextField replyMessageIdTextField;

    @FXML
    private Button sendReplyBtn;
    @FXML
    private ImageView avatarImage;

    @FXML
    private Label bioLabel;

    @FXML
    private Button blackListBtn;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Label followersCountLabel;

    @FXML
    private Button followersListBtn;

    @FXML
    private Label followingCountLabel;

    @FXML
    private Button followingListBtn;

    @FXML
    private ImageView headerImage;
    @FXML
    private Label locationLabel;

    @FXML
    private Label usernameProfileLabel;
    @FXML
    private Button saveEditProfileBtn;

    @FXML
    private TextField setAvatarTextField;

    @FXML
    private TextField setBioTextField;

    @FXML
    private TextField setHeaderTextField;

    @FXML
    private TextField setLocationTextField;

    @FXML
    private TextField setPasswordTextField;

    @FXML
    private TextField setWebSiteTextField;
    @FXML
    private Button searchHashtagBtn;

    @FXML
    private Button searchUserBtn;
    @FXML
    private ImageView OtherAvatarImage;

    @FXML
    private Button blockBtn;

    @FXML
    private Button followBtn;

    @FXML
    private Button followingBtn;

    @FXML
    private ImageView otherHeaderImage;

    @FXML
    private Label otherbioLabel;

    @FXML
    private Label otherfollowersCountLabel;

    @FXML
    private Label otherfollowingCountLabel;

    @FXML
    private Label otherlocationLabel;

    @FXML
    private Label otherusernameProfileLabel;

    @FXML
    private Label otherwebsiteLabel;

    @FXML
    private Button unBlockBtn;
    @FXML
    private Label webSiteLabel;

    @FXML
    private TextField explorerTextField;

    @FXML
    private Button searchBtn;
    @FXML
    private TextField hashtagExplorerTextField;

    @FXML
    private Button hashtagSearchBtn;
    @FXML
    private Button followerBackBtn;
    @FXML
    private Button followingBackBtn;
    @FXML
    private Button seeOthesFollowers;

    @FXML
    private Button seeOthesFollowings;
    @FXML
    private Button createPollBtn;
    @FXML
    private Button polls;
    @FXML
    private ComboBox<String> countryComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(countryComboBox != null) {
            countryComboBox.setItems(FXCollections.observableArrayList("Armenia", "Australia", "Belarus", "Brazil"
                    , "Bulgaria", "Canada", "China", "Colombia", "Denmark", "Egypt", "France", "Germany", "India", "Iran",
                    "Iraq", "Italy", "Japan", "Kenya", "Mexico", "Netherlands", "Portugal", "Qatar", "Spain", "Turkey",
                    "United States", "United Kingdom", "Viet Nam", "Yemen"));
        }
    }


    public void signup() throws IOException {
        ClientRequest req = new ClientRequest("signup", "", regUserName.getText() + "$" + regPass.getText() + "$" + regFullName.getText() + "$" + regEmail.getText() + "$" + regPhoneNo.getText() + "$" + countryComboBox.getValue() + "$" + regBirth.getText());
        input.send(req);
    }

    @FXML
    public void alertRepeatedEmail() {
        unsuccesfulEmailLabel.setOpacity(1);
        regUserName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regFullName.setText("");
        regPhoneNo.setText("");
        regBirth.setText("");
        controlRegLabel.setOpacity(0);
    }

    @FXML
    public void alertRepeatedUsername() {
        unsuccesfulUsernameLabel.setOpacity(1);
        regUserName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regFullName.setText("");
        regPhoneNo.setText("");
        regBirth.setText("");
        controlRegLabel.setOpacity(0);
    }

    @FXML
    public void alertRepeatedNumber() {
        unsuccesfulNumberLabel.setOpacity(1);
        regUserName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regFullName.setText("");
        regPhoneNo.setText("");
        regBirth.setText("");
        controlRegLabel.setOpacity(0);
    }

    @FXML
    public void alertOneOfEmailOrNumber() {
        unsuccesfulOneOfLabel.setOpacity(1);
        regUserName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regFullName.setText("");
        regPhoneNo.setText("");
        regBirth.setText("");
        controlRegLabel.setOpacity(0);
    }

    public void signupOK() {
        goBack.setOpacity(1);
        success.setOpacity(1);
        makeDefaultSignup();
        if (controlRegLabel.getOpacity() == 1) {
            controlRegLabel.setOpacity(0);
        }
    }

    private void makeDefaultSignup() {
        regUserName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regFullName.setText("");
        regPhoneNo.setText("");
        regBirth.setText("");
        controlRegLabel.setOpacity(0);
        unsuccesfulNumberLabel.setOpacity(0);
        unsuccesfulUsernameLabel.setOpacity(0);
        unsuccesfulOneOfLabel.setOpacity(0);
        unsuccesfulEmailLabel.setOpacity(0);
    }

    public static String username = "";

    public void signin() throws IOException {
        ClientRequest req = new ClientRequest("signin", "", userName.getText() + "$" + passWord.getText());
        username = userName.getText();
        input.send(req);
    }

    @FXML
    public void alertWrongPassword() {
        unsuccessfulPasswordLabel.setOpacity(1);
    }

    @FXML
    public void alertUserNotFound() {
        unsuccessfulUsernameLabel.setOpacity(1);
    }

    public void signinOK() throws IOException {
        Scene scene = setControllerTimeLine();
        changeWindowToTimeline(scene);
        makeDefaultSignin();
        if (controlRegLabel.getOpacity() == 1) {
            controlRegLabel.setOpacity(0);
        }
    }

    private void makeDefaultSignin() {
        userName.setText("");
        passWord.setText("");
        unsuccessfulPasswordLabel.setOpacity(0);
        unsuccessfulUsernameLabel.setOpacity(0);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource().equals(btnSignUp)) {
            new FadeIn(pnSignUp).play();
            pnSignUp.toFront();
        }
        if (event.getSource().equals(getStarted)) {
            new FadeIn(pnSignIn).play();
            pnSignIn.toFront();
            unsuccessfulPasswordLabel.setOpacity(0);
            unsuccessfulUsernameLabel.setOpacity(0);
            userName.setText("");
            passWord.setText("");
        }
        if (event.getSource().equals(homeBtn)) {
            Scene scene = setControllerTimeLine();
            changeWindowToTimeline(scene);
        }
        if (event.getSource().equals(newTweetBtn)) {
            changeWindowNewTweet();
        }
        if (event.getSource().equals(simpleTweetBtn)) {
            changeWindowSimpleTweet();
        }
        if (event.getSource().equals(photoTweetBtn)) {
            changeWindowPhotoTweet();
        }
        if (event.getSource().equals(videoTweetBtn)) {
            changeWindowVideoTweet();
        }
        if (event.getSource().equals(sendSimpleTweetBtn)) {
            Scene scene = setControllerTimeLine();
            ClientRequest req = new ClientRequest("tweet-written-message", ServerOutput.token, writtenTextField.getText());
            input.send(req);
            changeWindowToTimeline(scene);
        }
        if (event.getSource().equals(sendPhotoTweetBtn)) {
            String photoPath = photoPathTextField.getText();
            ClientPic.SendPic("photoTweet", photoPath);
            Scene scene = setControllerTimeLine();
            ClientRequest req = new ClientRequest("tweet-photo", ServerOutput.token, writtenPhotoTextField.getText());
            input.send(req);
            changeWindowToTimeline(scene);
        }
        if (event.getSource().equals(sendVideoTweetBtn)) {
            String videoPath = videoPathTextField.getText();
            ClientPic.SendPic("videoTweet", videoPath);
            Scene scene = setControllerTimeLine();
            ClientRequest req = new ClientRequest("video-photo", ServerOutput.token, writtenVideoTextField.getText());
            input.send(req);
            changeWindowToTimeline(scene);
        }
        if (event.getSource().equals(sendQuoteBtn)) {
            targetMessageId = Integer.parseInt(messageIdTextField.getText());
            Scene scene = setControllerTimeLine();
            ClientRequest req = new ClientRequest("quote", ServerOutput.token, targetMessageId + "$" + QuoteTextField.getText());
            input.send(req);
            changeWindowToTimeline(scene);
        }
        if (event.getSource().equals(sendRetweetBtn)) {
            targetMessageId = Integer.parseInt(retweetMessageIdTextField.getText());
            Scene scene = setControllerTimeLine();
            ClientRequest req = new ClientRequest("retweet", ServerOutput.token, String.valueOf(targetMessageId));
            input.send(req);
            changeWindowToTimeline(scene);
        }
        if (event.getSource().equals(sendReplyBtn)) {
            targetMessageId = Integer.parseInt(replyMessageIdTextField.getText());
            Scene scene = setControllerTimeLine();
            ClientRequest req = new ClientRequest("reply", ServerOutput.token, targetMessageId + "$" + ReplyTextField.getText());
            input.send(req);
            changeWindowToTimeline(scene);
        }
        if (event.getSource().equals(followerBackBtn)) {
            Scene scene = setProfileController();
            changeWindowToProfile(scene);
        }
        if (event.getSource().equals(followingBackBtn)) {
            Scene scene = setProfileController();
            changeWindowToProfile(scene);
        }
        if (event.getSource().equals(profileBtn)) {
            Scene scene = setProfileController();
            changeWindowToProfile(scene);
        }
        if (event.getSource().equals(saveEditProfileBtn)) {
            if (!setHeaderTextField.getText().equals("")) {
                ClientRequest req1 = new ClientRequest("header", ServerOutput.token, "");
                ClientPic.SendPic("header", setHeaderTextField.getText());
                input.send(req1);
            }
            if (!setAvatarTextField.getText().equals("")) {
                ClientRequest req2 = new ClientRequest("avatar", ServerOutput.token, "");
                ClientPic.SendPic("avatar", setAvatarTextField.getText());
                input.send(req2);
            }
            if (!setBioTextField.getText().equals("")) {
                ClientRequest req3 = new ClientRequest("bio", ServerOutput.token, setBioTextField.getText());
                input.send(req3);
            }
            if (!setWebSiteTextField.getText().equals("")) {
                ClientRequest req4 = new ClientRequest("website", ServerOutput.token, setWebSiteTextField.getText());
                input.send(req4);
            }
            if (!setLocationTextField.getText().equals("")) {
                ClientRequest req5 = new ClientRequest("location", ServerOutput.token, setLocationTextField.getText());
                input.send(req5);
            }
            if (!setPasswordTextField.getText().equals("")) {
                ClientRequest req7 = new ClientRequest("edit-password", ServerOutput.token, setPasswordTextField.getText());
                input.send(req7);
            }
            Scene scene = setProfileController();
            changeWindowToProfile(scene);
        }
        if (event.getSource().equals(blockBtn)) {
            ClientRequest req = new ClientRequest("block", ServerOutput.token, otherusernameProfileLabel.getText());
            input.send(req);
            blockBtn.setVisible(false);
            unBlockBtn.setVisible(true);
        }
        if (event.getSource().equals(unBlockBtn)) {
            ClientRequest req = new ClientRequest("unblock", ServerOutput.token, otherusernameProfileLabel.getText());
            input.send(req);
            unBlockBtn.setVisible(false);
            blockBtn.setVisible(true);
        }
        if (event.getSource().equals(followBtn)) {
            ClientRequest req = new ClientRequest("follow", ServerOutput.token, otherusernameProfileLabel.getText());
            input.send(req);
            followBtn.setVisible(false);
            followingBtn.setVisible(true);
        }
        if (event.getSource().equals(followingBtn)) {
            ClientRequest req = new ClientRequest("unfollow", ServerOutput.token, otherusernameProfileLabel.getText());
            input.send(req);
            followBtn.setVisible(true);
            followingBtn.setVisible(false);
        }
        if (event.getSource().equals(editProfileBtn)) {
            changeWindowToEditProfile();
        }
        if (event.getSource().equals(followersListBtn) || event.getSource().equals(seeOthesFollowers)) {
            Scene scene = setFollwersListController();
            changeWindowToFollowers(scene);
        }
        if (event.getSource().equals(followingListBtn) || event.getSource().equals(seeOthesFollowings)) {
            Scene scene = setFollowingController();
            changeWindowToFollowing(scene);
        }
        if (event.getSource().equals(blackListBtn)) {
            Scene scene = setBlacklistController();
            changeWindowToBlackList(scene);
        }
        if (event.getSource().equals(explorerBtn)) {
            changeWindowToExplorer();
        }
        if (event.getSource().equals(searchBtn)) {
            ClientRequest req = new ClientRequest("searchUser", ServerOutput.token, explorerTextField.getText());
            input.send(req);
        }
        if (event.getSource().equals(searchUserBtn)) {
            changeWindowToExplorerUser();
        }
        if (event.getSource().equals(searchHashtagBtn)) {
            changeWindowToExplorerHashtag();
        }
        if (event.getSource().equals(hashtagSearchBtn)) {
            ClientRequest req = new ClientRequest("searchHashtag", ServerOutput.token, hashtagExplorerTextField.getText());
            input.send(req);
        }
        if (event.getSource().equals(createPollBtn)) {
            Scene scene = setControllerCreatePoll();
            changeWindowToCreatePoll(scene);
            Main.controller.setPollForm();
        }
        if (event.getSource().equals(polls)) {
            Scene scene = setPollController();
            changeWindowToPolls(scene);
        }
        if (event.getSource().equals(directBtn)) {
            Scene scene = setDirectController();
            changeWindowToDirect(scene);
        }
        if (event.getSource().equals(directSendBtn)) {
            Main.controller.send(clientDirect);
        }
        if(event.getSource().equals(backToDirects)){
            Scene scene = setDirectController();
            changeWindowToDirect(scene);
        }
    }

    int targetMessageId = 0;

    public void alertWrittenInvalid() {

    }

    public void SimpleTweetOK() throws IOException {
        Scene scene = setControllerTimeLine();
        changeWindowToTimeline(scene);
    }

    public void alertPhotoInvalid() {

    }

    public void photoTweetOK() throws IOException {
        Scene scene = setControllerTimeLine();
        changeWindowToTimeline(scene);
    }

    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnBack) {
            new FadeIn(pnSignIn).play();
            pnSignIn.toFront();
        }
        regFullName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regPhoneNo.setText("");
    }

    public Scene setControllerTimeLine() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("timeLine.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 354, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("timeLine", ServerOutput.token, "");
        input.send(req);
        return scene;
    }

    public void changeWindowToTimeline(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }

        );
    }

    @FXML
    private ScrollPane timeLineScrollPane;
    private VBox tweetContent;
    public void setTweetList(ArrayList<Message> tweetList) { //shows tweets in timeline
        Platform.runLater(() -> {
            String targetWritten = "";
            String targetUsername = "";
            Message targetTweet = null;
            tweetContent = new VBox();
            timeLineScrollPane.setContent(tweetContent);
            tweetContent.setSpacing(10);
            tweetContent.setStyle("-fx-background-color:gray;" +
                    "-fx-padding: 8;");
            for (Message tweet : tweetList) {
                boolean hasPhoto = false;
                if (tweet.getType().equals("tweet")) {
                    if (!tweet.getPhotoPath().equals("Null")) {
                        hasPhoto = true;
                    }
                    TweetComponent tweetComponent = new TweetComponent(tweet, hasPhoto,tweet.isHasLiked(),username);
                    tweetContent.getChildren().add(tweetComponent);
                } else if (tweet.getType().equals("quote")) {
                    if (!tweet.getPhotoPath().equals("Null")) {
                        hasPhoto = true;
                    }
                    for (Message t : tweetList) {
                        if (t.getMessageId().equals(tweet.getTargetMassageId())) {
                            targetUsername = t.getSenderUsername();
                            targetWritten = t.getWritten();
                            break;
                        }
                    }
                    QuoteComponent quoteComponent = new QuoteComponent(tweet, targetUsername, targetWritten, "quote",tweet.isHasLiked(),username);
                    tweetContent.getChildren().add(quoteComponent);
                } else if (tweet.getType().equals("retweet")) {
                    String date = tweet.getDate();
                    for (Message t : tweetList) {
                        if (t.getMessageId().equals(tweet.getTargetMassageId())) {
                            targetTweet = t;
                            break;
                        }
                    }
                    assert targetTweet != null;
                    if (!targetTweet.getPhotoPath().equals("Null")) {
                        hasPhoto = true;
                    }
                    RetweetComponent retweetComponent = new RetweetComponent(targetTweet, hasPhoto, username, tweet.getMessageId(),date,tweet,targetTweet.isHasLiked());
                    tweetContent.getChildren().add(retweetComponent);
                }
            }
        });
    }

    public void changeWindowNewTweet() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowSimpleTweet() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("simpleTweet.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowPhotoTweet() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tweetWithPhoto.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowVideoTweet() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tweetWithVideo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowToQuote() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("quote.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowToRetweet() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("retweet.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowToReply() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("reply.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowToEditProfile() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editProfile.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene setProfileController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("profile", ServerOutput.token, "");
        input.send(req);
        return scene;
    }


    public void changeWindowToProfile(Scene scene) throws IOException {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );

    }
    @FXML
    private Label fullnameLabels;

    public void setProfile(User user) throws IOException {
        Platform.runLater(() -> {
            if (!user.getHeader().equals("Null")) {
                headerImage.setImage(new Image(user.getHeader()));
            }
            if (!user.getAvatar().equals("Null")) {
                avatarImage.setImage(new Image(user.getAvatar()));
            }
            fullnameLabels.setText(user.getFullName());
            usernameProfileLabel.setText(user.getUserName());
            bioLabel.setText(user.getBio());
            locationLabel.setText(user.getLocation());
            webSiteLabel.setText(user.getWebsite());
        });
        ClientRequest req = new ClientRequest("followerAndFollowingNumber", ServerOutput.token, user.getUserName());
        input.send(req);
    }
    @FXML
    private Label otherFullnameLabel;

    public void setProfileOther(String username, String header, String avatar, String bio, String location, String website,String fullname) throws IOException {
        Platform.runLater(() -> {
            if (!header.equals("Null")) {
                otherHeaderImage.setImage(new Image(header));
            }
            if (!avatar.equals("Null")) {
                OtherAvatarImage.setImage(new Image(avatar));
            }
            otherFullnameLabel.setText(fullname);
            otherusernameProfileLabel.setText(username);
            otherbioLabel.setText(bio);
            otherlocationLabel.setText(location);
            otherwebsiteLabel.setText(website);
        });
        ClientRequest req = new ClientRequest("followerAndFollowingNumberOther", ServerOutput.token, username);
        input.send(req);
    }

    public void setNumbersOther(ArrayList<Integer> numbers) {
        Platform.runLater(() -> {
            otherfollowersCountLabel.setText(Integer.toString(numbers.get(0)));
            otherfollowingCountLabel.setText(Integer.toString(numbers.get(1)));
        });
    }

    public void setNumbers(ArrayList<Integer> numbers) {
        Platform.runLater(() -> {
            followersCountLabel.setText(Integer.toString(numbers.get(0)));
            followingCountLabel.setText(Integer.toString(numbers.get(1)));
        });
    }
    public boolean is_following = false;
    public boolean is_blocked = false;
    public Scene setControllerAnotherUser(String username1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profileOther.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req1 = new ClientRequest("is-following",ServerOutput.token,username1);
        input.send(req1);
        ClientRequest req2 = new ClientRequest("is-blocked",ServerOutput.token,username1);
        input.send(req2);
        ClientRequest req = new ClientRequest("anotherUserProfile", ServerOutput.token, username1);
        input.send(req);
        return scene;
    }

    public void changeWindowToAnotherUser(Scene scene) throws IOException {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    if(is_following){
                        followBtn.setVisible(false);
                        followingBtn.setVisible(true);
                    }else {
                        followBtn.setVisible(true);
                        followingBtn.setVisible(false);
                    }
                    if(is_blocked){
                        unBlockBtn.setVisible(true);
                        blockBtn.setVisible(false);
                    }
                    else{
                        unBlockBtn.setVisible(false);
                        blockBtn.setVisible(true);
                    }
                    Main.primaryStage.show();
                }
        );
    }

    public Scene setFollwersListController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("followers.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("seeFollowers", ServerOutput.token, "");
        input.send(req);
        return scene;
    }

    public void changeWindowToFollowers(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );

    }

    @FXML
    private ScrollPane followersListScrollPane;
    private VBox followersContent;

    public void setFollowersInFollowersList(ArrayList<User> users) {
        Platform.runLater(() -> {
            followersContent = new VBox();
            followersListScrollPane.setContent(followersContent);
            followersContent.setSpacing(10);
            followersContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");
            for (User user : users) {
                ProfileComponent profileComponent = new ProfileComponent(user, "profile");
                followersContent.getChildren().add(profileComponent);
            }
        });
    }

    public Scene setFollowingController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("following.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("seeFollowings", ServerOutput.token, "");
        input.send(req);
        return scene;
    }

    public void changeWindowToFollowing(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );

    }

    @FXML
    private ScrollPane followingListScrollpane;
    private VBox followingContent;

    public void setFollowingsInFollowingsList(ArrayList<User> users) {
        Platform.runLater(() -> {
            followingContent = new VBox();
            followingListScrollpane.setContent(followingContent);
            followingContent.setSpacing(10);
            followingContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");
            for (User user : users) {
                ProfileComponent profileComponent = new ProfileComponent(user, "profile");
                followingContent.getChildren().add(profileComponent);
            }
        });
    }

    public Scene setBlacklistController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("blackList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("seeBlocked", ServerOutput.token, "");
        input.send(req);
        return scene;
    }

    public void changeWindowToBlackList(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );
    }

    @FXML
    private ScrollPane blackListScrollpane;
    private VBox blockedContent;

    public void setBlackList(ArrayList<User> users) {
        Platform.runLater(() -> {
            blockedContent = new VBox();
            blackListScrollpane.setContent(blockedContent);
            blockedContent.setSpacing(10);
            blockedContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");
            for (User user : users) {
                ProfileComponent profileComponent = new ProfileComponent(user, "profile");
                blockedContent.getChildren().add(profileComponent);
            }
        });
    }

    public void changeWindowToExplorer() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("explorerMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowToExplorerUser() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("exploreUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 330, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWindowToExplorerHashtag() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("explorerHashtag.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 354, 560);
            Main.controller = fxmlLoader.getController();
            Platform.runLater(() -> {
                        Main.primaryStage.setScene(scene);
                        Main.primaryStage.setResizable(false);
                        Main.primaryStage.show();
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ScrollPane hashtagListScrollPane;
    private VBox hashtagTweetContent;
    public void setHashtagList(ArrayList<Message> tweetList) { //shows tweets in hashtag explorer
        Platform.runLater(() -> {
            String targetWritten = "";
            String targetUsername = "";
            Message targetTweet = null;
            hashtagTweetContent = new VBox();
            hashtagListScrollPane.setContent(hashtagTweetContent);
            hashtagTweetContent.setSpacing(10);
            hashtagTweetContent.setStyle("-fx-background-color:gray;" +
                    "-fx-padding: 8;");
            for (Message tweet : tweetList) {
                boolean hasPhoto = false;
                if (!tweet.getPhotoPath().equals("Null")) {
                    hasPhoto = true;
                }
                if (tweet.getType().equals("tweet")) {
                    TweetComponent tweetComponent = new TweetComponent(tweet, hasPhoto,tweet.isHasLiked(),username);
                    hashtagTweetContent.getChildren().add(tweetComponent);
                } else if (tweet.getType().equals("quote")) {
                    for (Message t : tweetList) {
                        if (t.getMessageId().equals(tweet.getTargetMassageId())) {
                            targetUsername = t.getSenderUsername();
                            targetWritten = t.getWritten();
                            break;
                        }
                    }
                    QuoteComponent quoteComponent = new QuoteComponent(tweet, targetUsername, targetWritten, "quote",tweet.isHasLiked(),username);
                    hashtagTweetContent.getChildren().add(quoteComponent);
                } else if (tweet.getType().equals("retweet")) {
                    String date = tweet.getDate();
                    for (Message t : tweetList) {
                        if (t.getMessageId().equals(tweet.getTargetMassageId())) {
                            targetTweet = t;
                            break;
                        }
                    }
                    assert targetTweet != null;
                    RetweetComponent retweetComponent = new RetweetComponent(targetTweet, hasPhoto, username, tweet.getMessageId(),date,tweet,tweet.isHasLiked());
                    hashtagTweetContent.getChildren().add(retweetComponent);
                }
                else if (tweet.getType().equals("reply")) {
                    for (Message t : tweetList) {
                        if (t.getMessageId().equals(tweet.getTargetMassageId())) {
                            targetUsername = t.getSenderUsername();
                            targetWritten = t.getWritten();
                            break;
                        }
                    }
                    QuoteComponent replyComponent = new QuoteComponent(tweet, targetUsername, targetWritten, "reply",tweet.isHasLiked(),username);
                    hashtagTweetContent.getChildren().add(replyComponent);
                }
            }
        });
    }

    @FXML
    private ScrollPane explorerScrollpane;
    private VBox explorerContent;

    public void setUserInExplorer(ArrayList<User> users) {
        Platform.runLater(() -> {
            explorerContent = new VBox();
            explorerScrollpane.setContent(explorerContent);
            explorerContent.setSpacing(10);
            explorerContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");
            for (User user : users) {
                ProfileComponent profileComponent = new ProfileComponent(user, "profile");
                explorerContent.getChildren().add(profileComponent);
            }
        });
    }

    public Scene setControllerCreatePoll() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("poll.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        return scene;
    }

    public void changeWindowToCreatePoll(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );
    }

    @FXML
    private ScrollPane pollScrollPane;
    private VBox pollContent;

    public void setPollForm() {
        pollContent = new VBox();
        pollScrollPane.setContent(pollContent);
        pollContent.setSpacing(10);
        pollContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");
        PollInfo pollInfo = new PollInfo();
        Poll poll = new Poll(pollScrollPane, pollContent, pollInfo);
        pollContent.getChildren().add(poll);
    }

    public Scene setPollController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("voting.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("find-polls", ServerOutput.token, "");
        input.send(req);
        return scene;
    }

    public void changeWindowToPolls(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );
    }

    @FXML
    private ScrollPane pollsListScrollPane;
    private VBox pollCon;

    public void setPollList(ArrayList<PollInfo> polls) {
        Platform.runLater(() -> {
            pollCon = new VBox();
            pollsListScrollPane.setContent(pollCon);
            pollCon.setSpacing(10);
            pollCon.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");
            ArrayList<Integer> ids = new ArrayList<>();
            for (PollInfo p : polls) {
                PollForm pollForm = new PollForm(p);
                if (!ids.contains(Integer.parseInt(p.getId()))) {
                    pollCon.getChildren().add(pollForm);
                    ids.add(Integer.parseInt(p.getId()));
                }
            }
        });
    }

    public Scene setDirectController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("direct.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("usersInDirect", ServerOutput.token, username);
        input.send(req);
        return scene;
    }

    public void changeWindowToDirect(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );
    }

    @FXML
    private ScrollPane directScrollpane;
    private VBox directContent;

    public void setUsersInDirect(ArrayList<User> users) {
        Platform.runLater(() -> {
            directContent = new VBox();
            directScrollpane.setContent(directContent);
            directContent.setSpacing(10);
            directContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");
            for (User user : users) {
                ProfileComponent profileComponent = new ProfileComponent(user, "direct");
                directContent.getChildren().add(profileComponent);
            }
        });
    }

    public Scene setControllerChat(String username1) throws IOException {
        otherDirectUsername = username1;
        directConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chat.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("directMessages", ServerOutput.token, username1);
        input.send(req);
        return scene;
    }

    public void changeWindowToChat(Scene scene) throws IOException {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    recieverName.setText(otherDirectUsername);
                    Main.primaryStage.show();
                }
        );
    }

    public static ClientDirect clientDirect;
    public static String otherDirectUsername;
    @FXML
    private Button directSendBtn;

    @FXML
    private TextField msgField;

    @FXML
    public TextArea msgRoom;

    @FXML
    private Label recieverName;

    public void directConnection() {
        try {
            Socket socket = new Socket("localhost", 1989);
            clientDirect = new ClientDirect(socket);
            DirectMessage message = new DirectMessage(username, otherDirectUsername, "", "");
            clientDirect.getOutput().writeObject(message);
            Thread directThread = new Thread(clientDirect);
            directThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(ClientDirect clientDirect) throws IOException {
        ObjectOutputStream out = clientDirect.getOutput();
        String msg = msgField.getText();
        DirectMessage message = new DirectMessage(username, otherDirectUsername, msg, "");
        out.writeObject(message);
        msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        msgRoom.appendText("Me: " + msg + "\n");
        msgField.setText("");
    }

    public static void setDirectMessages(ArrayList<DirectMessage> messages) {
        clientDirect.setDirectMessages(messages);
    }
    static String targetIdForReply ;
    public Scene setReplyListController(String messageId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ReplyList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        Main.controller = fxmlLoader.getController();
        ClientRequest req = new ClientRequest("replyList", ServerOutput.token,messageId );
        input.send(req);
        targetIdForReply = messageId;
        return scene;
    }

    public void changeWindowToReplyList(Scene scene) {
        Platform.runLater(() -> {
                    Main.primaryStage.setScene(scene);
                    Main.primaryStage.setResizable(false);
                    Main.primaryStage.show();
                }
        );
    }

    @FXML
    private ScrollPane replyListScrollpane;

    @FXML
    private Label targetTweetId;
    private VBox replyContent;
    @FXML
    private Button backToDirects;


    public void setReplyList(ArrayList<Message> tweetList) { //shows replies in reply list
        Platform.runLater(() -> {
            String targetWritten = "";
            String targetUsername = "";
            replyContent = new VBox();
            replyListScrollpane.setContent(replyContent);
            replyContent.setSpacing(10);
            replyContent.setStyle("-fx-background-color:gray;" +
                    "-fx-padding: 8;");
            for (Message tweet : tweetList) {
                if (tweet.getType().equals("reply") && tweet.getTargetMassageId().equals(targetIdForReply)) {
                    for (Message t : tweetList) {
                        if (t.getMessageId().equals(targetIdForReply)) {
                            targetUsername = t.getSenderUsername();
                            targetWritten = t.getWritten();
                            targetTweetId.setText(targetIdForReply);
                            QuoteComponent replyComponent = new QuoteComponent(tweet, targetUsername, targetWritten, "reply",tweet.isHasLiked(),username);
                            replyContent.getChildren().add(replyComponent);
                            break;
                        }
                    }
                }
            }
        });
    }
}
