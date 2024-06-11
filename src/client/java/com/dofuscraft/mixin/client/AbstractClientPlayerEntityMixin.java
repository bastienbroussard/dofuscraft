package com.dofuscraft.mixin.client;

import com.dofuscraft.SpellTelegraphAccessor;
import com.dofuscraft.entity.TelegraphEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin implements SpellTelegraphAccessor {
    @Unique
    private TelegraphEntity spellTelegraph;

    @Override
    public TelegraphEntity dofuscraft$getSpellTelegraph() {
        return this.spellTelegraph;
    }

    @Override
    public void dofuscraft$setSpellTelegraph(TelegraphEntity spellTelegraph) {
        this.spellTelegraph = spellTelegraph;
    }
}
