package com.marktplatz.marktplatz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@SpringBootApplication
public class BackendMarktplatzApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendMarktplatzApplication.class, args);


    }

}
