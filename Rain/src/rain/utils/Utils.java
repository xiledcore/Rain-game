package rain.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static File[] getFilesInFolder(String path) {
		File folder = new File(path);
		return folder.listFiles();
	}

	public static List<String> getLevelContents(File level) {
		List<String> contents = new ArrayList<>();
		
		try(
			FileReader fr = new FileReader(level);
			BufferedReader br = new BufferedReader(fr);
		) {
			String line = null;
			while((line = br.readLine()) != null) {
				if(line.startsWith("#"))
					continue;
				contents.add(line);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return contents;
	}
	
	public static int parseInt(String num) {
		try {
			return Integer.parseInt(num);
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			return 0;
		}
	}
	
}
