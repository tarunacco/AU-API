package com.accolite.au;

import com.accolite.au.utils.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class AuApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuApplication.class, args);
    }
}