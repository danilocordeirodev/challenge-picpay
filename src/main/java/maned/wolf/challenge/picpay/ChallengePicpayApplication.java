package maned.wolf.challenge.picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengePicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengePicpayApplication.class, args);
	}

}
