package com.dofuscraft;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.event.GameEvent;

import java.util.Iterator;

public class DofusEquipmentSlot extends Slot {
    public static final Identifier EMPTY_HAT_SLOT_TEXTURE = new Identifier(DofusCraft.MOD_ID, "item/empty_armor_slot_hat");
    public static final Identifier EMPTY_CLOAK_SLOT_TEXTURE = new Identifier(DofusCraft.MOD_ID, "item/empty_armor_slot_cloak");
    public static final Identifier EMPTY_AMULET_SLOT_TEXTURE = new Identifier(DofusCraft.MOD_ID, "item/empty_armor_slot_amulet");
    public static final Identifier EMPTY_RING_SLOT_TEXTURE = new Identifier(DofusCraft.MOD_ID, "item/empty_armor_slot_ring");
    public static final Identifier EMPTY_BELT_SLOT_TEXTURE = new Identifier(DofusCraft.MOD_ID, "item/empty_armor_slot_belt");
    public static final Identifier EMPTY_BOOTS_SLOT_TEXTURE = new Identifier(DofusCraft.MOD_ID, "item/empty_armor_slot_boots");

    private final PlayerEntity owner;

    @Override
    public String toString() {
        return super.toString();
    }

    private final DofusEquipmentType slotType;

    public DofusEquipmentSlot(Inventory inventory, PlayerEntity owner, int index, int x, int y, DofusEquipmentType slotType) {
        super(inventory, index, x, y);
        this.owner = owner;
        this.slotType = slotType;
    }

    @Override
    public void setStack(ItemStack stack, ItemStack previousStack) {
        super.setStack(stack, previousStack);
        if (this.hasStack()) {
            var item = (DofusEquipmentItem) stack.getItem();
            this.owner.getWorld().playSound(null, this.owner.getX(), this.owner.getY(), this.owner.getZ(),
                    item.getEquipSound(), this.owner.getSoundCategory(), 1.0f, 1.0f);
            this.owner.emitGameEvent(GameEvent.EQUIP);
        }
        else {
            this.owner.emitGameEvent(GameEvent.UNEQUIP);
        }
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        var item = stack.getItem();
        if (item instanceof DofusEquipmentItem dofusEquipmentItem) {
            return dofusEquipmentItem.getType() == this.slotType;
        }
        else {
            return false;
        }
    }

    @Override
    public Pair<Identifier, Identifier> getBackgroundSprite() {
        Identifier textureIdentifier = null;
        switch (this.slotType) {
            case HAT -> textureIdentifier = EMPTY_HAT_SLOT_TEXTURE;
            case CLOAK -> textureIdentifier = EMPTY_CLOAK_SLOT_TEXTURE;
            case AMULET -> textureIdentifier = EMPTY_AMULET_SLOT_TEXTURE;
            case RING -> textureIdentifier = EMPTY_RING_SLOT_TEXTURE;
            case BELT -> textureIdentifier = EMPTY_BELT_SLOT_TEXTURE;
            case BOOTS -> textureIdentifier = EMPTY_BOOTS_SLOT_TEXTURE;
        }
        return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, textureIdentifier);
    }
}
