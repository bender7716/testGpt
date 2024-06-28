package com.example.testgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TestGptApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestGptApplication.class, args);
    }

}
