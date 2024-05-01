package com.dofuscraft.entity.ai.brain.behavior;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.DofusMobEntity;
import com.dofuscraft.entity.TelegraphEntity;
import com.dofuscraft.entity.ai.Behavior;
import com.dofuscraft.entity.ai.MemoryCell;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;

import java.util.List;

public class AttackWithTelegraphBehavior extends Behavior {
    @Override
    public void start(DofusMobEntity entity) {
        super.start(entity);

        var attackTarget = (LivingEntity) entity.getDofusBrain().getMemory(MemoryCell.ATTACK_TARGET);
        if (attackTarget == null) {
            return;
        }

        // look at target
        var angleToTarget = (float) Math.toDegrees(Math.atan2(attackTarget.getZ() - entity.getZ(), attackTarget.getX() - entity.getX()));
        entity.setYaw(angleToTarget - 90);

        // spawn telegraph
        var world = entity.getWorld();
        world.spawnEntity(new TelegraphEntity(world, entity, 0.75f));
    }
}
