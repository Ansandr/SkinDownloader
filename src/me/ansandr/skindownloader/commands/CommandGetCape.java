package me.ansandr.skindownloader.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ansandr.skindownloader.SkinDownloader;
import me.ansandr.skindownloader.utils.Downloader;
import me.ansandr.skindownloader.utils.TextureGetter;
import net.md_5.bungee.api.ChatColor;

public class CommandGetCape implements CommandExecutor {
	
	private SkinDownloader plugin;
	
	public CommandGetCape(SkinDownloader plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage("Only for ops!");
			return true;
		}
		
		if (args.length == 1) {
			if (plugin.getServer().getPlayer(args[0]) != null) {
				Player player = plugin.getServer().getPlayer(args[0]);
				String texture = TextureGetter.getFromPlayer(player)[0];
				
				String url = Downloader.getCapeURL(texture);
				
				if (url == null) {
					sender.sendMessage("Couldn't find a player cape");
					return true;
				}
				
				try {
					Downloader.saveFile(url, String.format("%s_cape", player.getName()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				sender.sendMessage(ChatColor.GREEN + String.format("Successful generated cape image with name %s_cape.png", player.getName()));
				return true;
			}
			sender.sendMessage("player is offline");
		}
		return false;
	}

}
