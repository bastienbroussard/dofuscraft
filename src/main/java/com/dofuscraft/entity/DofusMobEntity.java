package com.dofuscraft.entity;

import com.dofuscraft.entity.ai.DofusEntityBrain;
import com.dofuscraft.entity.ai.EntityEvent;
import com.dofuscraft.entity.ai.EntityState;
import com.dofuscraft.entity.ai.MemoryCell;
import com.dofuscraft.registry.DofusTrackedDataHandlerRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import net.tslat.smartbrainlib.util.BrainUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class DofusMobEntity extends PathAwareEntity {
    public static final TrackedData<EntityState> STATE;

    protected final DofusEntityBrain brain = new DofusEntityBrain(this);
    private final List<EntityEvent> events = new ArrayList<>();

    protected DofusMobEntity(EntityType<? extends DofusMobEntity> entityType, World world) {
        super(entityType, world);

        if (!world.isClient) {
            this.initBrain();
        }
    }

    @Override
    public void tick() {
        super.tick();

        // handle ai
        if (!this.getWorld().isClient) {
            this.brain.tick();
        }

        // handle events
        if (!this.getWorld().isClient) {
            var triggeredEvents = new ArrayList<EntityEvent>();

            for (var event : this.events) {
                event.increment();
                if (event.shouldBeTriggered()) {
                    event.getCallback().run();
                    triggeredEvents.add(event);
                }
            }

            this.events.removeAll(triggeredEvents);
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(STATE, EntityState.IDLING);
    }

    protected void initBrain() {
        // nothing
    }

    public DofusEntityBrain getDofusBrain() {
        return this.brain;
    }

    public void setDelayedEvent(int ticks, Runnable callback) {
        this.events.add(new EntityEvent(ticks, callback));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.dataTracker.get(STATE) == EntityState.IDLING) {
            if (source.getAttacker() instanceof LivingEntity attacker && attacker.isAlive() && attacker.getWorld() == this.getWorld()) {
                this.brain.setMemory(MemoryCell.ATTACK_TARGET, attacker);
                this.dataTracker.set(STATE, EntityState.APPROACHING);
            }
        }

        return super.damage(source, amount);
    }

    static {
        STATE = DataTracker.registerData(DofusMobEntity.class, DofusTrackedDataHandlerRegistry.STATE);
    }
}
