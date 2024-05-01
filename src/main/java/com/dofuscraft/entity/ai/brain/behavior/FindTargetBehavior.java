package com.dofuscraft.entity.ai.brain.behavior;

import com.dofuscraft.entity.DofusMobEntity;
import com.dofuscraft.entity.ai.Behavior;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

public class FindTargetBehavior extends Behavior {
    private final int radius;

    public FindTargetBehavior(int radius) {
        this.radius = radius;
    }

    @Override
    public void start(DofusMobEntity entity) {
        // define radius
        var box = new Box(entity.getX() - this.radius, entity.getY() - this.radius, entity.getZ() - this.radius,
                entity.getX() + this.radius, entity.getY() + this.radius, entity.getZ() + this.radius);

        // search for closest player inside this radius
        double dist = Double.MAX_VALUE;
        PlayerEntity closest = null;
        for (PlayerEntity player : entity.getWorld().getPlayers()) {
            if (box.contains(player.getPos()) /*&& predicate.test(player)*/) {
                double playerDist = player.squaredDistanceTo(entity);

                if (playerDist < dist) {
                    dist = playerDist;
                    closest = player;
                }
            }
        }

        if (closest != null) {
        }
    }

    @Override
    public void tick(DofusMobEntity entity) {

    }

    @Override
    public void stop(DofusMobEntity entity) {

    }
}
