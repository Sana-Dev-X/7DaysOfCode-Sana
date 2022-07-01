package br.com.SevenDaysOfCode.project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import br.com.SevenDaysOfCode.project.modelo.Filmes;

public class HTMLGenerator {

	static int pos = 0;

	public static void generate(List<Filmes> colecao, int position) throws FileNotFoundException {

		// Cria uma String HTML para usar de template para as páginas que serão criadas

		String html = "<!DOCTYPE html><html lang=\"pt-br\">" + "<head><title>" + colecao.get(position).getTitle()
				+ "</title><meta charset=\"utf-8\"> "
				+ "<link rel=\"stylesheet\" href=\"src\\main\\webapp\\WEB-INF\\style.css\"></head>" + "<body><h1>"
				+ colecao.get(position).getTitle() + "</h1>" + "<div class=\"principal\"><img id=\"img\" src=\""
				+ colecao.get(position).getImage() + "\"></div>" + "<p>Full title: "
				+ colecao.get(position).getFullTitle() + "<br>Year:" + colecao.get(position).getYear() + "<br>Rank:"
				+ colecao.get(position).getRank() + "<br>Crew:" + colecao.get(position).getCrew() + "<br>IMDB rating: "
				+ colecao.get(position).getImdbRating() + "<br>IMDB rating count:"
				+ colecao.get(position).getImdbRatingCount() +

				"</p></body></html>";

		// Cria um writer para criar as páginas por meio do "template"
		PrintWriter pw = new PrintWriter(
				colecao.get(position).getRank() + " - " + colecao.get(position).getTitle() + ".html");
		pw.print(html);
		pw.close();
	}

	public static void HTMLColetionGenerator(List<Filmes> filmes) throws FileNotFoundException {

		// Faz um laço para gerar todas as páginas de filme de uma vez só

		for (Filmes f : filmes) {
			generate(filmes, pos);
			pos++;
		}

		pos = 0;
	}

}
