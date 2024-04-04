package com.dofuscraft;

public class EquipmentEffect {
    private final Characteristic effect;
    private final int minValue;
    private final int maxValue;

    public EquipmentEffect(Characteristic effect, int minValue, int maxValue) {
        this.effect = effect;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Characteristic getEffect() {
        return this.effect;
    }

    public int getMinValue() {
        return this.minValue;
    }

    public int getMaxValue() {
        return this.maxValue;
    }
}
