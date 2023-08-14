package com.example.twitter_client;

import java.util.regex.*;

public class Regex {

    private static final String password = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final String email = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    private static final Pattern passwordPattern = Pattern.compile(password);
    private static final Pattern emailPattern = Pattern.compile(email);


    public static boolean isPassValid(final String string) {
        Matcher matcher = passwordPattern.matcher(string);
        return matcher.matches();
    }

    public static boolean isEmailValid(final String string) {
        Matcher matcher = emailPattern.matcher(string);
        return matcher.matches();
    }
}
