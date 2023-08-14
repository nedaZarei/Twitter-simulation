package com.example.twitter_client.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerPic implements Runnable {
    private static void savePicture(Socket socket, String info) throws IOException {
        String[] parts = info.split(" "); //parts[0] -> jwt // parts[1] ->type(avatar/header/photoTweet/videoTweet)
        long seconds = System.currentTimeMillis();
        InputStream inputStream = socket.getInputStream();
        FileOutputStream fileOutputStream = null;
        if (parts[1].equalsIgnoreCase("avatar") || parts[1].equalsIgnoreCase("header")) {
            fileOutputStream = new FileOutputStream("C:\\Users\\\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\" + parts[1].toLowerCase() + "s\\" + JWToken.VerifyJWT(parts[0]) + ".jpg");
        } else if (parts[1].equalsIgnoreCase("photoTweet")) {
            fileOutputStream = new FileOutputStream("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\" + parts[1].toLowerCase() + "s\\" + JWToken.VerifyJWT(parts[0]) + "@" + seconds + ".jpg");
        } else if (parts[1].equalsIgnoreCase("videoTweet")) {
            fileOutputStream = new FileOutputStream("C:\\Users\\Asus\\IdeaProjects\\twitter_client\\src\\main\\java\\com\\example\\twitter_client\\" + parts[1].toLowerCase() + "s\\" + JWToken.VerifyJWT(parts[0]) + "@" + seconds + ".mp4");
        }
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }
        fileOutputStream.close();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                byte[] userNamePic = new byte[4096];
                int bytesRead = socket.getInputStream().read(userNamePic);
                String info = new String(userNamePic, 0, bytesRead);
                savePicture(socket, info);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
