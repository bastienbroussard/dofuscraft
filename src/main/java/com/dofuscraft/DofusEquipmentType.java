package com.dofuscraft;

public enum DofusEquipmentType {
    HAT("hat", 0),
    CLOAK("cloak", 1),
    AMULET("amulet", 2),
    RING("ring", 3),
    BELT("belt", 5),
    BOOTS("boots", 6);

    private final String name;
    private final int id;

    DofusEquipmentType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
