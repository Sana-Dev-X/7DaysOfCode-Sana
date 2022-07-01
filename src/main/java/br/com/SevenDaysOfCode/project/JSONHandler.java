package br.com.SevenDaysOfCode.project;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.SevenDaysOfCode.project.modelo.Filmes;

public class JSONHandler {

	 static List<Filmes> parseInfo(String[] colecao) {
		int pos = 0;

		/*
		 * Esse método faz um laço que itera por cada item do array fazendo um split e
		 * colocando em uma variavel de um objeto tipo Filmes novo
		 * 
		 * Dentro da classe Filmes tem um construtor que recebe os parametros e coloca
		 * em suas devidas variaveis
		 * 
		 * ainda é provisório...
		 */

		// cria um array de filmes para inserir os filmes e suas informações
		List<Filmes> listaFilmes = new ArrayList<>();

		// cria um laço para atribuir as informações para cada filme
		for (String string : colecao) {

			// cria um array que vai receber as informações separadas de cada informações
			// sobre os filmes de cada vez o laço rodar
			String[] infos;
			// divide as informações do primeiro filme pelos caracteres ","
			infos = colecao[pos].split("\",\"");

			// divide as informações que já foram separadas e remove ainda os caracteres ":"
			// sendo assim, já não tem os demarcadores de inicio e fim nas Strings, não vou
			// precisar usar o substring nem fazer laços pra ficar removendo
			// fica mais código, porém acho que é mais fácil de ler

			String[] id = infos[0].split("\":\"");
			String[] rank = infos[1].split("\":\"");
			String[] title = infos[2].split("\":\"");
			String[] fullTitle = infos[3].split("\":\"");
			String[] year = infos[4].split("\":\"");
			String[] image = infos[5].split("\":\"");
			String[] crew = infos[6].split("\":\"");
			String[] imdbRating = infos[7].split("\":\"");
			String[] imdbRatingCount = infos[8].split("\":\"");

			// passa todas as informações que foram parseadas para a coleção de filmes, o
			// construtor chama os sets na ordem
			listaFilmes.add(new Filmes(id[1], rank[1], title[1], fullTitle[1], year[1], image[1], crew[1],
					imdbRating[1], imdbRatingCount[1].substring(0, imdbRatingCount[1].length() - 1)));
			pos++;
		}

		// retorna a lista de filmes já populada
		return listaFilmes;
	}
	
	static String[] parseJson(String json) {

		 //Pega os dados do json e trata dele deixando as informações dos filmes separadas, cada um com seu filme
		 
			Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
			if (!matcher.matches()) {
				throw new IllegalArgumentException("Não encontrado em " + json);
			}

			//Pega o que foi filtrado e joga em uma String
			String mColecao = matcher.group(1);
			//Separa as informações segundo o padrão de caracteres e colca num array
			String[] colecao = mColecao.split("\\},\\{");

			//Descarta o primeiro caracter da primeira posição do array(as aspas duplas)
			colecao[0] = colecao[0].substring(1);

			// remove as aspas duplas da ultima String
			int ultimaPosicao = colecao.length - 1;
			String ultimaString = colecao[ultimaPosicao];
			colecao[ultimaPosicao] = ultimaString.substring(0, ultimaString.length() - 1);
			return colecao;
		}

	
}
