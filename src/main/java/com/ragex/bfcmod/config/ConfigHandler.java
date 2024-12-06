package com.ragex.bfcmod.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static Configuration config;

	// Configuration values
	public static String playerNameFormat;
	public static String chatMessageFormat;
	public static String timestampFormat;
	public static String discordBotToken;

	public static int maximumNicknameLength;
	public static int minimumNicknameLength;

	public static boolean enableTimestamp;
	public static boolean enableFtbEssentials;
	public static boolean enableLuckPerms;
	public static boolean enableMarkdown;
	public static boolean enableColorsCommand;
	public static boolean enableTabListIntegration;
	public static boolean enableMetadataInTabList;
	public static boolean enableNicknamesInTabList;
	public static boolean enableWhoisCommand;
	public static boolean enableChatNicknameCommand;
	public static boolean autoEnableChatNicknameCommand;
	public static boolean enableDiscordBotIntegration;

	public static void loadConfig(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();

			// Read configuration values
			playerNameFormat = config.getString("playerNameFormat", "General", "$prefix$name$suffix",
					"Controls the chat message format\n" +
							"$prefix: user's prefix or nothing\n" +
							"$suffix: user's suffix or nothing\n" +
							"$name: user's name or nickname if present");

			chatMessageFormat = config.getString("chatMessageFormat", "General", "$time | $name: $msg",
					"Controls the chat message format\n" +
							"$time: timestamp\n" +
							"$name: user's name or nickname\n" +
							"$msg: user's message");

			timestampFormat = config.getString("timestampFormat", "General", "HH:mm",
					"Timestamp format following the java SimpleDateFormat.\n" +
							"Read more: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html");

			discordBotToken = config.getString("discordBotToken", "General", "",
					"Discord bot token for integration.");

			enableTimestamp = config.getBoolean("enableTimestamp", "Features", true,
					"Enables or disables the filling in of timestamps.");
			enableFtbEssentials = config.getBoolean("useFtbEssentials", "Features", true,
					"Enables or disables FTB essentials nickname integration.");
			enableLuckPerms = config.getBoolean("useLuckPerms", "Features", true,
					"Enables or disables LuckPerms integration.");
			enableMarkdown = config.getBoolean("markdownEnabled", "Features", true,
					"Enables or disables markdown styling.");
			enableTabListIntegration = config.getBoolean("tabList", "Features", true,
					"Enables or disables custom tab list information.");
			enableMetadataInTabList = config.getBoolean("tabListMetadata", "Features", true,
					"Enables or disables prefixes & suffixes in the tab list.");
			enableNicknamesInTabList = config.getBoolean("tabListNicknames", "Features", true,
					"Enables or disables nicknames in the tab list.");
			enableColorsCommand = config.getBoolean("enableColorsCommand", "Features", true,
					"Enables or disables the /colors command.");
			enableWhoisCommand = config.getBoolean("enableWhoisCommand", "Features", true,
					"Enables or disables the integrated whois command.");
			enableChatNicknameCommand = config.getBoolean("enableIntegratedNicknames", "Features", false,
					"Enables or disables the integrated nickname command.");
			autoEnableChatNicknameCommand = config.getBoolean("autoIntegratedNicknames", "Features", true,
					"Automatically enables integrated nickname-related commands if FTB essentials is not present.");
			enableDiscordBotIntegration = config.getBoolean("enableDiscordIntegration", "Features", false,
					"Enables or disables discord integration.");

			maximumNicknameLength = config.getInt("maximumNicknameLength", "Limits", 50, 1, 500,
					"Maximum allowed nickname length for integrated nickname commands.");
			minimumNicknameLength = config.getInt("minimumNicknameLength", "Limits", 1, 1, 500,
					"Minimum allowed nickname length for integrated nickname commands.");

		} catch (Exception e) {
			System.err.println("Error loading configuration file: " + configFile.getName());
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}
