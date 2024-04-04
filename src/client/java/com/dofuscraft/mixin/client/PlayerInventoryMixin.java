package com.dofuscraft.mixin.client;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    public void PlayerInventory(PlayerEntity player, CallbackInfo callBack) {
        this.armor = DefaultedList.ofSize(7, ItemStack.EMPTY);
        this.combinedInventory = ImmutableList.of(this.main, this.armor, this.offHand);
    }

    @Final @Shadow public DefaultedList<ItemStack> main;

    @Final @Mutable @Shadow public DefaultedList<ItemStack> armor;

    @Final @Shadow public DefaultedList<ItemStack> offHand;

    @Final @Mutable @Shadow private List<DefaultedList<ItemStack>> combinedInventory;
}
