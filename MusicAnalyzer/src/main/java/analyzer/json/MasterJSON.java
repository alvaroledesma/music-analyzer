package analyzer.json;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import analyzer.scrapers.MetroLyrics;
import analyzer.scrapers.iTunesScraper;

public class MasterJSON {

	private static final int METADATA_PARSE_ERROR = 2002;
	private static final int UNABLE_TO_GENERATE_FINGERPRINT_ERROR = 2004;
	private static final int TIMEOUT_ERROR = 2005;
	private static final int RECOGNITION_SERVICE_ERROR = 3000;
	private static final int RECORDING_ERROR = 2000;
	private static final int NO_RECOGNITION_RESULT = 1001;
	private static final int RECOGNITION_SUCCEED = 0;

	private static final List<Metadata> filledMetadatas = new ArrayList<Metadata>();

	private static final File USER_DIR = new File(System.getProperty("user.dir"));
	private static final File ARTWORK_LOCATION = new File(USER_DIR + "\\Artwork");

	private String title;
	private String artist;
	private String album;
	private String composer;
	private String publisher;
	private String genre;
	private String year;
	private int trackNum;
	private int diskNum;
	private boolean compilation;
	private String bpm;
	private String comments;

	private File artwork;
	private String artworkExtension;

	private String lyrics;

	private boolean retrieved;

	public MasterJSON(String JSON) {
		JsonElement root = new JsonParser().parse(JSON);

		Gson gson = new Gson();

		// ACRCloud Status
		JsonObject fetched = root.getAsJsonObject().get("status").getAsJsonObject();
		Status status = gson.fromJson(fetched, Status.class);

		switch (status.code) {

		// Song found
		case RECOGNITION_SUCCEED: {
			this.retrieved = true;
			JsonArray music = root.getAsJsonObject().get("metadata").getAsJsonObject().get("music").getAsJsonArray();

			// Get all data from multiple elements
			for (JsonElement element : music) {
				Metadata metadata = gson.fromJson(element, Metadata.class);
				filledMetadatas.add(metadata);
			}

			// Find highest possible match percentage
			int loc = 0;
			int higher = 0;

			for (int x = 0; x < filledMetadatas.size(); x++) {

				if (higher < filledMetadatas.get(x).score) {
					loc = x;
				}
				higher = filledMetadatas.get(x).score;
			}
			Metadata m = filledMetadatas.get(loc);

			// Title
			this.title = m.title;

			// Artists
			List<JsonObject> artists = m.artists;
			this.artist = getMultiplesAsString(artists);

			// Album
			this.album = m.album.name;

			// Composer currently null
			// TODO GET COMPOSER
			this.composer = null;

			// Publishing label
			this.publisher = m.label;

			// Genre
			List<JsonObject> genres = m.genres;
			this.genre = getMultiplesAsString(genres);

			// Year
			this.year = m.release_date.substring(0, 4);

			// Compilation
			// This remains false
			this.compilation = false;

			// Comments
			// There should not be any comments unless otherwise noted ***
			this.comments = null;

			// Lyrics
			if (artist.contains(",")) {
				MetroLyrics lyrics = new MetroLyrics(title, artist.substring(0, artist.indexOf(",")));
				this.lyrics = lyrics.getLyrics();
			} else {
				MetroLyrics lyrics = new MetroLyrics(title, artist);
				this.lyrics = lyrics.getLyrics();
			}

			// Remaining itunes data
			iTunesScraper itunes = new iTunesScraper(title);
			List<iTunesJSON> data = itunes.getiTunesJSONs();

			for (int x = 0; x < data.size(); x++) {
				String itunes_title = data.get(x).trackName;
				String itunes_artist = data.get(x).artistName;

				if (title.equals(itunes_title) && artist.equals(itunes_artist)) {
					if (this.genre == null || !this.genre.equals(data.get(x).primaryGenreName)) {
						this.genre = data.get(x).primaryGenreName;
					}

					this.trackNum = data.get(x).trackNumber;
					this.diskNum = data.get(x).diskNumber;

					if (data.get(x).getArtworkUrl1000() != null) {
						this.artwork = getArtwork(data.get(x).artworkUrl1000);
					}

				}
			}

			break;
		}

		case NO_RECOGNITION_RESULT:
			this.retrieved = false;
			break;
		case METADATA_PARSE_ERROR:
			this.retrieved = false;
			break;
		case UNABLE_TO_GENERATE_FINGERPRINT_ERROR:
			this.retrieved = false;
			break;
		case TIMEOUT_ERROR:
			this.retrieved = false;
			break;
		case RECOGNITION_SERVICE_ERROR:
			this.retrieved = false;
			break;
		case RECORDING_ERROR:
			this.retrieved = false;
			break;
		default:
			this.retrieved = false;
			break;
		}

	}

	public void printAll() {
		System.out.println("Title: " + title);
		System.out.println("Artist: " + artist);
		System.out.println("Album: " + album);
		System.out.println("Composer: " + composer);
		System.out.println("Publisher: " + publisher);
		System.out.println("Genre: " + genre);
		System.out.println("Year: " + year);
		System.out.println("TrackNumber: " + trackNum);
		System.out.println("DiskNumber: " + diskNum);
		System.out.println("isCompliation: " + compilation);
		System.out.println("BPM: " + bpm);
		System.out.println("Comments: " + comments);
		System.out.println("Artwork: " + artwork.toString());
		System.out.println("Lyrics: " + lyrics);
		System.out.println("Retrieved: " + retrieved);
	}

	private static String getMultiplesAsString(List<JsonObject> array) {
		if (array == null || array.isEmpty()) {
			return null;
		} else {
			String s = "";
			for (int y = 0; y < array.size(); y++) {
				if (y == array.size() - 1) {
					s += array.get(y).get("name").getAsString();
				} else {

					s += array.get(y).get("name").getAsString() + ", ";
				}
			}
			return s;
		}
	}

	private File getArtwork(URL artworkUrl100) {
		String extension = getExtension(artworkUrl100.toString());
		this.artworkExtension = extension;
		File f = new File(ARTWORK_LOCATION + "\\" + title + " - " + artist + extension);
		try {
			FileUtils.copyURLToFile(artworkUrl100, f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	private static String getExtension(String x) {
		return new String(x.substring(x.lastIndexOf(".")));
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getComposer() {
		return composer;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getGenre() {
		return genre;
	}

	public String getYear() {
		return year;
	}

	public String getTrackNum() {
		return Integer.toString(trackNum);
	}

	public int getDiskNum() {
		return diskNum;
	}

	public boolean isCompilation() {
		return compilation;
	}

	public String getBpm() {
		return bpm;
	}

	public String getComments() {
		return comments;
	}

	public File getArtwork() {
		return artwork;
	}

	public String getLyrics() {
		return lyrics;
	}

	public boolean isRetrieved() {
		return retrieved;
	}

	public String getArtworkExtension() {
		return artworkExtension;
	}

}

class Status {
	String msg;
	int code;
	String version;
}

class Metadata {

	ExternalIDs external_ids;
	int play_offset_ms;
	ExternalMetadata external_metadata;
	List<JsonObject> artists;
	String title;
	List<JsonObject> genres;
	String release_date;
	String label;
	int duration;
	Album album;
	String acrid;
	int result_from;
	int score;

	static class ExternalIDs {
		String isrc;
		String upc;
	}

	static class ExternalMetadata {

		Youtube youtube;
		Deezer deezer;
		Spotify spotify;

		static class Youtube {
			String vid;
		}

		static class Deezer {
			Album album;
			List<JsonObject> artists;
			List<JsonObject> genres;
			Track track;

			static class Album {
				String id;
			}

			static class Track {
				String id;
			}
		}

		static class Spotify {
			Album album;
			List<JsonObject> artists;
			Track track;

			static class Album {
				String id;
			}

			static class Track {
				String id;
			}
		}
	}

	static class Album {
		String name;
	}

}
