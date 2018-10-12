package analyzer.scrapers;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetroLyrics {

	private static final String BASE_URL = "http://www.metrolyrics.com/";
	private static final String LYRICS = "-lyrics-";
	private static final String END_URL = ".html";

	private String title;
	private String artist;

	private String lyrics;
	
	public MetroLyrics(String songName, String artistName) {
		this.title = removeSpaces(songName);
		this.artist = removeSpaces(artistName);

		String url = new String(BASE_URL + title + LYRICS + artist + END_URL);
		setLyrics(url);
//		System.out.println(lyrics);
	}

	private static String removeSpaces(String s) {
		if (s.contains(" ")) {
			return s.replaceAll("\\s+", "-");
		} else {
			return s;
		}

	}

	private void setLyrics(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements lyricsBody = doc.getElementsByClass("lyrics-body");
			String line = "";
			for (Element verse : lyricsBody) {
				line += verse.getElementsByClass("verse").html();
			}
			line = line.replaceAll("<br> ", "\n");
			this.lyrics = line;
		} catch (IOException e) {
			this.lyrics = "";
		}
	}

	public String getLyrics() {
		return lyrics;
	}

}