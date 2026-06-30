package com.amandaLinger.screenmatch3;

import com.amandaLinger.screenmatch3.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatch3Application implements CommandLineRunner {
//o implements CommandLineRunner informa que sera uma aplicacao via linha de comando (CLI)
	public static void main(String[] args) {
		SpringApplication.run(Screenmatch3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
