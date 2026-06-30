package com.amandaLinger.screenmatch3.principal;

import com.amandaLinger.screenmatch3.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=4ea549c4" ;

    private ConsumoApi consumo= new ConsumoApi();

    public void exibeMenu(){
        System.out.println("Digite o nome da serie para buscar: ");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados( ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
    }

}
