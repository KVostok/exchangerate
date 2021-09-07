package com.alfabank.kosmos.exchangerate.utils;

import com.alfabank.kosmos.exchangerate.model.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class UtilTest {

    @Test
    void getExchangeURI()  {
        URI uri_test = Util.getExchangeURI("https://openexchangerates.org/api/", "6dc4b090c8d44d94a5e8bf4953810a76", "USD", LocalDateTime.now());
        URI uri = URI.create("https://openexchangerates.org/api/latest.json?app_id="+"6dc4b090c8d44d94a5e8bf4953810a76"+"&base=USD");
        Assertions.assertTrue(uri.equals(uri_test));
    }

    @Test
    void getGifURI() {
        URI uri_test = Util.getGifURI("https://api.giphy.com/", "9gLgANS5aTMNbHOFYqP3aAipTXeTGYAK", "rich");
        URI uri = URI.create("https://api.giphy.com/v1/gifs/random?api_key=9gLgANS5aTMNbHOFYqP3aAipTXeTGYAK&tag=rich");
        Assertions.assertTrue(uri.equals(uri_test));
    }

    @Test
    void compareExchangeRate()  {
        Map<String, Double> exMap = new HashMap<>();
        exMap.put("GBP", 1.77);
        exMap.put("RUB", 78.0);
        Exchange exchange = new Exchange("disclaimer", "license", "USD", exMap);
        Map<String, Double> exMap1 = new HashMap<>();
        exMap1.put("GBP", 1.77);
        exMap1.put("RUB", 77.0);
        Exchange exchange1 = new Exchange("disclaimer", "license", "USD", exMap1);
        Assertions.assertTrue(Util.compareExchangeRate(exchange, exchange1, "RUB"));
    }
}