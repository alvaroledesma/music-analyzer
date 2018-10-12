package analyzer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class FileProcessor {

	private File originDirectory;
	private File endDirectory;
	private File exceptionDirectory;

	private static final JFileChooser FILE_CHOOSER = new JFileChooser();

	public FileProcessor() {
		FILE_CHOOSER.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	public File getOriginDirectory() {
		FILE_CHOOSER.setDialogTitle("Select MP3 Origin Directory");
		this.originDirectory = openDirectory();
		System.out.println("Origin Directory: " + originDirectory);
		return originDirectory;
	}

	public File getEndDirectory() {
		FILE_CHOOSER.setDialogTitle("Select MP3 End Directory");
		this.endDirectory = openDirectory();
		System.out.println("End Directory: " + endDirectory);
		return endDirectory;
	}

	public File getExceptionDirectory() {
		FILE_CHOOSER.setDialogTitle("Select MP3 Exception");
		this.exceptionDirectory = openDirectory();
		System.out.println("Exception Directory: " + exceptionDirectory);
		return exceptionDirectory;
	}

	public void moveFile(File endDirectory, File sourceFile) throws IOException {
		Path source = Paths.get(sourceFile.toURI());
		File temp = new File(endDirectory.getAbsolutePath().concat("\\" + sourceFile.getName()));
		while (temp.exists()) {
			temp = createDuplicate(temp);
		}
		Path destination = Paths.get(temp.toURI());
		System.out.println("Moving: " + sourceFile.getName());
		Files.move(source, destination);

	}

	public static void fileDelete(File file) {
		System.out.println("Deleting: " + file.getName());
		file.delete();
	}

	public List<File> filterFiles(String filter, List<File> directory) {
		List<File> filterdFiles = new ArrayList<File>();
		for (File x : directory) {
			if (x.getName().contains(filter)) {
				filterdFiles.add(x);
			}
		}
		return filterdFiles;
	}

	public List<File> FileOrDirectory(File check) {
		List<File> files = new ArrayList<File>();
		for (File query : check.listFiles()) {
			if (query.isFile())
				files.add(query);

			else if (query.isDirectory())
				FileOrDirectory(query);
		}
		return files;
	}

	private static File openDirectory() {
		if (FILE_CHOOSER.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return FILE_CHOOSER.getSelectedFile();
		} else {
			System.out.println("Cancelled");
			System.exit(0);
			return null;
		}
	}

	public File createDuplicate(File x) {
		String name = getFileName(x);
		String extension = getExtension(x);
		String location = getFileLocation(x);
		String first_par = "(";
		String last_par = ")";

		int num = 1;

		Matcher m = Pattern.compile(".*?\\((\\d+)\\)").matcher(name);
		if (m.find()) {
			num = Integer.valueOf(m.group(1)) + 1;
			String no_dupe_name = m.group(0).substring(0, m.group(0).lastIndexOf("("));
			File n = new File(location + no_dupe_name + first_par + num + last_par + extension);
			return n;
		}
		File n = new File(location + name + first_par + num + last_par + extension);
		return n;
	}

	public static String getExtension(File x) {
		return new String(x.toString().substring(x.toString().lastIndexOf("."), x.toString().length()));
	}

	public static String getFileName(File x) {
		return new String(x.getName().substring(0, x.getName().length() - getExtension(x).length()));
	}

	public static String getFileLocation(File x) {
		return new String(x.toString().substring(0, x.toString().lastIndexOf("\\") + 1));
	}

}
