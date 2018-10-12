package analyzer.scrapers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import analyzer.json.iTunesJSON;

public class iTunesScraper {

	private static final String BASE_URL = "https://itunes.apple.com/search?term=";
	private static final String FILTER = "&entity=musicTrack&limit=5";

	private List<iTunesJSON> dataList = new ArrayList<iTunesJSON>();

	private String itunes_results;
	private String searchString;
	private URL queryURL;

	public iTunesScraper(String search) {
		this.searchString = search;
		try {
			this.queryURL = createURL(searchString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			this.itunes_results = readURL(queryURL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonElement root = new JsonParser().parse(itunes_results);
		JsonArray results = root.getAsJsonObject().get("results").getAsJsonArray();
		Gson gson = new Gson();

		for (JsonElement result : results) {
			iTunesJSON data = gson.fromJson(result.getAsJsonObject(), iTunesJSON.class);
			dataList.add(data);
		}

	}

	private static URL createURL(String searchString) throws MalformedURLException {
		String query = searchString.replaceAll(" ", "+");

		String url = BASE_URL + query + FILTER;

		URL url_query = new URL(url);
		return url_query;
	}

	private static String readURL(URL url) throws Exception {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}

	}

	public List<iTunesJSON> getiTunesJSONs() {
		return dataList;
	}

}
