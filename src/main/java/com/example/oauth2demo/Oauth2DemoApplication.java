package com.example.oauth2demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing 
public class Oauth2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2DemoApplication.class, args);
    }
}