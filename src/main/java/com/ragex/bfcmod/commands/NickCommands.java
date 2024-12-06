package com.ragex.bfcmod.commands;

import java.util.List;
import java.util.UUID;

import com.ragex.bfcmod.BetterForgeChat;
import com.ragex.bfcmod.TextFormatter;
import com.ragex.bfcmod.config.ConfigHandler;
import com.ragex.bfcmod.config.PermissionsHandler;
import com.ragex.bfcmod.config.PlayerData;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class NickCommands extends CommandBase {
	private static boolean cfgWhoIsEnabled = false;
	private static boolean cfgNickEnabled = false;
	private static int minNicknameLength = 1;
	private static int maxNicknameLength = 50;

	public static boolean nicknameIntegrationEnabled = false;

	public static void reloadConfig() {
		// Placeholder for loading configuration values.
		minNicknameLength = 1; // Example value
		maxNicknameLength = 50; // Example value
		cfgWhoIsEnabled = true; // Example value
		cfgNickEnabled = true; // Example value
	}

	@Override
	public String getCommandName() {
		return "nick";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/nick <nickname> or /nickfor <username> <nickname>";
	}

	@Override
	public List<String> getCommandAliases() {
		List<String> aliases = new ArrayList<String>();
		aliases.add("nickname");
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.addChatMessage(new ChatComponentText("Usage: /nick <nickname> or /nickfor <username> <nickname>"));
			return;
		}

		if (args.length == 1) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				String nick = args[0];
				if (nick.length() >= minNicknameLength && nick.length() <= maxNicknameLength) {
					setNickname(player.getGameProfile(), nick);
					sender.addChatMessage(new ChatComponentText("Nickname set to: " + nick));
				} else {
					sender.addChatMessage(new ChatComponentText("Nickname must be between " + minNicknameLength + " and " + maxNicknameLength + " characters."));
				}
			} else {
				sender.addChatMessage(new ChatComponentText("This command can only be used by players."));
			}
		} else if (args.length == 2) {
			String username = args[0];
			String nick = args[1];
			GameProfile profile = lookupGameProfile(username);
			if (profile != null) {
				if (nick.length() >= minNicknameLength && nick.length() <= maxNicknameLength) {
					setNickname(profile, nick);
					sender.addChatMessage(new ChatComponentText("Nickname for " + username + " set to: " + nick));
				} else {
					sender.addChatMessage(new ChatComponentText("Nickname must be between " + minNicknameLength + " and " + maxNicknameLength + " characters."));
				}
			} else {
				sender.addChatMessage(new ChatComponentText("Player not found: " + username));
			}
		} else {
			sender.addChatMessage(new ChatComponentText("Invalid usage! Use /nick <nickname> or /nickfor <username> <nickname>"));
		}
	}

	private void setNickname(GameProfile profile, String nick) {
		// Placeholder: Implement nickname assignment logic
		// Example: Save nickname to a map or configuration file
	}

	private GameProfile lookupGameProfile(String username) {
		MinecraftServer server = MinecraftServer.getServer();
		if (server != null) {
			for (Object playerObj : server.getConfigurationManager().playerEntityList) {
				EntityPlayerMP player = (EntityPlayerMP) playerObj;
				if (player.getCommandSenderName().equalsIgnoreCase(username)) {
					return player.getGameProfile();
				}
			}
		}
		return null;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true; // Adjust permission logic as needed
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
		}
		return null;
	}
}