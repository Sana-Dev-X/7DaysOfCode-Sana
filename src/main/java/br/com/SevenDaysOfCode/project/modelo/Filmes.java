package br.com.SevenDaysOfCode.project.modelo;

public class Filmes {

	private String id, rank, title, fullTitle, year, image, crew, imdbRating, imdbRatingCount;

	public Filmes(String id, String rank, String title, String fullTitle, String year, String image, String crew,
			String imdbRating, String imdbRatingCount) {
		setId(id);
		setRank(rank);
		setTitle(title);
		setFullTitle(fullTitle);
		setYear(year);
		setImage(image);
		setCrew(crew);
		setImdbRating(imdbRating);
		setImdbRatingCount(imdbRatingCount);
	}

	@Override
	public String toString() {
		return "\nID: " + getId() + "\nRank: " + getRank() + "\nTitle: " + getTitle() + "\nFull Title: "
				+ getFullTitle() + "\nYear: " + getYear() + "\nImage: " + getImage() + "\nCrew: " + getCrew()
				+ "\nIMDB Rating: " + getImdbRating() + "\nIMDB Rating Count: " + getImdbRatingCount() + "\n\n";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {

		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getFullTitle() {
		return fullTitle;
	}

	public void setFullTitle(String fullTitle) {

		this.fullTitle = fullTitle;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbRatingCount() {
		return imdbRatingCount;
	}

	public void setImdbRatingCount(String imdbRatingCount) {
		this.imdbRatingCount = imdbRatingCount;
	}

}
