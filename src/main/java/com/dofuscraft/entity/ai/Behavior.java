package com.dofuscraft.entity.ai;

import com.dofuscraft.entity.DofusMobEntity;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

import java.util.function.Consumer;

public abstract class Behavior {
    private Consumer<DofusMobEntity> onStartCallback;
    private Consumer<DofusMobEntity> onStopCallback;

    @MustBeInvokedByOverriders
    protected void start(DofusMobEntity entity) {
        if (this.onStartCallback != null) {
            this.onStartCallback.accept(entity);
        }
    }

    @MustBeInvokedByOverriders
    protected void tick(DofusMobEntity entity) {
        // nothing
    }

    @MustBeInvokedByOverriders
    protected void stop(DofusMobEntity entity) {
        if (this.onStopCallback != null) {
            this.onStopCallback.accept(entity);
        }
    }

    public Behavior onStart(Consumer<DofusMobEntity> callback) {
        this.onStartCallback = callback;
        return this;
    }

    public Behavior onStop(Consumer<DofusMobEntity> callback) {
        this.onStopCallback = callback;
        return this;
    }
}