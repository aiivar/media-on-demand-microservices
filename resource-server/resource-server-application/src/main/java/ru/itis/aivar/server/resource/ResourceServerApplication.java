package ru.itis.aivar.server.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import ru.itis.aivar.streaming.video.config.VideoMessagingRabbitMQConfig;

@SpringBootApplication
@EnableEurekaClient
@Import({VideoMessagingRabbitMQConfig.class})
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}
