package com.ragex.bfcmod.events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.ragex.bfcmod.MarkdownFormatter;
import com.ragex.bfcmod.TextFormatter;
import com.ragex.bfcmod.config.ConfigHandler;
import com.ragex.bfcmod.config.IReloadable;
import com.ragex.bfcmod.config.PermissionsHandler;
import com.ragex.bfcmod.utils.BetterForgeChatUtilities;
import com.mojang.authlib.GameProfile;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.ServerChatEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ChatEventHandler {
	private SimpleDateFormat timestampFormat = null;
	private boolean markdownEnabled = false;
	private String chatMessageFormat = "";
	private boolean loaded = false;

	public void reloadConfigOptions() {
		loaded = false;
		// Simulating the config handler for demonstration
		timestampFormat = ConfigHandler.config.enableTimestamp ? new SimpleDateFormat(ConfigHandler.config.timestampFormat) : null;
		markdownEnabled = ConfigHandler.config.enableMarkdown;
		chatMessageFormat = ConfigHandler.config.chatMessageFormat;
		loaded = true;
	}

	public ChatStyle getHoverClickEventStyle(ChatComponentTranslation old) {
		if (old != null) {
			Object[] args = old.getFormatArgs();
			for (Object arg : args) {
				if (arg instanceof ChatComponentText) {
					ChatStyle style = ((ChatComponentText) arg).getChatStyle();
					if (style != null && style.getChatClickEvent() != null) {
						return style;
					}
				}
			}
		}
		return null;
	}

	@SubscribeEvent
	public void onServerChat(ServerChatEvent e) {
		if (!loaded) return; // Do nothing until everything's ready

		EntityPlayerMP player = e.player;
		GameProfile profile = player.getGameProfile();
		UUID uuid = profile.getId();

		if (e.message == null || e.message.isEmpty()) return;

		String msg = e.message;
		String tstamp = (timestampFormat == null) ? "" : timestampFormat.format(new Date());
		String name = BetterForgeChatUtilities.getRawPreferredPlayerName(profile);
		String fmat = chatMessageFormat.replace("$time", tstamp).replace("$name", name);

		String beforeMsg = fmat.substring(0, fmat.indexOf("$msg"));
		String afterMsg = fmat.substring(fmat.indexOf("$msg") + 4);

		boolean enableColor = PermissionsHandler.playerHasPermission(uuid, PermissionsHandler.coloredChatNode);
		boolean enableStyle = PermissionsHandler.playerHasPermission(uuid, PermissionsHandler.styledChatNode);

		String emsg = "";
		if (!enableColor && TextFormatter.messageContainsColorsOrStyles(msg, true)) {
			emsg = "You are not permitted to use colors";
		}
		if (!enableStyle && TextFormatter.messageContainsColorsOrStyles(msg, false)) {
			emsg += emsg.isEmpty() ? "You are not permitted to use styles" : " or styles";
		}
		if (!emsg.isEmpty()) {
			ChatComponentText errorMessage = new ChatComponentText(emsg + "!");
			errorMessage.getChatStyle().setColor(EnumChatFormatting.RED).setBold(true);
			player.addChatMessage(errorMessage);
			return;
		}

		// Convert markdown to standard formatting if allowed
		if (markdownEnabled && enableStyle && PermissionsHandler.playerHasPermission(uuid, PermissionsHandler.markdownChatNode)) {
			msg = MarkdownFormatter.markdownStringToFormattedString(msg);
		}

		// Format message components
		ChatComponentText beforeComponent = new ChatComponentText(beforeMsg);
		ChatComponentText afterComponent = new ChatComponentText(afterMsg);
		ChatComponentText msgComponent = TextFormatter.stringToFormattedText(msg, enableColor, enableStyle);

		// Apply hover and click events if present
		ChatStyle hoverClickStyle = getHoverClickEventStyle((ChatComponentTranslation) e.component);
		if (hoverClickStyle != null) {
			msgComponent.setChatStyle(hoverClickStyle);
		}

		e.component = beforeComponent.appendSibling(msgComponent).appendSibling(afterComponent);
	}
}