module com.example.twitter_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires java.sql;
    requires java.desktop;
    requires com.auth0.jwt;
    requires com.jfoenix;


    opens com.example.twitter_client to javafx.fxml;
    exports com.example.twitter_client;
}