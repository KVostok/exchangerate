package com.alfabank.kosmos.exchangerate.utils;

import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util {

    @Value("${gif.rich}") private static String rich;
    @Value("${gif.broken}") private static String broken;

    public static URI getExchangeURI(String exServer, String exchangeAppID, String curBase, LocalDateTime date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        StringBuilder builderURL = new StringBuilder();
        builderURL
                .append(exServer)
                .append("historical/")
                .append(timeFormatter.format(date))
                .append(".json?app_id=")
                .append(exchangeAppID)
                .append("&base=")
                .append(curBase.toUpperCase());
        return URI.create(builderURL.toString());
    }

    public static URI getGifURI(String gifServer, String gifApiID, String tag) {
        StringBuilder builderURL = new StringBuilder();
        builderURL
                .append(gifServer)
                .append("v1/gifs/random?api_key=")
                .append(gifApiID)
                .append("&tag=")
                .append(tag);
         return URI.create(builderURL.toString());
    }

    public static String getGifTag(boolean b){
        return b?rich:broken;
    }

}
