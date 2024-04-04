package com.dofuscraft;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class DofusItemRegistry {
    // hats
    public static final DofusEquipmentItem RED_PIWI_HAT = register("red_piwi_hat", new DofusEquipmentItem(
            DofusEquipmentItem.Type.HAT,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.WISDOM, 7, 10),
                    new EquipmentEffect(Characteristic.FIRE_DAMAGE, 1, 1)
            )));

    public static final DofusEquipmentItem BLUE_PIWI_HAT = register("blue_piwi_hat", new DofusEquipmentItem(
            DofusEquipmentItem.Type.HAT,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // capes
    public static final DofusEquipmentItem RED_PIWI_CAPE = register("red_piwi_cape", new DofusEquipmentItem(
            DofusEquipmentItem.Type.CLOAK,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // amulets
    public static final DofusEquipmentItem RED_PIWI_AMULET = register("red_piwi_amulet", new DofusEquipmentItem(
            DofusEquipmentItem.Type.AMULET,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));

    // rings
    public static final DofusEquipmentItem RED_PIWI_RING = register("red_piwi_ring", new DofusEquipmentItem(
            DofusEquipmentItem.Type.RING,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));

    // belts
    public static final DofusEquipmentItem RED_PIWI_BELT = register("red_piwi_belt", new DofusEquipmentItem(
            DofusEquipmentItem.Type.BELT,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // boots
    public static final DofusEquipmentItem RED_PIWI_SANDALS = register("red_piwi_sandals", new DofusEquipmentItem(
            DofusEquipmentItem.Type.BOOTS,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // drops
    public static final Item RED_PIWI_FEATHER = register("red_piwi_feather", new Item(new FabricItemSettings()));

    private static DofusEquipmentItem register(String name, DofusEquipmentItem item) {
        return Registry.register(
                Registries.ITEM,
                new Identifier(DofusCraft.MOD_ID, name),
                item);
    }

    private static Item register(String name, Item item) {
        return Registry.register(
                Registries.ITEM,
                new Identifier(DofusCraft.MOD_ID, name),
                item);
    }
}
