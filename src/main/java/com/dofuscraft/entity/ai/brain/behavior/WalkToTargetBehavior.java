package com.dofuscraft.entity.ai.brain.behavior;

import com.dofuscraft.entity.DofusMobEntity;
import com.dofuscraft.entity.ai.Behavior;
import com.dofuscraft.entity.ai.MemoryCell;
import net.minecraft.entity.LivingEntity;

public class WalkToTargetBehavior extends Behavior {
    private int closeEnoughDistance = 3;

    @Override
    public void tick(DofusMobEntity entity) {
        super.tick(entity);

        var attackTarget = (LivingEntity) entity.getDofusBrain().getMemory(MemoryCell.ATTACK_TARGET);
        if (attackTarget == null) {
            return;
        }

        if (attackTarget.getBlockPos().getManhattanDistance(entity.getBlockPos()) <= this.closeEnoughDistance) {
            this.stop(entity);
        }
        else {
            entity.getNavigation().startMovingTo(attackTarget, 0.5f);
        }
    }

    @Override
    public void stop(DofusMobEntity entity) {
        super.stop(entity);

        entity.getNavigation().stop();
    }

    public WalkToTargetBehavior closeEnoughDistance(int distance) {
        this.closeEnoughDistance = distance;
        return this;
    }
}
