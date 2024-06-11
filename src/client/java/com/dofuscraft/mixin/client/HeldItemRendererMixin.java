package com.dofuscraft.mixin.client;

import com.dofuscraft.SpellItem;
import com.dofuscraft.SpellTelegraphAccessor;
import com.dofuscraft.entity.TelegraphEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @Inject(at = @At("HEAD"), method = "renderFirstPersonItem", cancellable = true)
    public void renderFirstPersonItem(
            AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress,
            ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo callback) {
        if (item.getItem() instanceof SpellItem) {
            // set spell telegraph
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.cameraEntity != null && client.world != null) {
                HitResult hit = client.cameraEntity.raycast(1000, tickDelta, false);
                switch (hit.getType()) {
                    case HitResult.Type.MISS:
                        //nothing near enough
                        break;
                    case HitResult.Type.BLOCK:
                        var telegraphEntity = ((SpellTelegraphAccessor)player).dofuscraft$getSpellTelegraph();
                        if (telegraphEntity == null) {
                            telegraphEntity = new TelegraphEntity(player.clientWorld, player);
                            ((SpellTelegraphAccessor)player).dofuscraft$setSpellTelegraph(telegraphEntity);
                            player.clientWorld.spawnEntity(telegraphEntity);
                        }

                        telegraphEntity.updatePosition(hit.getPos().x, (int)hit.getPos().y, hit.getPos().z);
                        break;
                    case HitResult.Type.ENTITY:
                        EntityHitResult entityHit = (EntityHitResult) hit;
                        Entity entity = entityHit.getEntity();
                        break;
                }
            }

            // disabled item rendering in main hand
            boolean isMainHand = hand == Hand.MAIN_HAND;
            Arm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            matrices.push();
            if (isMainHand && !player.isInvisible()) {
                this.renderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                callback.cancel();
            }
        }
        else if (hand == Hand.MAIN_HAND) {
            ((SpellTelegraphAccessor)player).dofuscraft$setSpellTelegraph(null);
        }
    }

    @Shadow protected abstract void renderArmHoldingItem(
            MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float equipProgress, float swingProgress, Arm arm);
}
