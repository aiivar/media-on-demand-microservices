package com.example.authorizationserver;

import com.example.authorizationserver.config.OAuth2AuthorizationServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({OAuth2AuthorizationServerConfig.class})
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

}
