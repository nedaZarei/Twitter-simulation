package com.example.twitter_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {
    public static Stage primaryStage;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage=primaryStage;
        try {
            Socket client = new Socket("localhost", 7661);
            Controller.input = new ServerInput(client);
            Thread output = new Thread(new ServerOutput(client));
            output.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Starter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 560);
        controller = fxmlLoader.getController();
        primaryStage.setTitle("Twttr");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}