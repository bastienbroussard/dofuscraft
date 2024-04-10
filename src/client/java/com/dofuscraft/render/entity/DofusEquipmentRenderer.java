package com.dofuscraft.render.entity;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.DofusEquipmentType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class DofusEquipmentRenderer {
    private final Identifier texture;
    private final BipedEntityModel<LivingEntity> model;

    public DofusEquipmentRenderer(String textureName, BipedEntityModel<LivingEntity> model) {
        this.texture = new Identifier(DofusCraft.MOD_ID, "textures/entity/equipment/" + textureName + ".png");
        this.model = model;
    }

    public void render(DofusEquipmentType type, boolean leftArm, EntityModel<? extends LivingEntity> contextModel,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity,
                       float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (type == DofusEquipmentType.RING) {
            if (leftArm) {
                this.model.leftArm.visible = true;
                this.model.rightArm.visible = false;
            }
            else {
                this.model.rightArm.visible = true;
                this.model.leftArm.visible = false;
            }
        }

        if (type == DofusEquipmentType.AMULET) {
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.texture));
            followBodyRotations(entity, this.model);
            matrices.translate(-0.015f, 0f, -0.1f);
            matrices.scale(0.5f, 0.5f, 0.5f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }

        if (type == DofusEquipmentType.HAT || type == DofusEquipmentType.BELT || type == DofusEquipmentType.RING) {
            this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, animationProgress, headPitch);
            this.model.animateModel(entity, limbAngle, limbDistance, tickDelta);
            followBodyRotations(entity, this.model);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.texture));
            matrices.scale(1.2f, 1.2f, 1.2f);
            this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }
        else if (type == DofusEquipmentType.BOOTS) {
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.texture));
            followBodyRotations(entity, this.model);
            matrices.translate(0f, -0.15f, 0.02f);
            matrices.scale(1.1f, 1.1f, 1.1f);
            this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }
        else if (type == DofusEquipmentType.CLOAK) {
            followBodyRotations(entity, this.model);
            matrices.translate(0.0f, 0.0f, 0.17f);
            matrices.scale(1.1f, 1.1f, 1.1f);

            if (entity instanceof PlayerEntity playerEntity) {
                double d = MathHelper.lerp(tickDelta, playerEntity.prevCapeX, playerEntity.capeX) - MathHelper.lerp(tickDelta, playerEntity.prevX, playerEntity.getX());
                double e = MathHelper.lerp(tickDelta, playerEntity.prevCapeY, playerEntity.capeY) - MathHelper.lerp(tickDelta, playerEntity.prevY, playerEntity.getY());
                double m = MathHelper.lerp(tickDelta, playerEntity.prevCapeZ, playerEntity.capeZ) - MathHelper.lerp(tickDelta, playerEntity.prevZ, playerEntity.getZ());
                float n = MathHelper.lerpAngleDegrees(tickDelta, playerEntity.prevBodyYaw, playerEntity.bodyYaw);
                double o = MathHelper.sin(n * ((float)Math.PI / 180));
                double p = -MathHelper.cos(n * ((float)Math.PI / 180));
                float q = (float)e * 10.0f;
                q = MathHelper.clamp(q, -6.0f, 32.0f);
                float r = (float)(d * o + m * p) * 100.0f;
                r = MathHelper.clamp(r, 0.0f, 150.0f);
                float s = (float)(d * p - m * o) * 100.0f;
                s = MathHelper.clamp(s, -20.0f, 20.0f);
                if (r < 0.0f) {
                    r = 0.0f;
                }
                float t = MathHelper.lerp(tickDelta, playerEntity.prevStrideDistance, playerEntity.strideDistance);
                q += MathHelper.sin(MathHelper.lerp(tickDelta, playerEntity.prevHorizontalSpeed, playerEntity.horizontalSpeed) * 6.0f) * 32.0f * t;
                if (playerEntity.isInSneakingPose()) {
                    q += 25.0f;
                }
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(6.0f + r / 2.0f + q));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(s / 2.0f));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - s / 2.0f));
            }

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.texture));
            this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }
    }

    private void updateCapeAngles(PlayerEntity entity) {
        entity.prevCapeX = entity.capeX;
        entity.prevCapeY = entity.capeY;
        entity.prevCapeZ = entity.capeZ;
        double d = entity.getX() - entity.capeX;
        double e = entity.getY() - entity.capeY;
        double f = entity.getZ() - entity.capeZ;
        double g = 10.0;
        if (d > 10.0) {
            entity.prevCapeX = entity.capeX = entity.getX();
        }
        if (f > 10.0) {
            entity.prevCapeZ = entity.capeZ = entity.getZ();
        }
        if (e > 10.0) {
            entity.prevCapeY = entity.capeY = entity.getY();
        }
        if (d < -10.0) {
            entity.prevCapeX = entity.capeX = entity.getX();
        }
        if (f < -10.0) {
            entity.prevCapeZ = entity.capeZ = entity.getZ();
        }
        if (e < -10.0) {
            entity.prevCapeY = entity.capeY = entity.getY();
        }
        entity.capeX += d * 0.25;
        entity.capeZ += f * 0.25;
        entity.capeY += e * 0.25;
    }

    @SuppressWarnings("unchecked")
    static void followBodyRotations(final LivingEntity entity, final BipedEntityModel<LivingEntity> model) {

        EntityRenderer<? super LivingEntity> render = MinecraftClient.getInstance()
                .getEntityRenderDispatcher().getRenderer(entity);

        if (render instanceof LivingEntityRenderer) {
            LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer =
                    (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> entityModel = livingRenderer.getModel();

            if (entityModel instanceof BipedEntityModel<LivingEntity> bipedModel) {
                bipedModel.copyBipedStateTo(model);
            }
        }
    }
}
