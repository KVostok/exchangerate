package com.alfabank.kosmos.exchangerate.services;

import com.alfabank.kosmos.exchangerate.model.Gif;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.alfabank.kosmos.exchangerate.utils.Util.getGifURI;

@SpringBootTest
class GifServiceTest {
    @Value("${gif.server}") private String gifServer;
    @Value("${gif.app_id}") private String gifApiID;
    @Value("${gif.rich}") private String rich;

    @Autowired
    GifService gifService;

    @Test
    void getGif() {
        Gif gif = gifService.getGif(getGifURI(gifServer, gifApiID, rich));
        Assertions.assertNotNull(gif);
    }
}