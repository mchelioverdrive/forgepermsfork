package com.ragex.bfcmod.commands;

import java.util.Arrays;

import com.ragex.bfcmod.BetterForgeChat;
import com.ragex.bfcmod.TextFormatter;
import com.ragex.bfcmod.config.ConfigHandler;
import com.ragex.bfcmod.config.PermissionsHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BfcCommands extends CommandBase {
	private static final String[] bfcModSubCommands = {"info", "colors", "test"};

	@Override
	public String getCommandName() {
		return "bfc";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/bfc <subcommand>";
	}

	@Override
	public List<String> getCommandAliases() {
		return Arrays.asList("betterforgechat");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid subcommand. Use info, colors, or test."));
			return;
		}

		String subCommand = args[0].toLowerCase();
		if ("info".equals(subCommand)) {
			handleInfoCommand(sender);
		} else if ("colors".equals(subCommand)) {
			handleColorsCommand(sender);
		} else if ("test".equals(subCommand)) {
			handleTestCommand(sender);
		} else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unknown subcommand."));
		}
	}

	private void handleInfoCommand(ICommandSender sender) {
		String metaProvName = "None";
		String nickProvName = "None";
		// Replace with actual checks for your metadata/nickname providers if applicable

		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "BetterForgeChat Mod Info:"));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Mod ID: " + EnumChatFormatting.LIGHT_PURPLE + "betterforgechat"));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Version: " + EnumChatFormatting.LIGHT_PURPLE + "1.0"));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Metadata Provider: " + EnumChatFormatting.LIGHT_PURPLE + metaProvName));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Nickname Provider: " + EnumChatFormatting.LIGHT_PURPLE + nickProvName));
	}

	private void handleColorsCommand(ICommandSender sender) {
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Available Colors:"));
		for (EnumChatFormatting color : EnumChatFormatting.values()) {
			sender.addChatMessage(new ChatComponentText(color.toString() + color.name()));
		}
	}

	private void handleTestCommand(ICommandSender sender) {
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Color & Styling Test:"));
		sender.addChatMessage(new ChatComponentText(
				EnumChatFormatting.RESET + "Normal " + EnumChatFormatting.BOLD + "Bold " + EnumChatFormatting.UNDERLINE + "Underline " + EnumChatFormatting.ITALIC + "Italic " + EnumChatFormatting.STRIKETHROUGH + "Strikethrough " + EnumChatFormatting.OBFUSCATED + "Obfuscated"));
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true; // Adjust permission checks here if needed
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, bfcModSubCommands);
		}
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}
}


