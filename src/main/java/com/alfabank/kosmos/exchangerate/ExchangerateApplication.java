package com.alfabank.kosmos.exchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@SpringBootApplication
@EnableFeignClients
public class ExchangerateApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ExchangerateApplication.class, args);
    }
}
