package br.com.sicredi.memberwrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MemberswrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberswrapperApplication.class, args);
	}

}
