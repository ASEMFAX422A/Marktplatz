package com.marktplatz.marktplatz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800, allowCredentials = "false", allowedHeaders = {"Access-Control-Allow-Origin", "Content-Type"})
@SpringBootApplication
public class BackendMarktplatzApplication {


    public static void main(String[] args) {
        SpringApplication.run(BackendMarktplatzApplication.class, args);


    }


}
