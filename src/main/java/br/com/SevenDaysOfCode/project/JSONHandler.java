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
		 * Esse m�todo faz um la�o que itera por cada item do array fazendo um split e
		 * colocando em uma variavel de um objeto tipo Filmes novo
		 * 
		 * Dentro da classe Filmes tem um construtor que recebe os parametros e coloca
		 * em suas devidas variaveis
		 * 
		 * ainda � provis�rio...
		 */

		// cria um array de filmes para inserir os filmes e suas informa��es
		List<Filmes> listaFilmes = new ArrayList<>();

		// cria um la�o para atribuir as informa��es para cada filme
		for (String string : colecao) {

			// cria um array que vai receber as informa��es separadas de cada informa��es
			// sobre os filmes de cada vez o la�o rodar
			String[] infos;
			// divide as informa��es do primeiro filme pelos caracteres ","
			infos = colecao[pos].split("\",\"");

			// divide as informa��es que j� foram separadas e remove ainda os caracteres ":"
			// sendo assim, j� n�o tem os demarcadores de inicio e fim nas Strings, n�o vou
			// precisar usar o substring nem fazer la�os pra ficar removendo
			// fica mais c�digo, por�m acho que � mais f�cil de ler

			String[] id = infos[0].split("\":\"");
			String[] rank = infos[1].split("\":\"");
			String[] title = infos[2].split("\":\"");
			String[] fullTitle = infos[3].split("\":\"");
			String[] year = infos[4].split("\":\"");
			String[] image = infos[5].split("\":\"");
			String[] crew = infos[6].split("\":\"");
			String[] imdbRating = infos[7].split("\":\"");
			String[] imdbRatingCount = infos[8].split("\":\"");

			// passa todas as informa��es que foram parseadas para a cole��o de filmes, o
			// construtor chama os sets na ordem
			listaFilmes.add(new Filmes(id[1], rank[1], title[1], fullTitle[1], year[1], image[1], crew[1],
					imdbRating[1], imdbRatingCount[1].substring(0, imdbRatingCount[1].length() - 1)));
			pos++;
		}

		// retorna a lista de filmes j� populada
		return listaFilmes;
	}
	
	static String[] parseJson(String json) {

		 //Pega os dados do json e trata dele deixando as informa��es dos filmes separadas, cada um com seu filme
		 
			Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
			if (!matcher.matches()) {
				throw new IllegalArgumentException("N�o encontrado em " + json);
			}

			//Pega o que foi filtrado e joga em uma String
			String mColecao = matcher.group(1);
			//Separa as informa��es segundo o padr�o de caracteres e colca num array
			String[] colecao = mColecao.split("\\},\\{");

			//Descarta o primeiro caracter da primeira posi��o do array(as aspas duplas)
			colecao[0] = colecao[0].substring(1);

			// remove as aspas duplas da ultima String
			int ultimaPosicao = colecao.length - 1;
			String ultimaString = colecao[ultimaPosicao];
			colecao[ultimaPosicao] = ultimaString.substring(0, ultimaString.length() - 1);
			return colecao;
		}

	
}
