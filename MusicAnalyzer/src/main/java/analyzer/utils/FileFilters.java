package analyzer.utils;

public class FileFilters {
	public static final String[] AUDIO_EXTENSIONS =

			{ ".3gp", ".aa", ".aac", ".aax", ".act", ".aiff", ".amr", ".ape", ".au", ".awb", ".dct", ".dss", ".flac",
					".gsm", ".m4a", ".m4b", ".m4p", ".mp3", ".mpc", ".ogg", ".oga", ".opus", ".ra", ".rm", ".raw",
					".sln", ".tta", ".vox", ".wav", ".wma", ".wv", ".webm",

					".3GP", ".AA", ".AAC", ".AAX", ".ACT", ".AIFF", ".AMR", ".APE", ".AWB", ".DCT", ".DSS", ".FLAC",
					".GSM", ".M4A", ".M4B", ".MP3", ".MPC", ".OGG", ".OGA", ".OPUS", ".RA", ".RM", ".SLN", ".TTA",
					".VOX", ".WAV", ".WMA", ".WV", ".WEBM", };

	public static final String GP_FILES = ".3gp";
	public static final String AA_FILES = ".aa";
	public static final String AAC_FILES = ".aac";
	public static final String AAX_FILES = ".aax";
	public static final String ACT_FILES = ".act";
	public static final String AIFF_FILES = ".aiff";
	public static final String AMR_FILES = ".amr";
	public static final String APE_FILES = ".ape";
	public static final String AU_FILES = ".au";
	public static final String AWB_FILES = ".awb";
	public static final String DCT_FILES = ".dct";
	public static final String DSS_FILES = ".dss";
	public static final String FLAC_FILES = ".flac";
	public static final String GSM_FILES = ".gsm";
	public static final String M4A_FILES = ".m4a";
	public static final String M4B_FILES = ".m4b";
	public static final String M4P_FILES = ".m4p";
	public static final String MP3_FILES = ".mp3";
	public static final String MPC_FILES = ".mpc";
	public static final String OGG_FILES = ".ogg";
	public static final String OGA_FILES = ".oga";
	public static final String OPUS_FILES = ".opus";
	public static final String RA_FILES = ".ra";
	public static final String RM_FILES = ".rm";
	public static final String RAW_FILES = ".raw";
	public static final String SLN_FILES = ".sln";
	public static final String TTA_FILES = ".tta";
	public static final String VOX_FILES = ".vox";
	public static final String WAV_FILES = ".wav";
	public static final String WMA_FILES = ".wma";
	public static final String WV_FILES = ".wv";
	public static final String WEBM_FILES = ".webm";

	public static final String[] VIDEO_EXTENSIONS = { ".webm", ".mkv", ".flv", ".flv", ".vob", ".ogv, .ogg", ".drc",
			".gif", ".gifv", ".mng", ".avi", ".mov, .qt", ".wmv", ".yuv", ".rm", ".rmvb", ".asf", ".mp4", ".m4p",
			".m4v", ".mpg", ".mp2", ".mpeg", ".mpe", ".mpv", ".mpg", ".mpeg", ".m2v", ".m4v", ".svi", ".3gp", ".3g2",
			".mxf", ".roq", ".nsv", ".flv", ".f4v", ".f4p", ".f4a", ".f4b", ".mov",

			".WEBM", ".MKV", ".FLV", ".FLV", ".VOB", ".OGV, .OGG", ".DRC", ".GIF", ".MNG", ".AVI", ".MOV, .QT", ".WMV",
			".YUV", ".RM", ".RMVB", ".MP4", ".M4P", ".M4V", ".MPG", ".MP2", ".MPEG", ".MPE", ".MPG", ".MPEG", ".M2V",
			".M4V", ".SVI", ".3GP", ".3G2", ".ROQ", ".NSV", ".FLV", ".F4V", ".F4P", ".F4A", ".F4B", ".MOV" };

	public static final String[] WORD_EXTENSIONS = { ".doc", ".dot", ".docx", ".docm", ".dotx", ".dotm", ".docb",

			".DOC", ".DOT", ".DOCX", ".DOCM", ".DOTX", ".DOTM", ".DOCB" };

	public static final String[] EXCEL_EXTENSIONS = { ".xls", ".xlt", ".xlm", ".xlsx", ".xlsm", ".xltx", ".xltm",
			".xlsb", ".xla", ".xlam", ".xll", ".xlw",

			".XLS", ".XLT", ".XLM", ".XLSX", ".XLSM", ".XLTX", ".XLTM", ".XLSB", ".XLAM", ".XLL", ".XLW" };

	public static final String[] POWERPOINT_EXTENSIONS = { ".ppt", ".pot", ".pps", ".pptx", ".pptm", ".potx", ".potm",
			".ppam", ".ppsx", ".ppsm", ".sldx", ".sldm",

			".PPT", ".POT", ".PPS", ".PPTX", ".PPTM", ".POTX", ".POTM", ".PPAM", ".PPSM", ".SLDX", ".SLDM" };

	public static final String[] PUBLISHER_EXTENSION = { ".pub", ".PUB" };

	public static final String[] PICTURE_EXTENSIONS = { ".tif", ".tiff", ".gif", ".jpeg", "jpg", ".jif", ".jfif",
			".fpx", ".pcd", ".png", ".bmp",

			".TIF", ".TIFF", ".GIF", ".JPEG", "JPG", ".JIF", ".JFIF", ".FPX", ".PNG", ".BMP" };

	public static final String[] PDF_EXTENSION = { ".pdf", ".PDF" };

	public static final String[] DLL_EXTENSION = { ".dll", ".DLL" };

	public static final String[] JAVA_EXTENSION = { ".java", ".JAVA" };

	public static final String[] ARCHIVE_EXTENSIONS = { ".a", ".ar", ".cpio", ".shar", ".LBR", ".iso", ".lbr", ".mar",
			".tar", ".bz2", ".F", ".gz", ".lz", ".lzma", ".lzo", ".rz", ".sfark", ".sz", ".?Q?", ".?Z?", ".xz", ".z",
			".Z", ".infl", ".7z", ".s7z", ".ace", ".afa", ".alz", ".apk", ".arc", ".arj", ".b1", ".ba", ".bh", ".cab",
			".car", ".cfs", ".cpt", ".dar", ".dd", ".dgc", ".dmg", ".ear", ".gca", ".ha", ".hki", ".ice", ".jar",
			".kgb", ".lzh", ".lha", ".lzx", ".pak", ".partimg", ".paq6", ".paq7", ".paq8", ".pea", ".pim", ".pit",
			".qda", ".rar", ".rk", ".sda", ".sea", ".sen", ".sfx", ".sit", ".sitx", ".sqx", ".tar.gz", ".tgz", ".tar.Z",
			".tar.bz2", ".tbz2", ".tar.lzma", ".tlz", ".uc", ".uc0", ".uc2", ".ucn", ".ur2", ".ue2", ".uca", ".uha",
			".war", ".wim", ".xar", ".xp3", ".yz1", ".zip", ".zipx", ".zoo", ".zpaq", ".zz", ".ecc", ".par .par2",

			".A", ".AR", ".CPIO", ".SHAR", ".LBR", ".ISO", ".LBR", ".MAR", ".BZ2", ".F", ".GZ", ".LZ", ".LZMA", ".LZO",
			".RZ", ".SZ", ".?Q?", ".?Z?", ".XZ", ".Z", ".Z", ".INFL", ".S7Z", ".ACE", ".AFA", ".ALZ", ".APK", ".ARC",
			".ARJ", ".BA", ".BH", ".CAB", ".CAR", ".CFS", ".CPT", ".DAR", ".DGC", ".DMG", ".EAR", ".GCA", ".HA", ".HKI",
			".ICE", ".KGB", ".LZH", ".LHA", ".LZX", ".PAK", ".PARTIMG", ".PAQ6", ".PAQ8", ".PEA", ".PIM", ".PIT",
			".QDA", ".RAR", ".RK", ".SEA", ".SEN", ".SFX", ".SIT", ".SITX", ".SQX", ".TAR.GZ", ".TAR.Z", ".TAR.BZ2",
			".TBZ2", ".TAR.LZMA", ".TLZ", ".UC", ".UC0", ".UCN", ".UR2", ".UE2", ".UCA", ".UHA", ".WAR", ".WIM", ".XP3",
			".YZ1", ".ZIP", ".ZIPX", ".ZOO", ".ZPAQ", ".ZZ", ".PAR .PAR2", };

	public static final String[] IOS_EXTENSION = { ".ipa", ".IPA" };

	public static final String[] CONTACT_EXTENSION = { ".vcf", ".VCF" };

	public static final String[] TXT_EXTENSION = { ".txt", ".TXT" };

	public static final String[] BOOKS_EXTENSIONS = { ".mobi", ".epub", ".ibooks", ".azw",

			".MOBI", ".EPUB", ".IBOOKS", ".AZW" };

	public static final String[] ISO_EXTENSION = { ".iso", ".ISO" };

	public static final String[] XML_EXTENSION = { ".xml", ".XML" };

	public static final String[] SUB_EXTENSION = { ".srt", ".SRT" };

	public static final String[] ITUNESARTWORK_EXTENSION = {
			// ".itc2", ".ITC2"
			".ithmb", ".ITHMB" };
}
