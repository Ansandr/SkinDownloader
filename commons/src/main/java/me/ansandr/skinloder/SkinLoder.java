package me.ansandr.skinloder;

import java.io.File;
import java.util.logging.Logger;

import me.ansandr.skinloder.commands.CommandCapeGet;
import me.ansandr.skinloder.commands.CommandSkinGet;
import me.ansandr.skinloder.versions.TextureManager_1_12_R1;
import me.ansandr.skinloder.versions.TextureManager_1_16_R3;
import me.ansandr.skinloder.versions.TextureManager_1_17_R1;
import me.ansandr.skinloder.versions.TextureManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkinLoder extends JavaPlugin {
	
	private static Logger log;
	private TextureManager textureManager;
	public static File skinsFolder;
	
	
	@Override
	public void onEnable() {
		log = this.getLogger();
		if (!getServer().getOnlineMode()) {//if online-mode=false
			if (getServer().getPluginManager().getPlugin("SkinsRestorer") == null) {
				log.severe("[%s] - Disabled due no SkinsRestorer dependency found!");
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}

		generateFolder();

		setupTextureManager();
		
		getCommand("skinget").setExecutor(new CommandSkinGet(this));
		getCommand("capeget").setExecutor(new CommandCapeGet(this));
	}
	
	private void generateFolder() {
		skinsFolder = new File(getDataFolder() + File.separator + "skins");
		
		if (!skinsFolder.exists()) {
			skinsFolder.mkdirs();
		}
	}
	
	private void setupTextureManager() {
		
		String version;
		
		try {
			
			version = this.getServer().getClass().getPackage().getName().split("\\.")[3];
		} catch (ArrayIndexOutOfBoundsException ex) {
			return;
		}
		
		log.info("Your server is running version " + version);
		
		if (version.equals("v1_12_R1")) {
			textureManager = new TextureManager_1_12_R1();
		} else if (version.equals("v1_16_R3")) {
			textureManager = new TextureManager_1_16_R3();
		} else if (version.equals("v1_17_R1")) {
			textureManager = new TextureManager_1_17_R1();
		}
	}
	
	public File getSkinFolder() {
		return skinsFolder;
	}
	
	public TextureManager getTextureManager() {
		return textureManager;
	}
}