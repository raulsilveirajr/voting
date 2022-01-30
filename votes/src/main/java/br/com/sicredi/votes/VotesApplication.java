package br.com.sicredi.votes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class VotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotesApplication.class, args);
	}

}
