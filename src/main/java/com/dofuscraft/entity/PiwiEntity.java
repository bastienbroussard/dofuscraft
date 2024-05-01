package com.dofuscraft.entity;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.ai.EntityState;
import com.dofuscraft.entity.ai.MemoryCell;
import com.dofuscraft.entity.ai.brain.behavior.AttackWithTelegraphBehavior;
import com.dofuscraft.entity.ai.brain.behavior.FleeTargetBehavior;
import com.dofuscraft.entity.ai.brain.behavior.WaitBehavior;
import com.dofuscraft.entity.ai.brain.behavior.WalkToTargetBehavior;
import com.dofuscraft.registry.DofusSoundEventRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.world.World;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PiwiEntity extends DofusMobEntity implements GeoEntity {
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.model.idle");
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.model.walk");
    private static final RawAnimation RUN_ANIM = RawAnimation.begin().thenLoop("animation.model.run");
    private static final RawAnimation PECK_ANIM = RawAnimation.begin().thenPlay("animation.model.peck");
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("animation.model.attack");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private boolean isPecking = false;

    public PiwiEntity(EntityType<PiwiEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initBrain() {
        this.brain.setBehaviors(
                Pair.of(EntityState.APPROACHING, new WalkToTargetBehavior()
                        .closeEnoughDistance(2)
                        .onStop(entity -> entity.getDataTracker().set(STATE, EntityState.ATTACKING))),
                Pair.of(EntityState.ATTACKING, new AttackWithTelegraphBehavior()
                        .onStart(entity -> {
                            entity.setDelayedEvent(15, () -> {
                                entity.getWorld().playSound(null, entity.getBlockPos(), DofusSoundEventRegistry.PIWI_ATTACK, SoundCategory.HOSTILE);
                            });
                            entity.setDelayedEvent(25, () -> {
                                entity.getDataTracker().set(STATE, EntityState.FLEEING);
                            });
                        })),
                Pair.of(EntityState.FLEEING, new FleeTargetBehavior()
                        .onStop(entity -> entity.getDataTracker().set(STATE, EntityState.WAITING))),
                Pair.of(EntityState.WAITING, new WaitBehavior(15)
                        .onStart(entity -> {
                            var attackTarget = (LivingEntity) entity.getDofusBrain().getMemory(MemoryCell.ATTACK_TARGET);
                            if (attackTarget == null) {
                                return;
                            }

                            var angleToTarget = (float) Math.toDegrees(Math.atan2(attackTarget.getZ() - entity.getZ(), attackTarget.getX() - entity.getX())) - 90;
                            entity.teleport((ServerWorld) entity.getWorld(), entity.getX(), entity.getY(), entity.getZ(), null, angleToTarget, entity.getPitch());
                        })
                        .onStop(entity -> entity.getDataTracker().set(STATE, EntityState.APPROACHING)))
        );
    }

    @Override
    public void tick() {
        super.tick();

        this.setCustomName(Text.of(this.getDataTracker().get(STATE).toString().toLowerCase()));
        this.setCustomNameVisible(true);
    }

    /* @Override
    public BrainActivityGroup<PiwiEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<>()
                        .stopIf(entity -> {
                            var state = entity.getDataTracker().get(STATE);
                            return state != EntityState.APPROACHING && state != EntityState.FLEEING;
                        }));
    }

    @Override
    public BrainActivityGroup<PiwiEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        new TargetOrRetaliate<>(),
                        new SetRandomLookTarget<>()),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<>().speedModifier((owner, target) -> 0.3f),
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30) + 30)));
    }*/

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        var animationController = new AnimationController<>(this, "animations", animationState -> {
            var state = this.dataTracker.get(STATE);
            if (state == EntityState.ATTACKING) {
                animationState.setControllerSpeed(1f);
                return animationState.setAndContinue(ATTACK_ANIM);
            }
            else if (animationState.isMoving()) {
                this.isPecking = false;

                if (state == EntityState.APPROACHING || state == EntityState.FLEEING) {
                    animationState.setControllerSpeed(2f);
                    return animationState.setAndContinue(RUN_ANIM);
                }
                else {
                    animationState.setControllerSpeed(1.5f);
                    return animationState.setAndContinue(WALK_ANIM);
                }
            }
            else {
                // chance to display peck animation
                if (this.random.nextInt(600) == 0) {
                    this.isPecking = true;
                }

                if (this.isPecking && animationState.getController().hasAnimationFinished()) {
                    this.isPecking = false;
                }

                animationState.setControllerSpeed(1.0f);
                return animationState.setAndContinue(this.isPecking ? PECK_ANIM : IDLE_ANIM);
            }
        });
        controllers.add(animationController);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return DofusSoundEventRegistry.PIWI_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return DofusSoundEventRegistry.PIWI_DEATH;
    }
}
