package br.com.SevenDaysOfCode.project;

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
		URI uri = new URI("https://imdb-api.com/en/API/Top250Movies/key");

		// cria a request e joga a URI da API dentro
		HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

		// cria a response e faz o client executar a request, pega a resposta da request
		// e atribui a response
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		// Pega os dados do corpo do json inteiro e joga na String json
		String json = response.body();
		
		String[] colecao = parseJson(json);
		
//		int pos = 0;
//		for (String string : colecao) {
//			
//			System.out.println(colecao[pos]);
//			pos++;
//		}
	
		//cria uma lista de filmes que vai receber o ArrayList criado e populado no método parseInfo
		List<Filmes> filmes = parseInfo(colecao);
		
		
		System.out.println(filmes.get(12));
		
	}

	
	private static List<Filmes> parseInfo(String[] colecao) {
		int pos = 0;
		
		/*
		 * Esse método faz um laço que itera por cada item do array fazendo um split e colocando em uma variavel de um objeto tipo Filmes novo
		 * 
		 * Dentro da classe Filmes tem um construtor que recebe os parametros e coloca em suas devidas variaveis
		 * 
		 * ainda é provisório... 
		 */
		
		//cria um array de filmes para inserir os filmes e suas informações
		List<Filmes> listaFilmes = new ArrayList<>();
		
		//cria um laço para atribuir as informações para cada filme 
		for (String string : colecao) {

		//cria um array que vai receber as informações separadas de cada informações sobre os filmes de cada vez o laço rodar	
		String[] infos;
		//divide as informações do primeiro filme pelos caracteres ","
		infos = colecao[pos].split("\",\"");
		
		
		//divide as informações que já foram separadas e remove ainda os caracteres ":"
		//sendo assim, já não tem os demarcadores de inicio e fim nas Strings, não vou precisar usar o substring nem fazer laços pra ficar removendo
		//fica mais código, porém acho que é mais fácil de ler
		
		String[] id = infos[0].split("\":\""); 
		String[] rank = infos[1].split("\":\""); 
		String[] title = infos[2].split("\":\""); 
		String[] fullTitle = infos[3].split("\":\""); 
		String[] year = infos[4].split("\":\""); 
		String[] image = infos[5].split("\":\""); 
		String[] crew = infos[6].split("\":\""); 
		String[] imdbRating = infos[7].split("\":\""); 
		String[] imdbRatingCount = infos[8].split("\":\""); 
		
		//passa todas as informações que foram parseadas para a coleção de filmes, o construtor chama os sets na ordem 
		listaFilmes.add(new Filmes(id[1], rank[1], title[1], fullTitle[1], year[1], image[1], crew[1], imdbRating[1], imdbRatingCount[1]));
		pos++;
		}
		
		
		//retorna a lista de filmes já populada
		return listaFilmes;
	}

	
	private static String[] parseJson(String json) {
		
		//Essa parte do código foi refatorada e deixado o mais parecido possível com a lógica do professor Nico. 
		//A parte de cima eu quis deixar com a minha lógica, por mais que esteja um código sujo, porque preciso ver o quanto estou avançando sozinho.
		
		//cria um matcher que recebe um padrão de conteudos retirados da String json com as especificações de que deve estar entre os colchetes.
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
		
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Não encontrado em " + json);
		}
		
		//pega o conteudo da grupo do que está no matcher e divide toda vez que tiver os caracteres },{ em sequencia e joga pra um array chamado colecao
		String[] colecao = matcher.group(1).split("\\},\\{");
		
		//pega o que está na primeira posição do array e descarta o primeiro caracter do conteudo
		colecao[0] = colecao[0].substring(1);
		
		
		int ultimaPosicao = colecao.length-1;
		
		//demarca a ultima String do array colecao
		String ultimaString = colecao[ultimaPosicao];
		
		//a ultima posição do array sempre vai receber a ultima String com a contagem certa de letras
		colecao[ultimaPosicao] = ultimaString.substring(0, ultimaString.length() - 1);
		return colecao;
	}

}
