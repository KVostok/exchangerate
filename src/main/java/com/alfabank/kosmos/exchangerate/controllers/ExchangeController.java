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

import java.time.LocalDateTime;

import static com.alfabank.kosmos.exchangerate.utils.Util.*;

@RestController
@RequestMapping(produces = MediaType.IMAGE_GIF_VALUE)
public class ExchangeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${exchange.server}") private String exServer;
    @Value("${exchange.app_id}") private String exchangeAppID;
    @Value("${exchange.base}") private String curBase;
    @Value("${gif.server}") private String gifServer;
    @Value("${gif.app_id}") private String gifApiID;

    @Autowired
    private final ExchangeService exchangeService;
    @Autowired
    private final GifService gifService;


    public ExchangeController(ExchangeService exchangeService, GifService gifService) {
        this.exchangeService = exchangeService;
        this.gifService = gifService;
    }

//    @GetMapping
//    public String getGif(){
//        return getGifUrl("RUB");
//    }

    @ResponseBody
    @GetMapping("/{currency}")
    public Gif getGifUrl(@PathVariable String currency) {

        Exchange exchangeToday = exchangeService.getExchange(getExchangeURI(exServer, exchangeAppID, curBase, LocalDateTime.now().minusDays(1)));
        log.info("get exchangeToday: base currency {} ; rate {} for {}", exchangeToday.getBase(), exchangeToday.getRates().get(currency.toUpperCase()), currency);

        Exchange exchangeYesterday = exchangeService.getExchange(getExchangeURI(exServer,exchangeAppID,curBase,LocalDateTime.now().minusDays(2)));
        log.info("get exchangeYesterday: base currency {} ; rate {} for {}", exchangeYesterday.getBase(), exchangeYesterday.getRates().get(currency.toUpperCase()), currency);

        Gif gif = gifService.getGif(getGifURI(gifServer, gifApiID, getGifTag(exchangeToday.compareExchangeRate(exchangeYesterday,currency))));
        log.info("gifEmbed url {}", gif.getData().getEmbed_url());
        
        return gif;//.getData().getEmbed_url();
    }

}
