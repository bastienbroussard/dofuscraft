package com.dofuscraft;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import software.bernie.geckolib.network.GeckoLibNetwork;

@Environment(EnvType.CLIENT)
public class DofusCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		DofusEquipmentRendererRegistry.registerAll();
		DofusEntityRendererRegistry.registerAll();
		GeckoLibNetwork.registerClientReceiverPackets();

		WorldRenderEvents.END.register(context -> {
			var player = MinecraftClient.getInstance().player;
			if (player == null) {
				return;
			}

			var telegraphEntity = ((SpellTelegraphAccessor)player).dofuscraft$getSpellTelegraph();
			if (telegraphEntity == null) {
				return;
			}

			var camera = context.camera();
			var targetPosition = telegraphEntity.getPos();
			var transformedPosition = targetPosition.subtract(camera.getPos());

			MatrixStack matrixStack = new MatrixStack();
			matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
			matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180.0F));
			matrixStack.translate(transformedPosition.x, transformedPosition.y, transformedPosition.z);

			Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
			Tessellator tessellator = Tessellator.getInstance();

			BufferBuilder buffer = tessellator.getBuffer();

			buffer.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);
			buffer.vertex(positionMatrix, 0, 0.02f, 0).color(0f, 1f, 0f, 1f).next();
			for (double i = Math.PI; i >= -Math.PI; i -= Math.PI / 20) {
				var target = new Vec3d(Math.cos(i), 0.02f, Math.sin(i)).multiply(0.5).toVector3f();
				buffer.vertex(positionMatrix, target.x, target.y, target.z).color(0f, 1f, 0f, 0.5f).next();
			}

			RenderSystem.setShader(GameRenderer::getPositionColorProgram);
			RenderSystem.disableCull();
			RenderSystem.enableBlend();

			tessellator.draw();

			RenderSystem.disableBlend();
			RenderSystem.enableCull();
		});
	}
}