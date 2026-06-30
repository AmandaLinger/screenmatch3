package com.amandaLinger.screenmatch3;

import com.amandaLinger.screenmatch3.service.ConsumoApi;
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
		var conusmoApi = new ConsumoApi();
		var json = conusmoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=4ea549c4");
		System.out.println(json);

	}
}
