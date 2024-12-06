package com.ragex.bfcmod.events;

import com.ragex.bfcmod.commands.BfcCommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandRegistrationHandler {

	public static void registerCommands(FMLServerStartingEvent event) {
		ServerCommandManager commandManager = (ServerCommandManager) event.getServer().getCommandManager();

		// Register your commands here
		commandManager.registerCommand(new BfcCommands());
	}
}
