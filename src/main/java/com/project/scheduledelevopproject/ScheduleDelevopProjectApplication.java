package com.project.scheduledelevopproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleDelevopProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDelevopProjectApplication.class, args);
    }

}
