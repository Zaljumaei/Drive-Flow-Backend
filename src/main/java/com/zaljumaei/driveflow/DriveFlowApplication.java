package com.zaljumaei.driveflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DriveFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveFlowApplication.class, args);
    }

}
