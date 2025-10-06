package com.tga.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TgaEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgaEvaluationApplication.class, args);
    }

}