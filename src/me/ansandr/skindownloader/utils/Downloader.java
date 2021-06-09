package me.ansandr.skindownloader.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.ansandr.skindownloader.SkinDownloader;

public class Downloader {
	
	private static File skinFolder = SkinDownloader.skinsFolder;
	
	public static void saveFile(String url, String fileName) throws IOException {
		
		File file = new File(skinFolder.getPath() + File.separator + String.format("%s.png", fileName));
		
		URL skinURL = new URL(url);
		
		// SAVE FILE
		FileUtils.copyURLToFile(skinURL, file);
	}
	
	public static JsonObject getTextureJsonObject(String texture) {
		byte[] bytes = Base64.decodeBase64(texture);
		
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
