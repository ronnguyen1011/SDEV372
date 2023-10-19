package edu.greenriver.sdev.webapi;

import edu.greenriver.sdev.webapi.model.Game;
import edu.greenriver.sdev.webapi.services.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);
		GameService gameService = new GameService();
	}
}
