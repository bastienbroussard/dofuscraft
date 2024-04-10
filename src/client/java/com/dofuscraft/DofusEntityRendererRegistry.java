package com.dofuscraft;

import com.dofuscraft.entity.PiwiEntity;
import com.dofuscraft.render.entity.PiwiEntityRenderer;
import com.dofuscraft.render.entity.model.mob.PiwiEntityModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import static com.dofuscraft.DofusEntityRegister.PIWI;

public class DofusEntityRendererRegistry {
    public static void registerAll() {
        EntityRendererRegistry.register(PIWI, (context) -> new PiwiEntityRenderer(context, new PiwiEntityModel()));
        FabricDefaultAttributeRegistry.register(PIWI, PiwiEntity.createMobAttributes());
    }
}
