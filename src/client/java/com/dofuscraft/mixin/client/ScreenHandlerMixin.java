package com.dofuscraft.mixin.client;

import com.dofuscraft.DofusEquipmentItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Environment(EnvType.CLIENT)
@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {
    @Inject(at = @At("HEAD"), method = "onSlotClick")
    public void getStack(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo callback) {
        if ((ScreenHandler)(Object)this instanceof CraftingScreenHandler craftingScreenHandler) {
            int resultSlotIndex = craftingScreenHandler.getCraftingResultSlotIndex();
            if (slotIndex == resultSlotIndex) {
                var resultSlot = craftingScreenHandler.getSlot(resultSlotIndex);
                if (resultSlot.hasStack()) {
                    var stack = resultSlot.getStack();
                    var item = stack.getItem();
                    if (item instanceof DofusEquipmentItem equipmentItem) {
                        var random = new Random();
                        var nbt = stack.getOrCreateSubNbt("effects");
                        for (var effect : equipmentItem.getEffects()) {
                            int value = random.nextInt(effect.getMaxValue() - effect.getMinValue() + 1) + effect.getMinValue();
                            nbt.putInt(effect.getEffect().getName(), value);
                        }
                    }
                }
            }
        }
    }
}
