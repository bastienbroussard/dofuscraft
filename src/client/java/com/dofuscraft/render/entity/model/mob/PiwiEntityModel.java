package com.dofuscraft.render.entity.model.mob;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.PiwiEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PiwiEntityModel extends GeoModel<PiwiEntity> {
    @Override
    public Identifier getModelResource(PiwiEntity animatable) {
        return new Identifier(DofusCraft.MOD_ID, "geo/piwi.geo.json");
    }

    @Override
    public Identifier getTextureResource(PiwiEntity animatable) {
        return new Identifier(DofusCraft.MOD_ID, "textures/entity/mob/piwi/blue_piwi.png");
    }

    @Override
    public Identifier getAnimationResource(PiwiEntity animatable) {
        return new Identifier(DofusCraft.MOD_ID, "animations/piwi.animation.json");
    }
}
