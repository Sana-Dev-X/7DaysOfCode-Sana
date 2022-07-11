package br.com.SevenDaysOfCode.project.app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import br.com.SevenDaysOfCode.project.modelo.Filmes;

public class HTMLGenerator {

	static int pos = 0;

	public static void generate(List<Filmes> colecao) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("index.html");

		pw.println(
				"""
						<html>
						<head>
							<meta charset=\"utf-8\">
							<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
							<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
										+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">

						</head>
						<body>
								""");

		for (Filmes f : colecao) {
			
			String div = """
					<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem; relative; margin-left: auto; margin-right: auto;\">
						<h4 class=\"card-header\">%s</h4>
						<div class=\"card-body\">
							<img class=\"card-img\" src=\"%s\" alt=\"%s\">
							<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
							<p>Titulo Completo: %s</p>
							<p>Elenco: %s</p>
						</div>
					</div>
					""";
			pw.println(String.format(div, f.getTitle(), f.getImage(), f.getTitle(), f.getImdbRating(), f.getYear(), f.getFullTitle(),f.getCrew()));
		}

		pw.println("""
						</body>
				</html>
						""");

		pw.close();
	}
}
