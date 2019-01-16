package com.htstudio.core.util;

import java.util.Locale;

public class TimeUtil {

    public static String getDurationFromMilli(long milliseconds){
        int seconds = (int) (milliseconds / 1000) % 60 ;
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        int hours   = (int) ((milliseconds / (1000*60*60)) % 24);

        if (hours == 0) return String.format(Locale.US, "%02d:%02d",minutes, seconds);
        else return String.format(Locale.US, "%02d:%02d:%02d",hours, minutes, seconds);
    }
}
