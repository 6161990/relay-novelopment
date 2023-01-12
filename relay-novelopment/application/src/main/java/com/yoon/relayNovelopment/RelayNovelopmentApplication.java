package com.yoon.relayNovelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.yoon.relayNovelopment")
public class RelayNovelopmentApplication {

    @RestController
    public static class HealthCheckController {
        @GetMapping("/annyeng")
        public static String hello() {
            return "hello";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(RelayNovelopmentApplication.class, args);
    }
}
