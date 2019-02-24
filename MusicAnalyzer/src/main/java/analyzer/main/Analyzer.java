package analyzer.main;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import acrcloud.ACRCloudExtrTool;
import acrcloud.ACRCloudRecognizer;
import analyzer.json.MasterJSON;
import analyzer.utils.FileFilters;
import analyzer.utils.FileProcessor;
import analyzer.utils.MP3Manager;

public class Analyzer {

	private static File originDirectory = new File("/Users/alvaroledesma/Desktop/PTSD Music");
	private static File endDirectory = new File("/Users/alvaroledesma/Desktop/New Music");
	private static File exceptionDirectory = new File("Users/alvaroledesma/Desktop/EXEPT");

	private static List<File> allFiles;
	private static List<File> filteredFiles;

	private static final String HOST = "identify-us-west-2.acrcloud.com";
	private static final String ACCESS_KEY = "e11ab73367d9186069c81405222670e8";
	private static final String ACCESS_SECRET = "FrBnNOS4pKtRjYx7EsLj4W9k2cn9c191kqrbN1fF";
	private static final int TIMEOUT_SECONDS = 10;

	public static void main(String[] args) throws Exception {
		Map<String, Object> config = new HashMap<String, Object>();
		config.put("host", HOST);
		config.put("access_key", ACCESS_KEY);
		config.put("access_secret", ACCESS_SECRET);
		config.put("debug", false);
		config.put("timeout", TIMEOUT_SECONDS);

		ACRCloudRecognizer re = new ACRCloudRecognizer(config);

		FileProcessor fp = new FileProcessor();
//		originDirectory = fp.getOriginDirectory();
		allFiles = fp.FileOrDirectory(originDirectory);
		filteredFiles = fp.filterFiles(FileFilters.MP3_FILES, allFiles);

//		endDirectory = fp.getEndDirectory();

//		exceptionDirectory = fp.getExceptionDirectory();

		for (File x : filteredFiles) {
			String result = re.recognizeByFile(x.toString(), 30);
			System.out.println(result);

			int fileDurationMS = ACRCloudExtrTool.getDurationMSByFile(x.toString());

			if (fileDurationMS >= 600000) // Song is longer than 10 min
			{
				fp.moveFile(exceptionDirectory, x);
			} else {

				MasterJSON m = new MasterJSON(result);
				MP3Manager manager = new MP3Manager(x, m, fp);
				File d = manager.getNewFileName(endDirectory);
				manager.saveFile(d);
			}
		}
	}

}
