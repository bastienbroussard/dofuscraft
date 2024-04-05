package com.dofuscraft.mixin;

import com.dofuscraft.DofusEquipmentItem;
import com.dofuscraft.DofusEquipmentType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public abstract class ArmorStandEntityMixin {
    @Shadow public abstract void setShowArms(boolean showArms);

    @Inject(at = @At("HEAD"), method = "interactAt", cancellable = true)
    public void interactAt(PlayerEntity player, Vec3d hitPos, Hand hand, CallbackInfoReturnable<ActionResult> callback) {
        if (hand != Hand.MAIN_HAND) {
            callback.setReturnValue(ActionResult.PASS);
            return;
        }

        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isEmpty()) {
            // hand is empty: try to retrieve armor from armor stand
            var potentialTypes = this.dofusCraft$getArmorItemFromHitPos(hitPos);
            boolean unequipped = false;
            for (var potentialType : potentialTypes) {
                if (this.armorItems.get(potentialType.getId()) != ItemStack.EMPTY) {
                    this.dofusCraft$unequip(player, potentialType, itemStack);
                    unequipped = true;
                    break;
                }
            }
            callback.setReturnValue(unequipped ? ActionResult.SUCCESS : ActionResult.FAIL);
        } else {
            // hand contains something: try to put armor in hand onto armor stand
            var item = itemStack.getItem();
            if (item instanceof DofusEquipmentItem equipmentItem) {
                if (this.armorItems.get(equipmentItem.getType().getId()) != ItemStack.EMPTY) {
                    // armor stand does already have an item of this type
                    callback.setReturnValue(ActionResult.FAIL);
                }
                else {
                    // armor stand does not have an item of this type: we can safely equip it
                    this.dofusCraft$equip(player, equipmentItem.getType(), itemStack);
                    callback.setReturnValue(ActionResult.SUCCESS);
                }
            } else {
                // item in hand is not an equipment item: does nothing
                callback.setReturnValue(ActionResult.FAIL);
            }
        }
    }

    @Unique
    private DofusEquipmentType[] dofusCraft$getArmorItemFromHitPos(Vec3d hitPos) {
        if (hitPos.y < 0.6) {
            return new DofusEquipmentType[] { DofusEquipmentType.BOOTS };
        }
        else if (hitPos.y < 1.2) {
            return new DofusEquipmentType[] { DofusEquipmentType.RING, DofusEquipmentType.BELT, DofusEquipmentType.CLOAK };
        }
        else if (hitPos.y < 1.6) {
            return new DofusEquipmentType[] { DofusEquipmentType.AMULET };
        }
        else {
            return new DofusEquipmentType[] { DofusEquipmentType.HAT };
        }
    }

    @Unique
    private void dofusCraft$equip(PlayerEntity player, DofusEquipmentType equipmentType, ItemStack handStack) {
        // put item on armor stand
        var item = (DofusEquipmentItem) handStack.getItem();
        this.armorItems.set(equipmentType.getId(), handStack);

        // show arms if item is a ring
        if (equipmentType == DofusEquipmentType.RING) {
            this.setShowArms(true);
        }

        // remove item from player hand
        player.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);

        // play sound an emit game event
        var entity = ((ArmorStandEntity)(Object)this);
        if (!entity.getWorld().isClient() && !entity.isSpectator()) {
            entity.getWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    item.getEquipSound(), entity.getSoundCategory(), 1.0f, 1.0f);
            entity.emitGameEvent(GameEvent.EQUIP);
        }
    }

    @Unique
    private void dofusCraft$unequip(PlayerEntity player, DofusEquipmentType equipmentType, ItemStack handStack) {
        // remove item from armor stand
        var armorStandStack = this.armorItems.get(equipmentType.getId());
        this.armorItems.set(equipmentType.getId(), handStack);

        // hide arms if item is a ring
        if (equipmentType == DofusEquipmentType.RING) {
            this.setShowArms(false);
        }

        // add item to player hand
        player.setStackInHand(Hand.MAIN_HAND, armorStandStack);

        // emit game event
        var entity = ((ArmorStandEntity)(Object)this);
        entity.emitGameEvent(GameEvent.UNEQUIP);
    }

    @Shadow @Final private final DefaultedList<ItemStack> armorItems = DefaultedList.ofSize(7, ItemStack.EMPTY);
}
