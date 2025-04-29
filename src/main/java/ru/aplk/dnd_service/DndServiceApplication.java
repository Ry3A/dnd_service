package ru.aplk.dnd_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DndServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DndServiceApplication.class, args);
    }

}
