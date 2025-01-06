package org.example.dma6m6beadando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class Dma6M6BeadandoApplication {

    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Budapest"));
        SpringApplication.run(Dma6M6BeadandoApplication.class, args);
    }

}
