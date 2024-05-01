package com.dofuscraft.registry;

import com.dofuscraft.entity.ai.EntityState;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

public class DofusTrackedDataHandlerRegistry {
    public static final TrackedDataHandler<EntityState> STATE;

    static {
        STATE = TrackedDataHandler.ofEnum(EntityState.class);
        TrackedDataHandlerRegistry.register(STATE);
    }
}
