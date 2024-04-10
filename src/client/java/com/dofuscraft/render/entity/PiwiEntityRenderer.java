package com.dofuscraft.render.entity;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.PiwiEntity;
import me.x150.renderer.render.Renderer3d;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.awt.*;

public class PiwiEntityRenderer extends GeoEntityRenderer<PiwiEntity> {
    public PiwiEntityRenderer(EntityRendererFactory.Context renderManager, GeoModel<PiwiEntity> model) {
        super(renderManager, model);
        addRenderLayer(new AttackPreviewRenderLayer(this));
    }

    private class AttackPreviewRenderLayer extends GeoRenderLayer<PiwiEntity> {
        public AttackPreviewRenderLayer(GeoRenderer<PiwiEntity> entityRendererIn) {
            super(entityRendererIn);
        }

        @Override
        public void render(MatrixStack poseStack, PiwiEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
            super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);

            Vec3d pos = new Vec3d(animatable.getPos().x, animatable.getPos().y, animatable.getPos().z);

            var rotationVector = animatable.getRotationVector();
            var angle = Math.atan2(rotationVector.z, rotationVector.x);
            var delta = 0.3;
            var range = 4;
            var target1 = new Vec3d(Math.cos(angle - delta), 0, Math.sin(angle - delta)).multiply(range);
            var target2 = new Vec3d(Math.cos(angle + delta), 0, Math.sin(angle + delta)).multiply(range);

            Renderer3d.renderThroughWalls();
            Renderer3d.renderLine(poseStack, Color.RED, pos, pos.add(target1));
            Renderer3d.renderLine(poseStack, Color.RED, pos, pos.add(target2));
            Renderer3d.stopRenderThroughWalls();
        }
    }
}
