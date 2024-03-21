package com.sipc.intelligentfarmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class IntelligentFarmBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentFarmBackendApplication.class, args);
    }

}
