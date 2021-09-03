package com.alfabank.kosmos.exchangerate.controllers;

import com.alfabank.kosmos.exchangerate.model.Exchange;
import com.alfabank.kosmos.exchangerate.model.Gif;
import com.alfabank.kosmos.exchangerate.services.ExchangeService;
import com.alfabank.kosmos.exchangerate.services.GifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    static final String REST_URL = "/";

    @Value("${exchange.server}") private String exServer;
    @Value("${exchange.app_id}") private String exchangeAppID;
    @Value("${exchange.base}") private String curBase;
    @Value("${gif.server}") private String gifServer;
    @Value("${gif.app_id}") private String gifApiID;
    @Value("${gif.rich}") private String rich;
    @Value("${gif.broken}") private String broken;

    @Autowired
    private final ExchangeService exchangeService;
    @Autowired
    private final GifService gifService;


    public ExchangeController(ExchangeService exchangeService, GifService gifService) {
        this.exchangeService = exchangeService;
        this.gifService = gifService;
    }

    @GetMapping
    public ModelAndView getGif(){
        return new ModelAndView("redirect:" + getGifAsJSON("RUB").getData().getEmbed_url());
    }

    @GetMapping("/{currency}")
    public Gif getGifAsJSON(@PathVariable String currency) {

        String exchangeURL = exServer + "latest.json?app_id=" + exchangeAppID + "&base=" + curBase.toUpperCase();
        URI uri = URI.create(exchangeURL);
        log.info("exuri for today {}", uri);
        Exchange exchangeToday = exchangeService.getExchange(uri);
        log.info("get exchangeToday: base currency {} ; rate {} for {}", exchangeToday.getBase(), exchangeToday.getRates().get(currency.toUpperCase()), currency);

        LocalDateTime date = LocalDateTime.now().minusDays(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String yesterday = format.format(date);
        String exYesterday = exServer + "historical/" + yesterday + ".json?app_id=" + exchangeAppID + "&base=" + curBase.toUpperCase();
        uri = URI.create(exYesterday);
        log.info("exuri for yesterday {}", uri);
        Exchange exchangeYesterday = exchangeService.getExchange(uri);
        log.info("get exchangeYesterday: base currency {} ; rate {} for {}", exchangeYesterday.getBase(), exchangeYesterday.getRates().get(currency.toUpperCase()), currency);

        String gifTag;
        if (exchangeToday.getRates().get(currency.toUpperCase()) > exchangeYesterday.getRates().get(currency.toUpperCase())) {
            gifTag = rich;
        }
        else {
            gifTag = broken;
        }

        String gifRequest = gifServer + "v1/gifs/random?api_key=" + gifApiID + "&tag=" + gifTag;
        uri = URI.create(gifRequest);
        log.info("gifuri {}", uri);
        Gif gif = gifService.getGif(uri);
        log.info("gifEmbed url {}", gif.getData().getEmbed_url());
        return gif;
    }

}
