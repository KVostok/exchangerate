package com.alfabank.kosmos.exchangerate.controllers;

import com.alfabank.kosmos.exchangerate.model.Exchange;
import com.alfabank.kosmos.exchangerate.model.Gif;
import com.alfabank.kosmos.exchangerate.services.ExchangeService;
import com.alfabank.kosmos.exchangerate.services.GifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE) //value = ExchangeController.REST_URL,
public class ExchangeController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/api";

    @Autowired
    private final ExchangeService exchangeService;
    @Autowired
    private final GifService gifService;


    public ExchangeController(ExchangeService service, GifService gifService) {
        this.exchangeService = service;
        this.gifService = gifService;
    }

    @GetMapping
    public String main(){
        return "Привет!";
    }

    @GetMapping("/{symbol}")
    public ModelAndView getGif(@PathVariable String symbol) {

        String cur = "USD";
        String exID = "6dc4b090c8d44d94a5e8bf4953810a76";
        String exServer = "https://openexchangerates.org/api/";
        String exCurrent = exServer + "latest.json?app_id=" + exID;

        URI uri = URI.create(exCurrent);

        Exchange exchangeToday = exchangeService.getExchange(uri);
        log.info("get exchangeToday {}", exchangeToday);

        double curRub = exchangeToday.getRates().get("RUB");
        double curEx = exchangeToday.getRates().get(cur);
        double curToRub = curRub / curEx;

        LocalDateTime date = LocalDateTime.now().minusDays(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String yesterday = format.format(date);


        String exYesterday = exServer + "historical/" + yesterday + ".json?app_id=" + exID;
        uri = URI.create(exYesterday);

        Exchange yesterdayExchange = exchangeService.getExchange(uri);
        log.info("get yesterdayExchange {}", yesterdayExchange);

        curRub = yesterdayExchange.getRates().get("RUB");
        curEx = yesterdayExchange.getRates().get(cur);
        Double yesToRub = curRub / curEx;

        String gifApiKey = "9gLgANS5aTMNbHOFYqP3aAipTXeTGYAK";
        String gifServer = "https://api.giphy.com/";
        String gifTag;

        if (curToRub < yesToRub) {
            gifTag = "broken";
        }
        else {
            gifTag = "rich";
        }

        String gifRequest = gifServer + "v1/gifs/random?api_key=" + gifApiKey + "&tag=" + gifTag;
        uri = URI.create(gifRequest);
        log.info("gifuri {}", uri);

        Gif gif = gifService.getGif(uri);
        log.info("get gif {}", gif);
        return new ModelAndView("redirect:" + gif.getData().getEmbed_url());
    }

}
