package br.com.SevenDaysOfCode.project.app;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.SevenDaysOfCode.project.modelo.Filmes;

public class JSONHandler {

	 static List<Filmes> parseInfo(String[] colecao) {
		 /*
		  * Retorna uma lista de Filmes com seus todos preenchidos
		  * 
		  * Ex:
		  * String title = listaFilmes.get(5).getTitle();
		  */
		 
		int pos = 0;

		List<Filmes> listaFilmes = new ArrayList<>();

		for (String string : colecao) {
		
			String[] infos;
			infos = colecao[pos].split("\",\"");
			String[] id = infos[0].split("\":\"");
			String[] rank = infos[1].split("\":\"");
			String[] title = infos[2].split("\":\"");
			String[] fullTitle = infos[3].split("\":\"");
			String[] year = infos[4].split("\":\"");
			String[] image = infos[5].split("\":\"");
			String[] crew = infos[6].split("\":\"");
			String[] imdbRating = infos[7].split("\":\"");
			String[] imdbRatingCount = infos[8].split("\":\"");

			listaFilmes.add(new Filmes(id[1], rank[1], title[1], fullTitle[1], year[1], image[1], crew[1],
					imdbRating[1], imdbRatingCount[1].substring(0, imdbRatingCount[1].length() - 1)));
			pos++;
		}

		return listaFilmes;
	}
	
	static String[] parseJson(String json) {
/*
 * Retorna um String[] com a coleção de filmes, cada posição do array com todas as informações em uma linha
 */
		 
			Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
			if (!matcher.matches()) {
				throw new IllegalArgumentException("Não encontrado em " + json);
			}

			String mColecao = matcher.group(1);
			String[] colecao = mColecao.split("\\},\\{");

			colecao[0] = colecao[0].substring(1);

			int ultimaPosicao = colecao.length - 1;
			String ultimaString = colecao[ultimaPosicao];
			colecao[ultimaPosicao] = ultimaString.substring(0, ultimaString.length() - 1);
			return colecao;
		}
}
