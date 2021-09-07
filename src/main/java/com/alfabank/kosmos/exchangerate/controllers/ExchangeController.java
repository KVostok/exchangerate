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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.alfabank.kosmos.exchangerate.utils.Util.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExchangeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

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
    public Gif getGif(){
        return getGifAsJSON("RUB");
    }

    @GetMapping("/{currency}")
    public Gif getGifAsJSON(@PathVariable String currency) {

        Exchange exchangeToday = exchangeService.getExchange(getExchangeURI(exServer, exchangeAppID, curBase, LocalDateTime.now()));
        log.info("get exchangeToday: base currency {} ; rate {} for {} on time {}", exchangeToday.getBase(), exchangeToday.getRates().get(currency.toUpperCase()), currency, LocalDate.now());

        Exchange exchangeYesterday = exchangeService.getExchange(getExchangeURI(exServer,exchangeAppID,curBase,LocalDateTime.now().minusDays(1)));
        log.info("get exchangeYesterday: base currency {} ; rate {} for {} on time {}", exchangeYesterday.getBase(), exchangeYesterday.getRates().get(currency.toUpperCase()), currency, LocalDate.now().minusDays(1));

        String tag = getGifTag(compareExchangeRate(exchangeToday,exchangeYesterday,currency));
        Gif gif = gifService.getGif(getGifURI(gifServer, gifApiID, tag));
        log.info("gifEmbed url {} for {} Tag", gif.getData().getEmbed_url(), tag);
        
        return gif;
    }

    public String getGifTag(boolean b){
        return b?rich:broken;
    }
}
