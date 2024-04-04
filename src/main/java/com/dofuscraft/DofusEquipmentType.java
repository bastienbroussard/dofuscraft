package com.dofuscraft;

public enum DofusEquipmentType {
    HAT("hat"),
    CLOAK("cloak"),
    AMULET("amulet"),
    RING("ring"),
    BELT("belt"),
    BOOTS("boots");

    private final String name;

    DofusEquipmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
