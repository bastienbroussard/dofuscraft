package com.dofuscraft;

import net.minecraft.util.Formatting;

public enum Characteristic {
    WISDOM("wisdom"),
    FIRE_DAMAGE("fire_damage");

    private final String name;

    Characteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getTranslationKey() {
        return "characteristic." + this.name;
    }

    public Formatting getColor() {
        Formatting color = null;
        switch (this.name) {
            case "wisdom" -> color = Formatting.DARK_PURPLE;
            case "fire_damage" -> color = Formatting.RED;
        }

        return color;
    }
}
