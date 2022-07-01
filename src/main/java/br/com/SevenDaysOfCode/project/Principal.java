package br.com.SevenDaysOfCode.project;

import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.SevenDaysOfCode.project.modelo.Filmes;

public class Principal {

	public static void main(String[] args) throws Exception {

		// cria o client
		HttpClient client = HttpClient.newHttpClient();

		// cria o URI com a API
		URI uri = new URI("https://imdb-api.com/en/API/Top250Movies/k_i7oyujq7");

		// cria a request e joga a URI da API dentro
		HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

		// cria a response e faz o client executar a request, pega a resposta da request
		// e atribui a response
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		// Pega os dados do corpo do json inteiro e joga na String json
		String json = response.body();
		
		JSONHandler jh = new JSONHandler();

		// Pega o array que é retornado do método parseJson
		String[] colecao = jh.parseJson(json);

		// cria uma lista de filmes que vai receber o ArrayList criado e populado no
		// método parseInfo
		List<Filmes> filmes = jh.parseInfo(colecao);
		HTMLGenerator hGenerator = new HTMLGenerator();

		// hGenerator.HTMLColetionGenerator(filmes);
		hGenerator.generate(filmes, 2);

	}

}
