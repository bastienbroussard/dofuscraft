package com.dofuscraft.registry;

import com.dofuscraft.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class DofusItemRegistry {
    // hats
    public static final DofusEquipmentItem YELLOW_PIWI_HAT = register("yellow_piwi_hat", new DofusEquipmentItem(
            DofusEquipmentType.HAT,
            7,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.EARTH_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem RED_PIWI_HAT = register("red_piwi_hat", new DofusEquipmentItem(
            DofusEquipmentType.HAT,
            12,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.WISDOM, 7, 10),
                    new EquipmentEffect(Characteristic.FIRE_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem BLUE_PIWI_HAT = register("blue_piwi_hat", new DofusEquipmentItem(
            DofusEquipmentType.HAT,
            11,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.CHANCE, 16, 20),
                    new EquipmentEffect(Characteristic.CRITICAL, 2, 3)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem GREEN_PIWI_HAT = register("green_piwi_hat", new DofusEquipmentItem(
            DofusEquipmentType.HAT,
            8,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.SUMMON, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // capes
    public static final DofusEquipmentItem YELLOW_PIWI_CAPE = register("yellow_piwi_cape", new DofusEquipmentItem(
            DofusEquipmentType.CLOAK,
            9,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.STRENGTH, 16, 20),
                    new EquipmentEffect(Characteristic.EARTH_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem RED_PIWI_CAPE = register("red_piwi_cape", new DofusEquipmentItem(
            DofusEquipmentType.CLOAK,
            8,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.SUMMON, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem BLUE_PIWI_CAPE = register("blue_piwi_cape", new DofusEquipmentItem(
            DofusEquipmentType.CLOAK,
            7,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.WATER_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem GREEN_PIWI_CAPE = register("green_piwi_cape", new DofusEquipmentItem(
            DofusEquipmentType.CLOAK,
            10,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.AGILITY, 16, 20),
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // amulets
    public static final DofusEquipmentItem YELLOW_PIWI_AMULET = register("yellow_piwi_amulet", new DofusEquipmentItem(
            DofusEquipmentType.AMULET,
            12,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.WISDOM, 7, 10),
                    new EquipmentEffect(Characteristic.EARTH_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
    public static final DofusEquipmentItem RED_PIWI_AMULET = register("red_piwi_amulet", new DofusEquipmentItem(
            DofusEquipmentType.AMULET,
            11,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.INTELLIGENCE, 16, 20),
                    new EquipmentEffect(Characteristic.CRITICAL, 2, 3)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
    public static final DofusEquipmentItem BLUE_PIWI_AMULET = register("blue_piwi_amulet", new DofusEquipmentItem(
            DofusEquipmentType.AMULET,
            10,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.CHANCE, 16, 20),
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
    public static final DofusEquipmentItem GREEN_PIWI_AMULET = register("green_piwi_amulet", new DofusEquipmentItem(
            DofusEquipmentType.AMULET,
            7,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.AIR_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));

    // rings
    public static final DofusEquipmentItem YELLOW_PIWI_RING = register("yellow_piwi_ring", new DofusEquipmentItem(
            DofusEquipmentType.RING,
            11,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.STRENGTH, 16, 20),
                    new EquipmentEffect(Characteristic.CRITICAL, 2, 3)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
    public static final DofusEquipmentItem RED_PIWI_RING = register("red_piwi_ring", new DofusEquipmentItem(
            DofusEquipmentType.RING,
            10,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.INTELLIGENCE, 16, 20),
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
    public static final DofusEquipmentItem BLUE_PIWI_RING = register("blue_piwi_ring", new DofusEquipmentItem(
            DofusEquipmentType.RING,
            9,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.CHANCE, 16, 20),
                    new EquipmentEffect(Characteristic.WATER_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
    public static final DofusEquipmentItem GREEN_PIWI_RING = register("green_piwi_ring", new DofusEquipmentItem(
            DofusEquipmentType.RING,
            12,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.WISDOM, 7, 10),
                    new EquipmentEffect(Characteristic.AIR_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD));

    // belts
    public static final DofusEquipmentItem YELLOW_PIWI_BELT = register("yellow_piwi_belt", new DofusEquipmentItem(
            DofusEquipmentType.BELT,
            8,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.SUMMON, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem RED_PIWI_BELT = register("red_piwi_belt", new DofusEquipmentItem(
            DofusEquipmentType.BELT,
            7,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.FIRE_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem BLUE_PIWI_BELT = register("blue_piwi_belt", new DofusEquipmentItem(
            DofusEquipmentType.BELT,
            12,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.WISDOM, 7, 10),
                    new EquipmentEffect(Characteristic.WATER_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem GREEN_PIWI_BELT = register("green_piwi_belt", new DofusEquipmentItem(
            DofusEquipmentType.BELT,
            9,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.AGILITY, 16, 20),
                    new EquipmentEffect(Characteristic.AIR_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // boots
    public static final DofusEquipmentItem YELLOW_PIWI_SANDALS = register("yellow_piwi_sandals", new DofusEquipmentItem(
            DofusEquipmentType.BOOTS,
            10,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.STRENGTH, 16, 20),
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem RED_PIWI_SANDALS = register("red_piwi_sandals", new DofusEquipmentItem(
            DofusEquipmentType.BOOTS,
            9,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.INTELLIGENCE, 16, 20),
                    new EquipmentEffect(Characteristic.FIRE_DAMAGE, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem BLUE_PIWI_SANDALS = register("blue_piwi_sandals", new DofusEquipmentItem(
            DofusEquipmentType.BOOTS,
            8,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.VITALITY, 16, 20),
                    new EquipmentEffect(Characteristic.SUMMON, 1, 1)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
    public static final DofusEquipmentItem GREEN_PIWI_SANDALS = register("green_piwi_sandals", new DofusEquipmentItem(
            DofusEquipmentType.BOOTS,
            11,
            Arrays.asList(
                    new EquipmentEffect(Characteristic.AGILITY, 16, 20),
                    new EquipmentEffect(Characteristic.CRITICAL, 2, 3)
            ),
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));

    // drops
    public static final Item YELLOW_PIWI_FEATHER = register("yellow_piwi_feather", new Item(new FabricItemSettings()));
    public static final Item RED_PIWI_FEATHER = register("red_piwi_feather", new Item(new FabricItemSettings()));
    public static final Item BLUE_PIWI_FEATHER = register("blue_piwi_feather", new Item(new FabricItemSettings()));
    public static final Item GREEN_PIWI_FEATHER = register("green_piwi_feather", new Item(new FabricItemSettings()));

    // spells
    public static final Item SPELLITEM_HAND = register("hand", new SpellItem());

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
