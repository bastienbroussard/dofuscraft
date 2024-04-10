package com.dofuscraft;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.EntityAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DofusCraft implements ModInitializer {
	public static final String MOD_ID = "dofuscraft";
    public static final Logger LOGGER = LoggerFactory.getLogger("dofuscraft");

	public static final Map<Characteristic, EntityAttribute> ATTRIBUTES = new HashMap<>();

	@Override
	public void onInitialize() {
		// register characteristic attributes
		/*for (var characteristic : Characteristic.values()) {
			var attribute = new ClampedEntityAttribute(characteristic.getTranslationKey(), 0, 0, Integer.MAX_VALUE);
			ATTRIBUTES.put(characteristic, attribute);
			Registry.register(Registries.ATTRIBUTE, characteristic.getName(), attribute);
		}*/
	}
}