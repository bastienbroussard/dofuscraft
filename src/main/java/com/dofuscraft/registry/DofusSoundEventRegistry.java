package com.dofuscraft.registry;

import com.dofuscraft.DofusCraft;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class DofusSoundEventRegistry {
    // mobs
    public static SoundEvent PIWI_ATTACK;
    public static SoundEvent PIWI_HURT;
    public static SoundEvent PIWI_DEATH;

    // spells
    public static SoundEvent HAND;

    public static void registerAll() {
        PIWI_ATTACK = register("piwi_attack");
        PIWI_HURT = register("piwi_hurt");
        PIWI_DEATH = register("piwi_death");
        HAND = register("hand");
    }

    private static SoundEvent register(String name) {
        var identifier = new Identifier(DofusCraft.MOD_ID, name);
        var soundEvent = SoundEvent.of(identifier);
        return Registry.register(Registries.SOUND_EVENT, identifier, soundEvent);
    }
}
