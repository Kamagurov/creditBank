package com.example.creditbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CreditBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditBankApplication.class, args);
    }
}
