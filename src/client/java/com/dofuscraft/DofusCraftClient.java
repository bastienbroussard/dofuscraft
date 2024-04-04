package com.dofuscraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DofusCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		DofusEquipmentRendererRegistry.registerAll();
	}
}