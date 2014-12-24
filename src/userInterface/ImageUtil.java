package userInterface;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import exception.ClientException;


public class ImageUtil {
	
	//Avatar folder
	public final static String AVATAR_FOLDER = "images/avatars";
	
	//
	public static Map<String, ImageIcon> getAvatars() {
		try {
			File folderFile = new File(AVATAR_FOLDER);
			File[] files = folderFile.listFiles();
			Map<String, ImageIcon> avaters = new HashMap<String, ImageIcon>();
			
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				avaters.put(file.getPath(), new ImageIcon(file.getAbsolutePath()));
			}
			return avaters;
		} catch (Exception e) {
			throw new ClientException("Error in reading avatars");
		}
	}
}