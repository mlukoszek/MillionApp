package com.wsb.millionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.wsb.millionapp"})
public class MillionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MillionAppApplication.class, args);
    }

}
