package com.amandaLinger.screenmatch3;

import com.amandaLinger.screenmatch3.model.DadosEpisodio;
import com.amandaLinger.screenmatch3.model.DadosSerie;
import com.amandaLinger.screenmatch3.model.DadosTemporada;
import com.amandaLinger.screenmatch3.service.ConverteDados;
import com.amandaLinger.screenmatch3.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Screenmatch3Application implements CommandLineRunner {
//o implements CommandLineRunner informa que sera uma aplicacao via linha de comando (CLI)
	public static void main(String[] args) {
		SpringApplication.run(Screenmatch3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=4ea549c4");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

		System.out.println(dados);
		json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=4ea549c4");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i =1; i <= dados.totalTemporadas(); i++){
			json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=4ea549c4");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}
}
