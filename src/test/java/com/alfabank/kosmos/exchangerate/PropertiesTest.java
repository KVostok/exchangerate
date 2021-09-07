package com.alfabank.kosmos.exchangerate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertiesTest {
    @Value("${exchange.server}") private String exServer;
    @Value("${exchange.app_id}") private String exchangeAppID;
    @Value("${exchange.base}") private String curBase;
    @Value("${gif.server}") private String gifServer;
    @Value("${gif.app_id}") private String gifApiID;
    @Value("${gif.rich}") private String rich;
    @Value("${gif.broken}") private String broken;

    @Test
    @DisplayName("All options are assigned")
    void checkOptions() {
        Assertions.assertNotNull(exServer);
        Assertions.assertNotNull(exchangeAppID);
        Assertions.assertNotNull(curBase);
        Assertions.assertNotNull(gifServer);
        Assertions.assertNotNull(gifApiID);
        Assertions.assertNotNull(rich);
        Assertions.assertNotNull(broken);
    }
}
