package com.alfabank.kosmos.exchangerate;

import com.alfabank.kosmos.exchangerate.controllers.ExchangeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ExchangerateApplicationTests {

    @Autowired
    private ExchangeController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

}
