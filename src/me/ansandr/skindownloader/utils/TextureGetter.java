package me.ansandr.skindownloader.utils;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_16_R3.EntityPlayer;


public class TextureGetter {
	
	public static String[] getFromPlayer(Player player) {
		EntityPlayer playerNMS = ((CraftPlayer) player).getHandle();
		GameProfile profile = playerNMS.getProfile();
		
		Property property = profile.getProperties().get("textures").iterator().next();
		
		String texture = property.getValue();
		String signature = property.getSignature();
		
		return new String[] {texture, signature}; //Вся информация в texture
	}
}
