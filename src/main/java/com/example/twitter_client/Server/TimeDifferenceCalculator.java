package com.example.twitter_client.Server;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeDifferenceCalculator {
    public static String calculateTimeDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        long years = dateTime1.until(dateTime2, ChronoUnit.YEARS);
        if (years > 0) {
            if (years == 1) {
                return years + " year ago";
            }
            return years + " years ago";
        }

        long months = dateTime1.until(dateTime2, ChronoUnit.MONTHS);
        if (months > 0) {
            if (months == 1) {
                return months + " month ago";
            }
            return months + " months ago";
        }

        long days = dateTime1.until(dateTime2, ChronoUnit.DAYS);
        if (days > 0) {
            if (days == 1) {
                return days + " day ago";
            }
            return days + " days ago";
        }

        long hours = dateTime1.until(dateTime2, ChronoUnit.HOURS);
        if (hours > 0) {
            if (hours == 1) {
                return hours + " hour ago";
            }
            return hours + " hours ago";
        }

        long minutes = dateTime1.until(dateTime2, ChronoUnit.MINUTES);
        if (minutes > 0) {
            if (minutes == 1) {
                return minutes + " minute ago";
            }
            return minutes + " minutes ago";
        }
        long seconds = dateTime1.until(dateTime2, ChronoUnit.SECONDS);
        if (seconds == 1) {
            return seconds + " second ago";
        }
        return seconds + " seconds ago";
    }
}