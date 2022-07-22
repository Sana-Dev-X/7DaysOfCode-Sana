package br.com.SevenDaysOfCode.project.app;

import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.SevenDaysOfCode.project.modelo.Filmes;
/*
 * Essa classe usa a HttpClient para enviar a request contendo a URI da API 
 * Pega os dados que retornam e coloca dentro da variavel response do tipo Http
 * 
 * Pega os dados do corpo da response e passa para uma String
 * 
 * Instancia um objeto do tipo JSONHandler(classe que foi criada com o proposito de parsear os dados do JSON capturado pela API)
 * 
 * 
 */
public class Principal {

	public static void main(String[] args) throws Exception {

		HttpClient client = HttpClient.newHttpClient();
		URI uri = new URI("https://imdb-api.com/en/API/Top250Movies/(chave)");
		HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String json = response.body();
		
		JSONHandler jh = new JSONHandler();
		String[] colecao = jh.parseJson(json);
		List<Filmes> filmes = jh.parseInfo(colecao);
		HTMLGenerator hGenerator = new HTMLGenerator();

			//utilizei o comparador do Collections
		//Collections.sort(filmes, Comparator.comparing(Filmes::getYear));
		Collections.sort(filmes, Comparator.comparing(Filmes::getFullTitle));
		
		hGenerator.generate(filmes);
		
		System.out.println(filmes);

	}

}
