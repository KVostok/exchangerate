package com.alfabank.kosmos.exchangerate.services;

import com.alfabank.kosmos.exchangerate.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "Gif", url = "https://api.giphy.com/")
public interface GifService {
    @GetMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    Gif getGif(URI baseUrl);
}
