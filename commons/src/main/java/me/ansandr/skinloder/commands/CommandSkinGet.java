package me.ansandr.skinloder.commands;

import java.io.IOException;

import me.ansandr.skinloder.versions.TextureManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ansandr.skinloder.SkinLoder;
import me.ansandr.skinloder.utils.Downloader;
import net.md_5.bungee.api.ChatColor;

public class CommandSkinGet implements CommandExecutor {
	
	private SkinLoder plugin;
	
	public CommandSkinGet(SkinLoder plugin) {
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
				String texture = plugin.getTextureManager().getFromPlayer(player)[0];
				
				String url = Downloader.getSkinURL(texture);
				
				if (url == null) {
					sender.sendMessage("Couldn't find a player skin");
				}
				
				try {
					Downloader.saveFile(url, String.format("%s_skin", player.getName()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				sender.sendMessage(ChatColor.GREEN + String.format("Successful generated skin image with name %s_skin.png", player.getName()));
				return true;
			}
			sender.sendMessage("player is offline");
		}
		return false;
	}
	
	
}