package com.dofuscraft.entity.ai;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.DofusMobEntity;
import com.mojang.datafixers.util.Pair;

import java.util.HashMap;

import static com.dofuscraft.entity.DofusMobEntity.STATE;

public class DofusEntityBrain {
    private final DofusMobEntity entity;
    private final HashMap<MemoryCell, Object> memories = new HashMap<>();
    private final HashMap<EntityState, Behavior> behaviors = new HashMap<>();

    private EntityState currentState = null;

    public DofusEntityBrain(DofusMobEntity entity) {
        this.entity = entity;
    }

    public Object getMemory(MemoryCell cell) {
        return this.memories.get(cell);
    }

    public void setMemory(MemoryCell cell, Object value) {
        this.memories.put(cell, value);
    }

    @SafeVarargs
    public final void setBehaviors(Pair<EntityState, Behavior>... behaviors) {
        for (var behavior : behaviors) {
            this.behaviors.put(behavior.getFirst(), behavior.getSecond());
        }
    }

    public void tick() {
        var state = this.entity.getDataTracker().get(STATE);
        var behavior = this.behaviors.get(state);

        if (behavior == null) {
            DofusCraft.LOGGER.warn("No behavior for state: " + state.toString());
            return;
        }

        if (this.currentState != state) {
            // stop the old behavior
            /*if (this.currentState != null) {
                this.behaviors.get(this.currentState).stop(this.entity);
            }*/
            this.currentState = state;

            // start the new behavior
            behavior.start(this.entity);
        }

        behavior.tick(this.entity);
    }
}
