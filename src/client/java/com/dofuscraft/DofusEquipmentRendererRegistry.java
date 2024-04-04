package com.dofuscraft;

import com.dofuscraft.models.equipment.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import oshi.util.tuples.Pair;

import java.util.*;

@Environment(EnvType.CLIENT)
public class DofusEquipmentRendererRegistry {
    private static final Map<Item, List<DofusEquipmentRenderer>> RENDERERS = new HashMap<>();

    public static void registerAll() {
        // hats
        register(DofusItemRegistry.RED_PIWI_HAT, new DofusEquipmentRenderer(
                "red_piwi_hat", new PiwiHatModel(PiwiHatModel.getTexturedModelData().createModel())));
        register(DofusItemRegistry.BLUE_PIWI_HAT, new DofusEquipmentRenderer(
                "blue_piwi_hat", new PiwiHatModel(PiwiHatModel.getTexturedModelData().createModel())));

        // cloaks
        register(DofusItemRegistry.RED_PIWI_CAPE, new DofusEquipmentRenderer(
                "red_piwi_cape", new PiwiCapeModel(PiwiCapeModel.getTexturedModelData().createModel())));

        // amulets
        register(DofusItemRegistry.RED_PIWI_AMULET, new DofusEquipmentRenderer(
                "red_piwi_amulet", new PiwiAmuletModel(PiwiAmuletModel.getTexturedModelData().createModel())));

        // rings
        register(DofusItemRegistry.RED_PIWI_RING, new DofusEquipmentRenderer(
                "red_piwi_ring", new PiwiRingModel(PiwiRingModel.getTexturedModelData().createModel())));
        register(DofusItemRegistry.RED_PIWI_RING, new DofusEquipmentRenderer(
                "red_piwi_ring_slim", new PiwiRingModel(PiwiRingModel.getSlimTexturedModelData().createModel())));

        // belts
        register(DofusItemRegistry.RED_PIWI_BELT, new DofusEquipmentRenderer(
                "red_piwi_belt", new PiwiBeltModel(PiwiBeltModel.getTexturedModelData().createModel())));

        // boots
        register(DofusItemRegistry.RED_PIWI_SANDALS, new DofusEquipmentRenderer(
                "red_piwi_sandals", new PiwiSandalsModel(PiwiSandalsModel.getTexturedModelData().createModel())));
    }

    private static void register(Item item, DofusEquipmentRenderer renderer) {
        if (RENDERERS.containsKey(item)) {
            var renderers = RENDERERS.get(item);
            renderers.add(renderer);
        }
        else {
            var renderers = new ArrayList<DofusEquipmentRenderer>();
            renderers.add(renderer);
            RENDERERS.put(item, renderers);
        }
    }

    public static DofusEquipmentRenderer getRenderer(Item item, boolean isSlim) {
        if (RENDERERS.containsKey(item)) {
            var renderers = RENDERERS.get(item);
            if (isSlim && renderers.size() == 2) {
                return renderers.get(1);
            }
            else {
                return renderers.get(0);
            }
        }
        else {
            return null;
        }
    }
}
