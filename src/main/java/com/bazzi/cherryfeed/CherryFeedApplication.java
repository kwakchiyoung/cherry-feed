package com.bazzi.cherryfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CherryFeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(CherryFeedApplication.class, args);
    }

}
