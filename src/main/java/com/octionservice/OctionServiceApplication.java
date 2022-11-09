package com.octionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com")
public class OctionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OctionServiceApplication.class, args);
    }

}
