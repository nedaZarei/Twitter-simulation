package com.example.twitter_client;

import com.example.twitter_client.Server.Message;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RetweetComponent extends AnchorPane {
    private static Image redHeartImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\heart_red.png");
    private static Image grayHeartImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\heart_gray.png");
    private static Image quoteImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\quote.png");
    private static Image retImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\retweet.png");
    private static Image dotImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\three_dot.png");
    private static Image replyImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\replies.png");
    private static Image repliesImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\comment.png");
    private static Image profileImage = new Image("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\resources\\com\\example\\icons\\user.png");
    private ImageView heartImageView = new ImageView(grayHeartImage);
    private ImageView redHeartImageView = new ImageView(redHeartImage);
    private ImageView quoteImageView = new ImageView(quoteImage);
    private ImageView retImageView = new ImageView(retImage);
    private ImageView dotImageView = new ImageView(dotImage);
    private ImageView replyImageView = new ImageView(replyImage);
    private ImageView repliesImageView = new ImageView(repliesImage);

    private ImageView profileImageView = new ImageView(profileImage);
    private Message tweet;
    private Label usernameLabel;
    private Label typeWithId;
    private JFXTextArea textArea;
    private ImageView tweetImageView;
    private Circle circleClipProfile;
    private Rectangle tweetRectangle;
    private Label dateLabel;

    private Label typeOfAppLabel;
    private Label countOfRet;
    private Label retweetLabel;
    private Label countOfQuo;
    private Label quoteLabel;
    private Label countOfLikes;
    private Label likeLabel;
    private JFXButton quoteButton;
    private JFXButton retButton;
    private JFXButton likeButton;
    private JFXButton dotsButton;
    private Separator separator1;
    private Separator separator2;
    private Label countOfReplies;
    private Label replyLabel;
    private JFXButton replyButton;
    private JFXButton seeReplies;
    private JFXButton profile;

    public RetweetComponent(Message tweet, boolean hasPhoto, String retweeter,String retweetId,String date,Message retweet,boolean has_liked) {
        this.tweet = tweet;
        usernameLabel = new Label(tweet.getSenderUsername());
        typeWithId = new Label( retweeter+" Retweeted from id "+ tweet.getMessageId() +" by "+tweet.getSenderUsername());

        textArea = new JFXTextArea(tweet.getWritten());
        if (hasPhoto) {
            tweetImageView = new ImageView(tweet.getPhotoPath());
            tweetImageView.setFitHeight(200);
            tweetImageView.setFitWidth(280);
            tweetRectangle = new Rectangle(280, 200);
            tweetRectangle.setArcHeight(20.0);
            tweetRectangle.setArcWidth(20.0);
            tweetImageView.setClip(tweetRectangle);
        } else {
            tweetImageView = new ImageView();
        }
        dateLabel = new Label(date);
        typeOfAppLabel = new Label("Retweet id: " + retweetId);
        countOfRet = new Label(String.valueOf(tweet.getRetweetsNum()));
        countOfQuo = new Label(String.valueOf(tweet.getQuotesNum()));
        countOfLikes = new Label(String.valueOf(tweet.getLikesNum()));
        if(!hasPhoto){
            countOfReplies = new Label(String.valueOf(tweet.getRepliesNum()));
            replyLabel = new Label("replies");
            replyButton = new JFXButton();
            seeReplies = new JFXButton();
        }
        retweetLabel = new Label("Retweets");
        quoteLabel = new Label("Quote Tweets");
        likeLabel = new Label("Likes");
        quoteButton = new JFXButton();
        retButton = new JFXButton();
        likeButton = new JFXButton();
        dotsButton = new JFXButton();
        profile = new JFXButton();
        circleClipProfile = new Circle(24);
        separator1 = new Separator();
        separator2 = new Separator();
        circleClipProfile.setStroke(Color.GRAY);
        circleClipProfile.setFill(Color.SNOW);
        if(!tweet.getUser().getAvatar().equals("Null")){
            circleClipProfile.setFill(new ImagePattern(new Image(tweet.getUser().getAvatar())));
        }
        if(!hasPhoto){
            this.getChildren().addAll(usernameLabel, textArea, tweetImageView,
                    circleClipProfile, dateLabel, typeOfAppLabel, countOfRet, countOfLikes, countOfQuo,
                    retweetLabel, quoteLabel, likeLabel, retButton, quoteButton, likeButton,
                    dotsButton, separator1, separator2, replyButton, countOfReplies, replyLabel,seeReplies,typeWithId,profile);
        }
        else if (hasPhoto){
            this.getChildren().addAll(usernameLabel, textArea, tweetImageView,
                    circleClipProfile, dateLabel, typeOfAppLabel, countOfRet, countOfLikes, countOfQuo,
                    retweetLabel, quoteLabel, likeLabel, retButton, quoteButton, likeButton,typeWithId,
                    dotsButton, separator1, separator2,profile);
        }
        setConfig(hasPhoto,has_liked);
        setLocation(hasPhoto);
        setActions(hasPhoto,retweet,has_liked,retweeter);
    }


    private void setConfig(boolean hasPhoto,boolean has_liked) {
        this.setPrefSize(320, 500);
        this.setMinSize(320, 500);
        this.setMaxSize(320, 500);
        this.setStyle("-fx-background-color:#ffffff;" +
                "-fx-background-radius:10;" +
                "-fx-border-radius:10;" +
                "-fx-border-color:#3E3B3B");
        usernameLabel.setFont(Font.font("Roboto-Bold", FontWeight.BOLD, 24));
        usernameLabel.setStyle("-fx-text-fill:#242424;");

        typeWithId.setFont(Font.font("Roboto", FontWeight.BOLD,12));
        typeWithId.setStyle("-fx-text-fill:gray;");

        textArea.setStyle("-fx-background-color:#ffffff;" +
                "-fx-text-fill:#242424;");
        textArea.setMaxSize(250.0, 110.0);
        textArea.setMinSize(250.0, 110.0);
        textArea.setFont(Font.font("Roboto", FontWeight.BLACK, 14));
        textArea.setEditable(false);

        if (hasPhoto) {
            tweetImageView.setFitHeight(200);
            tweetImageView.setFitWidth(280);
            tweetRectangle = new Rectangle(280, 200);
            tweetRectangle.setArcHeight(20.0);
            tweetRectangle.setArcWidth(20.0);
            tweetImageView.setClip(tweetRectangle);
            tweetImageView.setStyle("-fx-border-color: #3E3B3B");
            tweetImageView.setEffect(new DropShadow(10, Color.BLACK));
        } else {
            tweetImageView = new ImageView();
        }
        dateLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        dateLabel.setStyle("-fx-text-fill:gray;");

        typeOfAppLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        typeOfAppLabel.setStyle("-fx-text-fill:#3366CC;");

        separator1.setPrefSize(320.0, 1.0);
        separator1.setStyle("-fx-background-color:#858080;");
        separator2.setPrefSize(320.0, 1.0);
        separator2.setStyle("-fx-background-color:#858080;");
        separator1.setOpacity(0.15);
        separator2.setOpacity(0.15);

        retImageView.setFitWidth(24);
        retImageView.setFitWidth(24);
        countOfRet.setFont(Font.font("Roboto", FontWeight.BOLD, 14));
        countOfRet.setTextFill(Paint.valueOf("#242424"));
        retweetLabel.setFont(Font.font("Roboto", FontWeight.BLACK, 12));
        retweetLabel.setTextFill(Paint.valueOf("gray"));

        countOfQuo.setFont(Font.font("Roboto", FontWeight.BOLD, 14));
        countOfQuo.setTextFill(Paint.valueOf("#242424"));
        quoteLabel.setFont(Font.font("Roboto", FontWeight.BLACK, 12));
        quoteLabel.setTextFill(Paint.valueOf("gray"));

        countOfLikes.setFont(Font.font("Roboto", FontWeight.BLACK, 12));
        countOfLikes.setTextFill(Paint.valueOf("#242424"));
        likeLabel.setFont(Font.font("Roboto", FontWeight.BLACK, 12));
        likeLabel.setTextFill(Paint.valueOf("gray"));

        quoteImageView.setFitHeight(20);
        quoteImageView.setFitWidth(20);
        retImageView.setFitHeight(20);
        retImageView.setFitWidth(20);
        heartImageView.setFitWidth(20);
        heartImageView.setFitHeight(20);

        quoteButton.setGraphic(quoteImageView);
        quoteButton.setPrefSize(14, 14);
        quoteButton.setRipplerFill(Paint.valueOf("#858080"));
        quoteButton.setStyle("-fx-background-radius: 50");

        retButton.setGraphic(retImageView);
        retButton.setPrefSize(20, 20);
        retButton.setRipplerFill(Paint.valueOf("#858080"));
        retButton.setStyle("-fx-background-radius: 50");

        if(!has_liked){
            likeButton.setGraphic(heartImageView);
        }
        else{
            likeButton.setGraphic(redHeartImageView);
        }
        likeButton.setPrefSize(20, 20);
        likeButton.setRipplerFill(Paint.valueOf("#858080"));
        likeButton.setStyle("-fx-background-radius: 50");
        redHeartImageView.setFitWidth(20);
        redHeartImageView.setFitHeight(20);

        if(!hasPhoto){
            replyImageView.setFitWidth(20);
            replyImageView.setFitHeight(20);
            countOfReplies.setFont(Font.font("Roboto", FontWeight.BLACK, 12));
            countOfReplies.setTextFill(Paint.valueOf("#242424"));
            replyLabel.setFont(Font.font("Roboto", FontWeight.BLACK, 12));
            replyLabel.setTextFill(Paint.valueOf("gray"));
            replyButton.setGraphic(replyImageView);
            replyButton.setPrefSize(20, 20);
            replyButton.setRipplerFill(Paint.valueOf("#858080"));
            replyButton.setStyle("-fx-background-radius: 50");

            repliesImageView.setFitWidth(20);
            repliesImageView.setFitHeight(20);
            seeReplies.setGraphic(repliesImageView);
            seeReplies.setPrefSize(30, 30);
            seeReplies.setRipplerFill(Paint.valueOf("#858080"));
            seeReplies.setStyle("-fx-background-radius: 50");
        }
        if (hasPhoto) {
            AnchorPane.setTopAnchor(dateLabel, 390.0);
            AnchorPane.setLeftAnchor(dateLabel, 30.0);
            AnchorPane.setTopAnchor(typeOfAppLabel, 390.0);
            AnchorPane.setLeftAnchor(typeOfAppLabel, 160.0);
            AnchorPane.setTopAnchor(tweetImageView, 170.0);
            AnchorPane.setLeftAnchor(tweetImageView, 24.0);
            AnchorPane.setTopAnchor(separator1, 400.0);
            AnchorPane.setLeftAnchor(separator1, 30.0);
            AnchorPane.setTopAnchor(countOfRet, 410.0);
            AnchorPane.setLeftAnchor(countOfRet, 40.0);
            AnchorPane.setTopAnchor(retweetLabel, 411.0);
            AnchorPane.setLeftAnchor(retweetLabel, 135.0);
            AnchorPane.setTopAnchor(separator2, 420.0);
            AnchorPane.setLeftAnchor(separator2, 30.0);
            AnchorPane.setTopAnchor(countOfQuo, 430.0);
            AnchorPane.setLeftAnchor(countOfQuo, 40.0);
            AnchorPane.setTopAnchor(quoteLabel, 431.0);
            AnchorPane.setLeftAnchor(quoteLabel, 135.0);
            AnchorPane.setTopAnchor(countOfLikes, 440.0);
            AnchorPane.setLeftAnchor(countOfLikes, 40.0);
            AnchorPane.setTopAnchor(likeLabel, 441.0);
            AnchorPane.setLeftAnchor(likeLabel, 135.0);
        } else {
            AnchorPane.setTopAnchor(dateLabel, 270.0);
            AnchorPane.setLeftAnchor(dateLabel, 30.0);
            AnchorPane.setTopAnchor(typeOfAppLabel, 270.0);
            AnchorPane.setLeftAnchor(typeOfAppLabel, 160.0);
            AnchorPane.setTopAnchor(separator1, 280.0);
            AnchorPane.setLeftAnchor(separator1, 30.0);

            AnchorPane.setTopAnchor(countOfRet, 290.0);
            AnchorPane.setLeftAnchor(countOfRet, 40.0);
            AnchorPane.setTopAnchor(retButton, 287.0);
            AnchorPane.setLeftAnchor(retButton, 100.0);
            AnchorPane.setTopAnchor(retweetLabel, 291.0);
            AnchorPane.setLeftAnchor(retweetLabel, 135.0);

            AnchorPane.setTopAnchor(separator2, 300.0);
            AnchorPane.setLeftAnchor(separator2, 30.0);

            AnchorPane.setTopAnchor(countOfQuo, 310.0);
            AnchorPane.setLeftAnchor(countOfQuo, 40.0);
            AnchorPane.setTopAnchor(quoteButton, 307.0);
            AnchorPane.setLeftAnchor(quoteButton, 100.0);
            AnchorPane.setTopAnchor(quoteLabel, 311.0);
            AnchorPane.setLeftAnchor(quoteLabel, 135.0);

            AnchorPane.setTopAnchor(countOfLikes, 320.0);
            AnchorPane.setLeftAnchor(countOfLikes, 40.0);
            AnchorPane.setTopAnchor(likeButton, 317.0);
            AnchorPane.setLeftAnchor(likeButton, 100.0);
            AnchorPane.setTopAnchor(likeLabel, 321.0);
            AnchorPane.setLeftAnchor(likeLabel, 135.0);

            AnchorPane.setTopAnchor(countOfReplies, 310.0);
            AnchorPane.setLeftAnchor(countOfReplies, 25.0);
            AnchorPane.setTopAnchor(replyButton, 287.0);
            AnchorPane.setLeftAnchor(replyButton, 85.0);
            AnchorPane.setTopAnchor(replyLabel, 311.0);
            AnchorPane.setLeftAnchor(replyLabel, 110.0);

//            AnchorPane.setTopAnchor(seeReplies, 311.0);
//            AnchorPane.setLeftAnchor(seeReplies, 130.0);

        }
        profileImageView.setFitWidth(30);
        profileImageView.setFitHeight(30);
        profile.setGraphic(profileImageView);
        profile.setPrefSize(20, 20);
        profile.setRipplerFill(Paint.valueOf("#858080"));
        profile.setStyle("-fx-background-radius: 50");
    }

    private void setLocation(boolean hasPhoto) {
        AnchorPane.setTopAnchor(typeWithId,45.0);
        AnchorPane.setLeftAnchor(typeWithId,65.0);

        AnchorPane.setTopAnchor(circleClipProfile, 10.0);
        AnchorPane.setLeftAnchor(circleClipProfile, 10.0);
        AnchorPane.setTopAnchor(usernameLabel, 12.0);
        AnchorPane.setLeftAnchor(usernameLabel, 65.0);

        AnchorPane.setTopAnchor(profile, 12.0);
        AnchorPane.setLeftAnchor(profile, 250.0);

        AnchorPane.setTopAnchor(textArea, 70.0);
        AnchorPane.setLeftAnchor(textArea, 30.0);
        AnchorPane.setTopAnchor(tweetImageView, 170.0);
        AnchorPane.setLeftAnchor(tweetImageView, 24.0);

        AnchorPane.setTopAnchor(dateLabel, 370.0);
        AnchorPane.setLeftAnchor(dateLabel, 30.0);
        AnchorPane.setTopAnchor(typeOfAppLabel, 370.0);
        AnchorPane.setLeftAnchor(typeOfAppLabel, 160.0);

        AnchorPane.setTopAnchor(separator1, 410.0);
        AnchorPane.setLeftAnchor(separator1, 0.0);

        AnchorPane.setTopAnchor(separator2, 460.0);
        AnchorPane.setLeftAnchor(separator2, 0.0);

        AnchorPane.setTopAnchor(countOfRet, 432.0);
        AnchorPane.setLeftAnchor(countOfRet, 30.0);

        AnchorPane.setTopAnchor(retweetLabel, 432.0);
        AnchorPane.setLeftAnchor(retweetLabel, 44.0);

        AnchorPane.setTopAnchor(countOfQuo, 432.0);
        AnchorPane.setLeftAnchor(countOfQuo, 130.0);

        AnchorPane.setTopAnchor(quoteLabel, 432.0);
        AnchorPane.setLeftAnchor(quoteLabel, 143.0);

        AnchorPane.setTopAnchor(countOfLikes, 432.0);
        AnchorPane.setLeftAnchor(countOfLikes, 240.0);

        AnchorPane.setTopAnchor(likeLabel, 432.0);
        AnchorPane.setLeftAnchor(likeLabel, 258.0);


        if (hasPhoto) {
            AnchorPane.setTopAnchor(dateLabel, 390.0);
            AnchorPane.setLeftAnchor(dateLabel, 30.0);
            AnchorPane.setTopAnchor(typeOfAppLabel, 390.0);
            AnchorPane.setLeftAnchor(typeOfAppLabel, 160.0);
            AnchorPane.setTopAnchor(tweetImageView, 170.0);
            AnchorPane.setLeftAnchor(tweetImageView, 24.0);

            AnchorPane.setBottomAnchor(retButton, 5.0);
            AnchorPane.setLeftAnchor(retButton, 45.0);

            AnchorPane.setBottomAnchor(quoteButton, 5.0);
            AnchorPane.setLeftAnchor(quoteButton, 140.0);

            AnchorPane.setBottomAnchor(likeButton, 5.0);
            AnchorPane.setLeftAnchor(likeButton, 230.0);
        } else {
            AnchorPane.setTopAnchor(dateLabel, 250.0);
            AnchorPane.setLeftAnchor(dateLabel, 30.0);
            AnchorPane.setTopAnchor(typeOfAppLabel, 250.0);
            AnchorPane.setLeftAnchor(typeOfAppLabel, 160.0);

            AnchorPane.setLeftAnchor(retButton, 45.0);
            AnchorPane.setBottomAnchor(retButton, 10.0);

            AnchorPane.setLeftAnchor(replyButton, 80.0);
            AnchorPane.setBottomAnchor(replyButton, 170.0);

            AnchorPane.setBottomAnchor(quoteButton, 10.0);
            AnchorPane.setLeftAnchor(quoteButton, 140.0);

            AnchorPane.setBottomAnchor(likeButton, 10.0);
            AnchorPane.setLeftAnchor(likeButton, 230.0);

            AnchorPane.setTopAnchor(countOfReplies, 300.0);
            AnchorPane.setLeftAnchor(countOfReplies, 130.0);

            AnchorPane.setTopAnchor(replyLabel, 300.0);
            AnchorPane.setLeftAnchor(replyLabel, 143.0);

            AnchorPane.setTopAnchor(seeReplies, 300.0);
            AnchorPane.setLeftAnchor(seeReplies, 180.0);
        }
    }

    private void setActions(boolean hasPhoto,Message retweet,boolean has_liked,String username) {
        quoteButton.setOnAction(event -> {
            tweet.setQuotesNum(tweet.getQuotesNum() + 1);
            countOfQuo.setText(String.valueOf(tweet.getQuotesNum()));
            Main.controller.changeWindowToQuote();
        });

        retButton.setOnAction(event -> {
            tweet.setRetweetsNum(tweet.getRetweetsNum() + 1);
            countOfRet.setText(String.valueOf(tweet.getRetweetsNum()));
            Main.controller.changeWindowToRetweet();
        });
        profile.setOnAction(event -> {
            try {
                if (tweet.getUser().getUserName().equals(username)) {
                    Scene scene = Main.controller.setProfileController();
                    Main.controller.changeWindowToProfile(scene);
                } else {
                    Scene scene = Main.controller.setControllerAnotherUser(tweet.getUser().getUserName());
                    Main.controller.changeWindowToAnotherUser(scene);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        likeButton.setOnAction(event -> {
            if(!has_liked){
                tweet.setLikesNum(tweet.getLikesNum() + 1);
                countOfLikes.setText(String.valueOf(tweet.getLikesNum()));
                heartImageView.setImage(redHeartImage);
                ClientRequest req = new ClientRequest("like", ServerOutput.token,tweet.getMessageId());
                try {
                    Controller.input.send(req);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        if(!hasPhoto){
            replyButton.setOnAction(event -> {
                tweet.setRepliesNum(tweet.getRepliesNum() + 1);
                countOfReplies.setText(String.valueOf(tweet.getRepliesNum()));
                replyImageView.setImage(replyImage);
                Main.controller.changeWindowToReply();
            });
            seeReplies.setOnAction(event -> {
                try {
                    Scene scene = Main.controller.setReplyListController(tweet.getMessageId());
                    Main.controller.changeWindowToReplyList(scene);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }
}

