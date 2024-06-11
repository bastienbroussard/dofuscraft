package com.dofuscraft.render.entity.model.spell;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.spell.HandEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HandEntityModel extends GeoModel<HandEntity> {
    @Override
    public Identifier getModelResource(HandEntity animatable) {
        return new Identifier(DofusCraft.MOD_ID, "geo/spell/hand.geo.json");
    }

    @Override
    public Identifier getTextureResource(HandEntity animatable) {
        return new Identifier(DofusCraft.MOD_ID, "textures/entity/spell/hand.png");
    }

    @Override
    public Identifier getAnimationResource(HandEntity animatable) {
        return new Identifier(DofusCraft.MOD_ID, "animations/spell/hand.animation.json");
    }
}
