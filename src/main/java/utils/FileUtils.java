package utils;

import javax.ejb.Stateless;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Base64;


@Stateless
public class FileUtils {
	private static final Logger logger = Logger.getLogger(FileUtils.class);

	
	public String uploadImage(String filePath) {
		try {
			byte[] fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath));
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			logger.info("encode: "+encodedString);
			return encodedString;
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
	}

}
