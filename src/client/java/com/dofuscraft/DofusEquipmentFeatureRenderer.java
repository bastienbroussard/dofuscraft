package com.dofuscraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class DofusEquipmentFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private final boolean isSlim;

    public DofusEquipmentFeatureRenderer(
            FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, boolean isSlim) {
        super(context);
        this.isSlim = isSlim;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity,
                       float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        int i = 0;
        var armorItemStacks = entity.getArmorItems();
        for (var itemStack : armorItemStacks) {
            var item = itemStack.getItem();
            if (item instanceof DofusEquipmentItem dofusEquipmentItem) {
                var type = dofusEquipmentItem.getType();
                var renderer = DofusEquipmentRendererRegistry.getRenderer(item, this.isSlim);
                if (renderer != null) {
                    matrices.push();
                    renderer.render(type, i == 1, this.getContextModel(), matrices, vertexConsumers,
                            light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
                    matrices.pop();
                }
            }

            i++;
        }
    }
}
