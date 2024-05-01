package com.dofuscraft.render.entity;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.TelegraphEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import static com.dofuscraft.entity.TelegraphEntity.*;
import static net.minecraft.client.render.RenderLayer.DEFAULT_BUFFER_SIZE;

@Environment(EnvType.CLIENT)
public class TelegraphEntityRenderer extends EntityRenderer<TelegraphEntity> {
    public TelegraphEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(TelegraphEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        var renderLayer = RenderLayer.of("triangle", VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLE_FAN,
                DEFAULT_BUFFER_SIZE, RenderLayer.of(RenderLayer.COLOR_PROGRAM));
        var buffer = vertexConsumers.getBuffer(renderLayer);
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();

        var rotationVector = entity.getDataTracker().get(OWNER_ROTATION_VECTOR);
        var angle = Math.atan2(rotationVector.z, rotationVector.x);
        var delta = 0.5;
        var range = 2.5;
        var alpha = entity.getLife() / (float) entity.getDataTracker().get(LIFESPAN);

        buffer.vertex(positionMatrix, 0, 0.01f, 0).color(1f, 0f, 0f, Math.clamp(alpha + 0.5f, 0f, 1f)).next();
        for (double i = delta; i >= -delta; i -= 0.1) {
            var target = new Vec3d(Math.cos(angle + i), 0.01f, Math.sin(angle + i)).multiply(range).toVector3f();
            buffer.vertex(positionMatrix, target.x, target.y, target.z).color(1f, 0f, 0f, alpha).next();
        }

        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(TelegraphEntity entity) {
        return null;
    }
}
