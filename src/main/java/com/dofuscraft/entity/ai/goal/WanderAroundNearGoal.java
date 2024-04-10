package com.dofuscraft.entity.ai.goal;

import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class WanderAroundNearGoal extends WanderAroundGoal {
    public WanderAroundNearGoal(PathAwareEntity mob, double speed) {
        super(mob, speed);
    }

    @Nullable
    protected Vec3d getWanderTarget() {
        return NoPenaltyTargeting.find(this.mob, 3, 2);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
