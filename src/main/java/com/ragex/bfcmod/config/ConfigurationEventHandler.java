package com.ragex.bfcmod.config;

import java.util.ArrayList;

import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;

public class ConfigurationEventHandler {
	private ArrayList<IReloadable> reloadables = new ArrayList<IReloadable>();

	public ConfigurationEventHandler() {
		// Register this class to the event bus
		FMLCommonHandler.instance().bus().register(this);
	}

	public void registerReloadable(IReloadable rel) {
		reloadables.add(rel);
	}

	public void reloadConfigOptions() {
		for (IReloadable reloadable : reloadables) {
			if (reloadable != null) {
				reloadable.reloadConfigOptions();
			}
		}
	}

	@SubscribeEvent
	public void onServerStarted(FMLServerStartedEvent event) {
		reloadConfigOptions();
	}
}
