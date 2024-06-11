package com.dofuscraft.entity;

import com.dofuscraft.registry.DofusEntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class TelegraphEntity extends Entity implements Ownable {
    public static final TrackedData<Vector3f> OWNER_ROTATION_VECTOR;
    public static final TrackedData<Integer> LIFESPAN;

    private final int lifespan;

    private LivingEntity owner;
    private int life = 0;

    public TelegraphEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
        this.lifespan = 100;
    }

    public TelegraphEntity(World world, LivingEntity owner) {
        super(DofusEntityRegistry.TELEGRAPH, world);
        this.owner = owner;
        this.lifespan = -1; // infinite lifespan

        this.dataTracker.set(OWNER_ROTATION_VECTOR, this.owner.getRotationVector().toVector3f());
        this.dataTracker.set(LIFESPAN, this.lifespan);
    }

    public TelegraphEntity(World world, LivingEntity owner, float lifespan) {
        super(DofusEntityRegistry.TELEGRAPH, world);
        this.owner = owner;
        this.lifespan = (int) (lifespan / 0.05);
        this.setPosition(this.owner.getPos());

        this.dataTracker.set(OWNER_ROTATION_VECTOR, this.owner.getRotationVector().toVector3f());
        this.dataTracker.set(LIFESPAN, this.lifespan);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(OWNER_ROTATION_VECTOR, new Vector3f());
        this.dataTracker.startTracking(LIFESPAN, 0);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.lifespan != -1) {
            if (!this.getWorld().isClient) {
                // destroy telegraph after lifespan has been achieved
                if (this.life >= this.lifespan) {
                    this.discard();
                }
            }

            this.life++;
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return this.owner;
    }

    public int getLife() {
        return this.life;
    }

    static {
        OWNER_ROTATION_VECTOR = DataTracker.registerData(TelegraphEntity.class, TrackedDataHandlerRegistry.VECTOR3F);
        LIFESPAN = DataTracker.registerData(TelegraphEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}
