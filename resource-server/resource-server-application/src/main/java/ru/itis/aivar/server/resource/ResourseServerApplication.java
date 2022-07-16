package ru.itis.aivar.server.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResourseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourseServerApplication.class, args);
    }

}
