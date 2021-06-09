package me.ansandr.skindownloader;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import me.ansandr.skindownloader.commands.CommandGetCape;
import me.ansandr.skindownloader.commands.CommandGetSkin;

public class SkinDownloader extends JavaPlugin {
	
	private static Logger log;
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
		
		
		
		getCommand("getskin").setExecutor(new CommandGetSkin(this));
		getCommand("getcape").setExecutor(new CommandGetCape(this));
		
		generateFolder();
	}
	
	public void generateFolder() {
		skinsFolder = new File(getDataFolder() + File.separator + "skins");
		
		if (!skinsFolder.exists()) {
			skinsFolder.mkdirs();
		}
	}
	
	public File getSkinFolder() {
		return skinsFolder;
	}
}
