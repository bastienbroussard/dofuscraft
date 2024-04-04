package com.dofuscraft.mixin.client;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.DofusEquipmentItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.UUID;

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
                        var nbt = stack.getOrCreateSubNbt("effects");
                        var random = new Random();
                        for (var effect : equipmentItem.getEffects()) {
                            String identifier = effect.getEffect().getName();
                            int value = random.nextInt(effect.getMaxValue() - effect.getMinValue() + 1) + effect.getMinValue();

                            stack.addAttributeModifier(
                                    DofusCraft.WISDOM,
                                    new EntityAttributeModifier("prout", value, EntityAttributeModifier.Operation.ADDITION),
                                    null);

                            nbt.putInt(identifier, value);
                        }
                    }
                }
            }
        }
    }
}
