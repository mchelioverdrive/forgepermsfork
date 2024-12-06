package com.ragex.bfcmod.utils;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.ragex.bfcmod.config.PlayerData;
import com.mojang.authlib.GameProfile;

public class IntegratedNicknameProvider implements INicknameProvider {
	@Override public String getPlayerNickname(@NonNull GameProfile player) {
		return PlayerData.getNickname(player.getId());
	}
	@Override public @NonNull String getProviderName() {
		return "BetterForgeChat";
	}
}
