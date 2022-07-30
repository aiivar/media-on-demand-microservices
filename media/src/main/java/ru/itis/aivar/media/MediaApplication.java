package ru.itis.aivar.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.itis.aivar.media.config.RabbitMQConfig;

@SpringBootApplication
@Import({RabbitMQConfig.class})
public class MediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaApplication.class, args);
	}

}
