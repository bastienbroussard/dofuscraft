package com.dofuscraft.entity.ai;

import net.minecraft.entity.Entity;

import java.util.function.Consumer;

public class EntityEvent {
    private final Runnable callback;
    private final int triggerTick;

    private int currentTick = 0;

    public EntityEvent(int triggerTick, Runnable callback) {
        this.triggerTick = triggerTick;
        this.callback = callback;
    }

    public void increment() {
        this.currentTick++;
    }

    public boolean shouldBeTriggered() {
        return this.currentTick >= this.triggerTick;
    }

    public Runnable getCallback() {
        return this.callback;
    }
}
