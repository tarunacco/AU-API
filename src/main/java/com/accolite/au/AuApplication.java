package com.accolite.au;

import com.accolite.au.utils.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class AuApplication {

    private static ApplicationProperties applicationProperties;

    public static void main(String[] args) {
        String serverUrl = applicationProperties.getPropertyData("server.port");

        System.out.println("Starting Order Management FullStack Application on port : " + serverUrl);
        SpringApplication.run(AuApplication.class, args);
        System.out.println("Started Order Management FullStack Application on port : " + serverUrl);
    }
}