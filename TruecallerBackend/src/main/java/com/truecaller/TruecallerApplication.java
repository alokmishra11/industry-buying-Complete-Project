package com.truecaller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TruecallerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TruecallerApplication.class, args);
    }

}
