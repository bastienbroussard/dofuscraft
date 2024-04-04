package com.dofuscraft;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DofusCraft implements ModInitializer {
	public static final String MOD_ID = "dofuscraft";
    public static final Logger LOGGER = LoggerFactory.getLogger("dofuscraft");

	public static final EntityAttribute WISDOM = new ClampedEntityAttribute("attribute.name.characteristic.wisdom", 0, 0, Integer.MAX_VALUE);

	@Override
	public void onInitialize() {
		Registry.register(Registries.ATTRIBUTE, "attribute.name.characteristic.wisdom", WISDOM);
	}
}