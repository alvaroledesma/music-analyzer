package analyzer.json;

import java.net.MalformedURLException;
import java.net.URL;

public class iTunesJSON {
	String wrapperType;
	String kind;
	int artistId;
	int collectionId;
	int trackId;
	String artistName;
	String collectionName;
	String trackName;
	String collectionCensoredName;
	String trackCensoredName;
	String artistViewUrl;
	String previewUrl;
	URL artworkUrl30;
	URL artworkUrl60;
	URL artworkUrl100;
	URL artworkUrl1000;
	double collectionPrice;
	double trackPrice;
	String releaseDate;
	String collectionExplicitness;
	String trackExplicitness;
	int discCount;
	int diskNumber;
	int trackCount;
	int trackNumber;
	long trackTimeMillis;
	String country;
	String currency;
	String primaryGenreName;
	boolean isStreamable;

	public String getWrapperType() {
		return wrapperType;
	}

	public String getKind() {
		return kind;
	}

	public int getArtistId() {
		return artistId;
	}

	public int getCollectionId() {
		return collectionId;
	}

	public int getTrackId() {
		return trackId;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public String getTrackName() {
		return trackName;
	}

	public String getCollectionCensoredName() {
		return collectionCensoredName;
	}

	public String getTrackCensoredName() {
		return trackCensoredName;
	}

	public String getArtistViewUrl() {
		return artistViewUrl;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public URL getArtworkUrl30() {
		return artworkUrl30;
	}

	public URL getArtworkUrl60() {
		return artworkUrl60;
	}

	public URL getArtworkUrl100() {
		return artworkUrl100;
	}

	public URL getArtworkUrl1000() {
		try {
			setArtworkUrl1000();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return artworkUrl1000;
	}

	public double getCollectionPrice() {
		return collectionPrice;
	}

	public double getTrackPrice() {
		return trackPrice;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getCollectionExplicitness() {
		return collectionExplicitness;
	}

	public String getTrackExplicitness() {
		return trackExplicitness;
	}

	public int getDiscCount() {
		return discCount;
	}

	public int getDiskNumber() {
		return diskNumber;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public long getTrackTimeMillis() {
		return trackTimeMillis;
	}

	public String getCountry() {
		return country;
	}

	public String getCurrency() {
		return currency;
	}

	public String getPrimaryGenreName() {
		return primaryGenreName;
	}

	public boolean isStreamable() {
		return isStreamable;
	}

	private void setArtworkUrl1000() throws MalformedURLException {
		String tempUrl = this.artworkUrl100.toString();
		String first = tempUrl.substring(0, tempUrl.lastIndexOf("/"));
		String end = tempUrl.substring(tempUrl.lastIndexOf("/"));
		end = end.replaceAll("100", "1000");
		String url = first + end;
		this.artworkUrl1000 = new URL(url);
	}
	 @SuppressWarnings("unused")
	private void printAll() {
		System.out.println(wrapperType);
		System.out.println(kind);
		System.out.println(artistId);
		System.out.println(collectionId);
		System.out.println(trackId);
		System.out.println(artistName);
		System.out.println(collectionName);
		System.out.println(trackName);
		System.out.println(collectionCensoredName);
		System.out.println(trackCensoredName);
		System.out.println(artistViewUrl);
		System.out.println(previewUrl);
		System.out.println(artworkUrl30);
		System.out.println(artworkUrl60);
		System.out.println(artworkUrl100);
		System.out.println(collectionPrice);
		System.out.println(trackPrice);
		System.out.println(releaseDate);
		System.out.println(collectionExplicitness);
		System.out.println(trackExplicitness);
		System.out.println(discCount);
		System.out.println(diskNumber);
		System.out.println(trackCount);
		System.out.println(trackNumber);
		System.out.println(trackTimeMillis);
		System.out.println(country);
		System.out.println(currency);
		System.out.println(primaryGenreName);
		System.out.println(isStreamable);

	}

}
