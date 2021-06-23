package me.ansandr.skinloder.versions;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_12_R1.EntityPlayer;

public class TextureManager_1_12_R1 implements TextureManager {

	@Override
	public String[] getFromPlayer(Player player) {
		EntityPlayer playerNMS = ((CraftPlayer) player).getHandle();
		GameProfile profile = playerNMS.getProfile();
		
		Property property = profile.getProperties().get("textures").iterator().next();
		
		String texture = property.getValue();
		String signature = property.getSignature();
		
		return new String[] {texture, signature};//All need info in texture
	}
}
