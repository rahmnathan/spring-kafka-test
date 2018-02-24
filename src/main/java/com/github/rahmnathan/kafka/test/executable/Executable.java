package com.github.rahmnathan.kafka.test.executable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.github.rahmnathan.kafka.test")
@SpringBootApplication
public class Executable {

    public static void main(String[] args) {
        SpringApplication.run(Executable.class, args);
    }
}
