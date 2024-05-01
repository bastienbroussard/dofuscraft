package com.dofuscraft.entity.ai.brain.behavior;

import com.dofuscraft.entity.DofusMobEntity;
import com.dofuscraft.entity.ai.Behavior;
import com.dofuscraft.entity.ai.MemoryCell;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.util.math.Vec3d;

public class FleeTargetBehavior extends Behavior {
    private int fleeDistance = 5;

    @Override
    public void start(DofusMobEntity entity) {
        super.start(entity);

        var attackTarget = (LivingEntity) entity.getDofusBrain().getMemory(MemoryCell.ATTACK_TARGET);
        if (attackTarget == null) {
            return;
        }

        Vec3d walkTarget = null;
        while (walkTarget == null) {
            walkTarget = NoPenaltyTargeting.findFrom(entity, this.fleeDistance, 10, attackTarget.getPos());
        }

        entity.getNavigation().startMovingTo(walkTarget.x, walkTarget.y, walkTarget.z, 0.5f);
    }

    @Override
    public void tick(DofusMobEntity entity) {
        super.tick(entity);

        if (entity.getNavigation().isIdle()) {
            this.stop(entity);
        }
    }

    public FleeTargetBehavior fleeDistance(int distance) {
        this.fleeDistance = distance;
        return this;
    }
}
