package com.dofuscraft.mixin.client;

import com.dofuscraft.DofusEquipmentItem;
import com.dofuscraft.DofusEquipmentSlot;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PlayerScreenHandler.class)
public abstract class PlayerScreenHandlerMixin extends AbstractRecipeScreenHandler<RecipeInputInventory> {
	public PlayerScreenHandlerMixin() {
		super(null, 0);
	}

	@Inject(at = @At("TAIL"), method = "<init>")
	public void PlayerScreenHandler(PlayerInventory inventory, boolean onServer, final PlayerEntity owner, CallbackInfo callBack) {
		// disable crafting slots, armor slots and offhand slot
		for (int i = this.slots.size() - 1; i >= 0; i--) {
			var slot = this.slots.get(i);
			if ((slot.id >= 0 && slot.id <= 8) || (slot.id == 45)) {
				this.slots.remove(i);
				this.slots.add(i, new Slot(inventory, 80, slot.x, slot.y) {
					@Override
					public boolean isEnabled() {
						return false;
					}
				});
			}
		}

		// create custom slots
		this.addSlot(new DofusEquipmentSlot(inventory, owner, 36, 8, 8, DofusEquipmentItem.Type.AMULET));
		this.addSlot(new DofusEquipmentSlot(inventory, owner, 37, 8, 26, DofusEquipmentItem.Type.RING));
		this.addSlot(new DofusEquipmentSlot(inventory, owner, 38, 8, 44, DofusEquipmentItem.Type.RING));
		this.addSlot(new DofusEquipmentSlot(inventory, owner, 39, 77, 8, DofusEquipmentItem.Type.HAT));
		this.addSlot(new DofusEquipmentSlot(inventory, owner, 40, 77, 26, DofusEquipmentItem.Type.CLOAK));
		this.addSlot(new DofusEquipmentSlot(inventory, owner, 41, 77, 44, DofusEquipmentItem.Type.BELT));
		this.addSlot(new DofusEquipmentSlot(inventory, owner, 42, 77, 62, DofusEquipmentItem.Type.BOOTS));
	}

	@Inject(at = @At("HEAD"), method = "quickMove", cancellable = true)
	public void quickMove(PlayerEntity player, int slot, CallbackInfoReturnable<ItemStack> callback) {
		Slot currentSlot = this.slots.get(slot);
		if (currentSlot.hasStack() && (currentSlot.id < 46 || currentSlot.id > 52)) {
			ItemStack currentStack = currentSlot.getStack();
			for (int i = 46; i <= 52; i++) {
				var targetSlot = this.slots.get(i);
				if (!targetSlot.hasStack() && targetSlot.canInsert(currentStack)) {
					targetSlot.insertStack(currentStack);
					currentSlot.onTakeItem(player, currentStack);
					callback.setReturnValue(currentStack);
				}
			}
		}
	}
}