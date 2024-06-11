package com.dofuscraft;

import com.dofuscraft.registry.DofusSoundEventRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DofusCraft implements ModInitializer {
	public static final String MOD_ID = "dofuscraft";
    public static final Logger LOGGER = LoggerFactory.getLogger("dofuscraft");

	public static final Map<Characteristic, EntityAttribute> ATTRIBUTES = new HashMap<>();

	public static final DefaultParticleType HAND = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		DofusSoundEventRegistry.registerAll();
		// register characteristic attributes
		/*for (var characteristic : Characteristic.values()) {
			var attribute = new ClampedEntityAttribute(characteristic.getTranslationKey(), 0, 0, Integer.MAX_VALUE);
			ATTRIBUTES.put(characteristic, attribute);
			Registry.register(Registries.ATTRIBUTE, characteristic.getName(), attribute);
		}*/

		// particles
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "hand"), HAND);
	}
}