package analyzer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.mpatric.mp3agic.ID3v1Genres;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import analyzer.json.MasterJSON;

public class MP3Manager {

	private static ID3v24Tag id3v24Tag = new ID3v24Tag();
	private static Mp3File mp3;
	private static String artistName;
	private static String songTitle;

	private static FileProcessor fileProcessor;
	
	private File song;

	public MP3Manager(File x, MasterJSON m, FileProcessor fp) throws Exception {
		song = x;
		fileProcessor = fp;
		artistName = m.getArtist();
		songTitle = m.getTitle();
		if (!song.toString().contains(".mp3")) {
			throw new Exception("Not an mp3 file!" + "\n" + "Please fix file: " + x);
		} else {
			mp3 = new Mp3File(x);
			mp3.setId3v2Tag(id3v24Tag);
			id3v24Tag.setTrack(m.getTrackNum());
			id3v24Tag.setAlbumArtist(m.getArtist());
			id3v24Tag.setArtist(m.getArtist());
			id3v24Tag.setTitle(m.getTitle());
			id3v24Tag.setAlbum(m.getAlbum());
			id3v24Tag.setYear(m.getYear());

			int genre = ID3v1Genres.matchGenreDescription(m.getGenre());
			id3v24Tag.setGenre(genre);

			id3v24Tag.setComment(null);
			id3v24Tag.setLyrics(m.getLyrics());
			id3v24Tag.setComposer(m.getComposer());
			id3v24Tag.setPublisher(m.getPublisher());

			byte[] picture_array = Files.readAllBytes(m.getArtwork().toPath());
			id3v24Tag.setAlbumImage(picture_array, m.getArtworkExtension());
		}

	}

	public void saveFile(File d) throws NotSupportedException, IOException {
	
		mp3.save(d.toString());
	}

	public File getNewFileName(File endDirectory)
	{
		String finalName = artistName + " - " + songTitle + ".mp3";
		File temp = new File(endDirectory.getAbsolutePath().concat("\\" + finalName ));
		while (temp.exists()) {
			temp = fileProcessor.createDuplicate(temp);
		}
		return temp;
	}
//
//	private static File createDuplicate(File x) {
//		String name = getFileName(x);
//		String extension = getExtension(x);
//		String location = getFileLocation(x);
//		String first_par = "(";
//		String last_par = ")";
//
//		int num = 1;
//
//		Matcher m = Pattern.compile(".*?\\((\\d+)\\)").matcher(name);
//		if (m.find()) {
//			num = Integer.valueOf(m.group(1)) + 1;
//			String no_dupe_name = m.group(0).substring(0, m.group(0).lastIndexOf("("));
//			File n = new File(location + no_dupe_name + first_par + num + last_par + extension);
//			return n;
//		}
//		File n = new File(location + name + first_par + num + last_par + extension);
//		return n;
//	}
//
//	private static String getExtension(File x) {
//		return new String(x.toString().substring(x.toString().lastIndexOf("."), x.toString().length()));
//	}
//
//	private static String getFileName(File x) {
//		return new String(x.getName().substring(0, x.getName().length() - getExtension(x).length()));
//	}
//
//	private static String getFileLocation(File x) {
//		return new String(x.toString().substring(0, x.toString().lastIndexOf("\\") + 1));
//	}

	@SuppressWarnings("unused")
	private void printAll(MasterJSON m) {
		System.out.println("Title: " + m.getTitle());
		System.out.println("Artist: " + m.getArtist());
		System.out.println("Album: " + m.getAlbum());
		System.out.println("Composer: " + m.getComposer());
		System.out.println("Publisher: " + m.getPublisher());
		System.out.println("Genre: " + m.getGenre());
		System.out.println("Year: " + m.getYear());
		System.out.println("TrackNumber: " + m.getTrackNum());
		System.out.println("DiskNumber: " + m.getDiskNum());
		System.out.println("isCompliation: " + m.isCompilation());
		System.out.println("BPM: " + m.getBpm());
		System.out.println("Comments: " + m.getComments());
		System.out.println("Artwork: " + m.getArtwork());
		System.out.println("Lyrics: " + m.getLyrics());
		System.out.println("Retrieved: " + m.isRetrieved());
	}
}
