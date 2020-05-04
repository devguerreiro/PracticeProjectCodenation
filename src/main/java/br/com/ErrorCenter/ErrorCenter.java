package br.com.ErrorCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ErrorCenter {

	public static void main(String[] args) {
		SpringApplication.run(ErrorCenter.class, args);
	}

}
