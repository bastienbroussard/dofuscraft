package com.dofuscraft;

import com.dofuscraft.entity.PiwiEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animatable.GeoEntity;

public class DofusEntityRegister {
    public static final EntityType<PiwiEntity> PIWI = register("piwi", 0.75f, 0.75f, PiwiEntity::new);

    private static <E extends MobEntity & GeoEntity> EntityType<E> register(
            String name, float width, float height, EntityType.EntityFactory<E> entityType) {
        return Registry.register(Registries.ENTITY_TYPE,
                new Identifier(DofusCraft.MOD_ID, name),
                FabricEntityTypeBuilder
                        .create(SpawnGroup.CREATURE, entityType).dimensions(EntityDimensions.fixed(width, height))
                        .build());
    }
}
