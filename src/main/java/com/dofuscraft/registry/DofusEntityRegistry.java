package com.dofuscraft.registry;

import com.dofuscraft.DofusCraft;
import com.dofuscraft.entity.PiwiEntity;
import com.dofuscraft.entity.TelegraphEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animatable.GeoEntity;

public class DofusEntityRegistry {
    public static final EntityType<TelegraphEntity> TELEGRAPH = register("telegraph", 1, 1, TelegraphEntity::new, SpawnGroup.MISC);
    public static final EntityType<PiwiEntity> PIWI = register("piwi", 0.75f, 0.75f, PiwiEntity::new, SpawnGroup.CREATURE);

    private static <E extends Entity> EntityType<E> register(
            String name, float width, float height, EntityType.EntityFactory<E> entityType, SpawnGroup spawnGroup) {
        return Registry.register(Registries.ENTITY_TYPE,
                new Identifier(DofusCraft.MOD_ID, name),
                FabricEntityTypeBuilder
                        .create(spawnGroup, entityType)
                        .dimensions(EntityDimensions.fixed(width, height))
                        .build());
    }
}
