package com.amandaLinger.screenmatch3.principal;

import com.amandaLinger.screenmatch3.model.DadosEpisodio;
import com.amandaLinger.screenmatch3.model.DadosSerie;
import com.amandaLinger.screenmatch3.model.DadosTemporada;
import com.amandaLinger.screenmatch3.model.Episodio;
import com.amandaLinger.screenmatch3.service.ConsumoApi;
import com.amandaLinger.screenmatch3.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

        for(int i =0; i <dados.totalTemporadas(); i++){
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for(int j = 0; j < episodiosTemporada.size(); j++){
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.toString())));


        List<String> nomes = Arrays.asList("matheus","maria","aline","amanda");

        //usando streams
//        nomes.stream().sorted().map(n -> n.toUpperCase()).forEach(System.out::println);


        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 10 episodios:"); //buscando top 5
        dadosEpisodios.stream()
                .filter(e  -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primerio filtro N/A " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenacao " + e))
                .limit(10)
                .peek(e -> System.out.println("Limite " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);

        System.out.println("\n"); //imprimindo todos os episodios
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(),d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

//        System.out.println("Apartir de que ano deseja ver os episodios?");
//        var ano = scanner.nextInt();
//        scanner.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano,1,1);
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatacao data
//
//        episodios.stream()
//                .filter(e ->e.getDataLancamento() != null &&
//                        e.getDataLancamento().isAfter(dataBusca))
//                .forEach( e -> System.out.println(
//                        "Temporada : " + e.getTemporada() +
//                                " Episódio : " + e.getTitulo() +
//                                " Data lançamento : " + e.getDataLancamento().format(formatador)
//                ));


    }

}
