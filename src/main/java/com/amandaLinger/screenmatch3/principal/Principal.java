package com.amandaLinger.screenmatch3.principal;

import com.amandaLinger.screenmatch3.model.DadosEpisodio;
import com.amandaLinger.screenmatch3.model.DadosSerie;
import com.amandaLinger.screenmatch3.model.DadosTemporada;
import com.amandaLinger.screenmatch3.service.ConsumoApi;
import com.amandaLinger.screenmatch3.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=4ea549c4" ;

    private ConverteDados conversor = new ConverteDados();

    private ConsumoApi consumo= new ConsumoApi();

    public void exibeMenu(){
        System.out.println("Digite o nome da serie para buscar: ");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados( ENDERECO + nomeSerie.replace(" ","+") + API_KEY);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i =1; i <= dados.totalTemporadas(); i++){
			json = consumo.obterDados( ENDERECO + nomeSerie.replace(" ","+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

//        for(int i =0; i <dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.toString())));
    }

}
