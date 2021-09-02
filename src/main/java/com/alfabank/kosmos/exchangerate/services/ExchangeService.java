package com.alfabank.kosmos.exchangerate.services;

import com.alfabank.kosmos.exchangerate.model.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "ExchCourse", url = "https://openexchangerates.org/api/")
public interface ExchangeService {

    @GetMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    Exchange getExchange(URI baseUrl);
}
