package me.ansandr.skinloder.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.ansandr.skinloder.SkinLoder;

public class Downloader {
	
	private static File skinFolder = SkinLoder.skinsFolder;
	
	public static void saveFile(String url, String fileName) throws IOException {
		
		File file = new File(skinFolder.getPath() + File.separator + String.format("%s.png", fileName));
		
		URL skinURL = new URL(url);
		
		// SAVE FILE
		InputStream inputStream = skinURL.openStream();
		Files.copy(inputStream, file.toPath());
	}



	public static JsonObject getTextureJsonObject(String texture) {
		byte[] bytes = Base64.getDecoder().decode(texture);
		
		String decoded = new String(bytes);
		
		//GET URL
		JsonParser parser = new JsonParser();
		
		Object obj = parser.parse(decoded);
		JsonObject jo = (JsonObject) obj;
		
		return jo.get("textures").getAsJsonObject();
	}
	
	public static String getSkinURL(String texture) {
		JsonObject texturesProperty = getTextureJsonObject(texture);
		
		try {
			return texturesProperty.get("SKIN").getAsJsonObject().get("url").getAsString();
		} catch(NullPointerException e) {
			return null;
		}
	}
	
	public static String getCapeURL(String texture) {
		JsonObject texturesProperty = getTextureJsonObject(texture);
		try {
			return texturesProperty.get("CAPE").getAsJsonObject().get("url").getAsString();
		} catch(NullPointerException e) {
			return null;
		}
		
	}
}