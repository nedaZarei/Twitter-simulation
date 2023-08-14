package com.example.twitter_client;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.example.twitter_client.Server.User;

import java.io.IOException;

public class ProfileComponent extends AnchorPane {
    private Label usernameLabel;
    private JFXButton visitProfileButton;
    private User user;
    private Circle circleClipProfile;

    public ProfileComponent(User user, String type) {
        this.user = user;
        usernameLabel = new Label(user.getUserName());
        if (type.equals("profile")) {
            visitProfileButton = new JFXButton("visit profile");
        } else if (type.equals("direct")) {
            visitProfileButton = new JFXButton("chat");
        }
        circleClipProfile = new Circle(15);
        circleClipProfile.setStroke(Color.GRAY);
        circleClipProfile.setFill(Color.SNOW);
        if (!user.getAvatar().equals("Null")) {
            circleClipProfile.setFill(new ImagePattern(new Image(user.getAvatar())));
        }
        this.getChildren().addAll(usernameLabel, visitProfileButton, circleClipProfile);
        setConfig();
        setLocation();
        setAction(type);
    }

    private void setConfig() {
        this.setPrefSize(320, 50);
        this.setMinSize(320, 50);
        this.setMaxSize(320, 50);
        this.setStyle("-fx-background-color:#ffffff;" +
                "-fx-background-radius:10;" +
                "-fx-border-radius:10;" +
                "-fx-border-color:#ffffff");
        usernameLabel.setFont(Font.font("Roboto-Bold", FontWeight.BOLD, 18));
        usernameLabel.setStyle("-fx-text-fill:#242424;");
        visitProfileButton.setFont(Font.font("Roboto-Bold", FontWeight.BOLD, 11));
        visitProfileButton.setTextFill(Paint.valueOf("#ffffff"));
        visitProfileButton.setStyle("-fx-background-color:#3698d9;" + "-fx-background-radius:90;");
    }

    private void setLocation() {
        AnchorPane.setTopAnchor(usernameLabel, 10.0);
        AnchorPane.setLeftAnchor(usernameLabel, 50.0);

        AnchorPane.setTopAnchor(visitProfileButton, 10.0);
        AnchorPane.setLeftAnchor(visitProfileButton, 220.0);

        AnchorPane.setTopAnchor(circleClipProfile, 10.0);
        AnchorPane.setLeftAnchor(circleClipProfile, 10.0);
    }

    private void setAction(String type) {
        if (type.equals("profile")) {
            visitProfileButton.setOnAction(event -> {
                try {
                    Scene scene = Main.controller.setControllerAnotherUser(user.getUserName());
                    Main.controller.changeWindowToAnotherUser(scene);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } else if (type.equals("direct")) {
            visitProfileButton.setOnAction(event -> {
                try {
                    Scene scene = Main.controller.setControllerChat(user.getUserName());
                    Main.controller.changeWindowToChat(scene);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }
}
