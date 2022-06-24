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
	
		//cria uma lista de filmes que vai receber o ArrayList criado e populado no m�todo parseInfo
		List<Filmes> filmes = parseInfo(colecao);
		
		
		System.out.println(filmes.get(12));
		
	}

	
	private static List<Filmes> parseInfo(String[] colecao) {
		int pos = 0;
		
		/*
		 * Esse m�todo faz um la�o que itera por cada item do array fazendo um split e colocando em uma variavel de um objeto tipo Filmes novo
		 * 
		 * Dentro da classe Filmes tem um construtor que recebe os parametros e coloca em suas devidas variaveis
		 * 
		 * ainda � provis�rio... 
		 */
		
		//cria um array de filmes para inserir os filmes e suas informa��es
		List<Filmes> listaFilmes = new ArrayList<>();
		
		//cria um la�o para atribuir as informa��es para cada filme 
		for (String string : colecao) {

		//cria um array que vai receber as informa��es separadas de cada informa��es sobre os filmes de cada vez o la�o rodar	
		String[] infos;
		//divide as informa��es do primeiro filme pelos caracteres ","
		infos = colecao[pos].split("\",\"");
		
		
		//divide as informa��es que j� foram separadas e remove ainda os caracteres ":"
		//sendo assim, j� n�o tem os demarcadores de inicio e fim nas Strings, n�o vou precisar usar o substring nem fazer la�os pra ficar removendo
		//fica mais c�digo, por�m acho que � mais f�cil de ler
		
		String[] id = infos[0].split("\":\""); 
		String[] rank = infos[1].split("\":\""); 
		String[] title = infos[2].split("\":\""); 
		String[] fullTitle = infos[3].split("\":\""); 
		String[] year = infos[4].split("\":\""); 
		String[] image = infos[5].split("\":\""); 
		String[] crew = infos[6].split("\":\""); 
		String[] imdbRating = infos[7].split("\":\""); 
		String[] imdbRatingCount = infos[8].split("\":\""); 
		
		//passa todas as informa��es que foram parseadas para a cole��o de filmes, o construtor chama os sets na ordem 
		listaFilmes.add(new Filmes(id[1], rank[1], title[1], fullTitle[1], year[1], image[1], crew[1], imdbRating[1], imdbRatingCount[1]));
		pos++;
		}
		
		
		//retorna a lista de filmes j� populada
		return listaFilmes;
	}

	
	private static String[] parseJson(String json) {
		
		//Essa parte do c�digo foi refatorada e deixado o mais parecido poss�vel com a l�gica do professor Nico. 
		//A parte de cima eu quis deixar com a minha l�gica, por mais que esteja um c�digo sujo, porque preciso ver o quanto estou avan�ando sozinho.
		
		//cria um matcher que recebe um padr�o de conteudos retirados da String json com as especifica��es de que deve estar entre os colchetes.
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
		
		if (!matcher.matches()) {
			throw new IllegalArgumentException("N�o encontrado em " + json);
		}
		
		//pega o conteudo da grupo do que est� no matcher e divide toda vez que tiver os caracteres },{ em sequencia e joga pra um array chamado colecao
		String[] colecao = matcher.group(1).split("\\},\\{");
		
		//pega o que est� na primeira posi��o do array e descarta o primeiro caracter do conteudo
		colecao[0] = colecao[0].substring(1);
		
		
		int ultimaPosicao = colecao.length-1;
		
		//demarca a ultima String do array colecao
		String ultimaString = colecao[ultimaPosicao];
		
		//a ultima posi��o do array sempre vai receber a ultima String com a contagem certa de letras
		colecao[ultimaPosicao] = ultimaString.substring(0, ultimaString.length() - 1);
		return colecao;
	}

}
