package com.dofuscraft.entity;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.ai.goal.WanderAroundNearGoal;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PiwiEntity extends PathAwareEntity implements GeoEntity {
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.model.idle");
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.model.walk");
    private static final RawAnimation RUN_ANIM = RawAnimation.begin().thenLoop("animation.model.run");
    private static final RawAnimation PECK_ANIM = RawAnimation.begin().thenPlay("animation.model.peck");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private boolean isPecking = false;

    public PiwiEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WanderAroundNearGoal(this, 0.3));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        var animationController = new AnimationController<>(this, "animations", state -> {
            if (state.isMoving()) {
                this.isPecking = false;
                state.setControllerSpeed(1.5f);
                return state.setAndContinue(WALK_ANIM);
            }
            else {
                // chance to display peck animation
                if (this.random.nextInt(600) == 0) {
                    this.isPecking = true;
                }

                if (this.isPecking && state.getController().hasAnimationFinished()) {
                    this.isPecking = false;
                }

                state.setControllerSpeed(1.0f);
                return state.setAndContinue(this.isPecking ? PECK_ANIM : IDLE_ANIM);
            }
        });
        controllers.add(animationController);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}
