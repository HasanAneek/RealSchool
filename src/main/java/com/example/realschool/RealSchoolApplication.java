package com.example.realschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.realschool.repository")
@EntityScan("com.example.realschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class RealSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealSchoolApplication.class, args);
    }

}
