package com.example.bibliothek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication//(scanBasePackages = "org.springframework.security.config.web.server")
public class BibliothekApplication {

    public static void main(String[] args) {SpringApplication.run(BibliothekApplication.class, args);}

}
