package com.example.twitter_client;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientPic {
    public static void SendPic(String pic_type, String picturePath) {
        String info = ServerOutput.token + " " + (pic_type.toLowerCase());
        try {
            Socket socket = new Socket("localhost", 8888);
            socket.getOutputStream().write(info.getBytes(StandardCharsets.UTF_8));
            sendPicture(socket, picturePath);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendPicture(Socket socket, String picturePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(picturePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        OutputStream outputStream = socket.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
    }
}