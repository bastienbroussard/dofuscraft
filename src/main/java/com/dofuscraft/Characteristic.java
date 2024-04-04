package com.dofuscraft;

import net.minecraft.util.Formatting;

public enum Characteristic {
    VITALITY("vitality"),
    STRENGTH("strength"),
    INTELLIGENCE("intelligence"),
    CHANCE("chance"),
    AGILITY("agility"),
    WISDOM("wisdom"),
    EARTH_DAMAGE("earth_damage"),
    FIRE_DAMAGE("fire_damage"),
    WATER_DAMAGE("water_damage"),
    AIR_DAMAGE("air_damage"),
    CRITICAL("critical"),
    SUMMON("summon");

    private final String name;

    Characteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getTranslationKey() {
        return "attribute.name.generic." + this.name;
    }

    public Formatting getColor() {
        Formatting color = null;
        switch (this) {
            case VITALITY, CRITICAL, SUMMON -> color = Formatting.WHITE;
            case STRENGTH, EARTH_DAMAGE -> color = Formatting.YELLOW;
            case CHANCE, WATER_DAMAGE -> color = Formatting.BLUE;
            case WISDOM -> color = Formatting.DARK_PURPLE;
            case INTELLIGENCE, FIRE_DAMAGE -> color = Formatting.RED;
            case AGILITY, AIR_DAMAGE -> color = Formatting.GREEN;
        }

        return color;
    }
}
