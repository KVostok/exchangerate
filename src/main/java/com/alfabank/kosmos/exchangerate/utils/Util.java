package com.alfabank.kosmos.exchangerate.utils;

import com.alfabank.kosmos.exchangerate.model.Exchange;
import org.springframework.util.Assert;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util {

    public static URI getExchangeURI(String exServer, String exchangeAppID, String curBase, LocalDateTime date) {
        Assert.notNull(exServer,"field Server is null");
        Assert.notNull(exchangeAppID,"field exchangeAppID is null");
        Assert.notNull(curBase,"field curBase is null");
        Assert.notNull(date,"field date is null");
        StringBuilder builderURL = new StringBuilder();

        if (date.toLocalDate().isEqual(LocalDate.now())) {
            builderURL
                    .append(exServer)
                    .append("latest.json?app_id=")
                    .append(exchangeAppID)
                    .append("&base=")
                    .append(curBase.toUpperCase());
            return URI.create(builderURL.toString());
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
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
        Assert.notNull(gifServer,"field gifServer is null");
        Assert.notNull(gifApiID,"field gifApiID is null");
        Assert.notNull(tag,"field tag is null");
        StringBuilder builderURL = new StringBuilder();
        builderURL
                .append(gifServer)
                .append("v1/gifs/random?api_key=")
                .append(gifApiID)
                .append("&tag=")
                .append(tag);
         return URI.create(builderURL.toString());
    }

    public static boolean compareExchangeRate(Exchange exch1, Exchange exch2, String currency) {
        return exch1.getRates().get(currency.toUpperCase()) > exch2.getRates().get(currency.toUpperCase());
    }
}
