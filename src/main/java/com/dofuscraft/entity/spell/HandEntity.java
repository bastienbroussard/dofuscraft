package com.dofuscraft.entity.spell;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HandEntity extends SpellEntity implements GeoEntity {
    private static final RawAnimation ANIM = RawAnimation.begin().thenPlay("hand.model.execute");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public HandEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        var controller = new AnimationController<>(this, "animations", animationState -> animationState.setAndContinue(ANIM))
                .setParticleKeyframeHandler(state -> {

                });

        controllers.add(controller);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}
