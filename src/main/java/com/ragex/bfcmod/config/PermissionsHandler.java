package com.ragex.bfcmod.config;

import java.lang.reflect.Field;
import java.util.UUID;

import com.ragex.bfcmod.BetterForgeChat;
import com.ragex.bfcmod.TextFormatter;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.util.ChatComponentText;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PermissionsHandler {

	// Permissions map for manual handling
	private static final Map<String, Boolean> defaultPermissions = new HashMap<String, Boolean>();


	static {
		// Initialize default permissions
		defaultPermissions.put("chat.colors", true);
		defaultPermissions.put("chat.styles", true);
		defaultPermissions.put("chat.styles.md", true);
		defaultPermissions.put("tablist.nickname", true);
		defaultPermissions.put("tablist.metadata", true);
		defaultPermissions.put("commands.colors", true);
		defaultPermissions.put("commands.bfc.allowed", true);
		defaultPermissions.put("commands.bfc.colors", true);
		defaultPermissions.put("commands.bfc.info", true);
		defaultPermissions.put("commands.whois", true);
		defaultPermissions.put("commands.nick", true);
		defaultPermissions.put("commands.nick.others", true);
	}

	public PermissionsHandler() {
		// Register this class to the event bus
		FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void registerPermissionNodes(Object event) {
		// Manually add permission nodes if needed
		// Forge 1.7.10 doesn't have a PermissionGatherEvent equivalent
		for (String permission : defaultPermissions.keySet()) {
			// This is a placeholder for your permission initialization logic
			System.out.println("Registered permission: " + permission);
		}
	}

	private static Boolean getDefaultPermission(String permission) {
		return defaultPermissions.containsKey(permission) ? defaultPermissions.get(permission) : false;
	}

	public static boolean playerHasPermission(UUID uuid, String permission) {
		// Simplified manual permission check
		// Extend this logic if you integrate with a third-party permission mod
		try {
			return getDefaultPermission(permission);
		} catch (Exception e) {
			System.out.println("Error checking permission: " + permission);
			return false;
		}
	}

	public static ChatComponentText createTextComponent(String text) {
		return new ChatComponentText(text);
	}
}
