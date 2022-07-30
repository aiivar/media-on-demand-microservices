package ru.itis.aivar.streaming.video.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.itis.aivar.streaming.video.repositories")
@ComponentScan(basePackages = "ru.itis.aivar.streaming.video")
@EntityScan(basePackages = "ru.itis.aivar.streaming.video.models")
@Import({VideoMessagingRabbitMQConfig.class})
public class VideoStreamingConfig {
}
