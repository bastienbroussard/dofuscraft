package com.dofuscraft.entity.ai.brain.behavior;

import com.dofuscraft.entity.DofusMobEntity;
import com.dofuscraft.entity.ai.Behavior;

public class WaitBehavior extends Behavior {
    private final int delay;

    public WaitBehavior(int delay) {
        this.delay = delay;
    }

    @Override
    public void start(DofusMobEntity entity) {
        super.start(entity);

        entity.setDelayedEvent(delay, () -> this.stop(entity));
    }
}
