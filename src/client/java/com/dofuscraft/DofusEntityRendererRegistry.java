package com.dofuscraft;

import com.dofuscraft.entity.PiwiEntity;
import com.dofuscraft.render.entity.TelegraphEntityRenderer;
import com.dofuscraft.render.entity.model.mob.PiwiEntityModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static com.dofuscraft.registry.DofusEntityRegistry.PIWI;
import static com.dofuscraft.registry.DofusEntityRegistry.TELEGRAPH;

public class DofusEntityRendererRegistry {
    public static void registerAll() {
        EntityRendererRegistry.register(TELEGRAPH, TelegraphEntityRenderer::new);
        EntityRendererRegistry.register(PIWI, (context) -> new GeoEntityRenderer<>(context, new PiwiEntityModel()));
        FabricDefaultAttributeRegistry.register(PIWI, PiwiEntity.createMobAttributes());
    }
}
