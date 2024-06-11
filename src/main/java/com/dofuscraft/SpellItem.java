package com.dofuscraft;

import com.dofuscraft.entity.spell.HandEntity;
import com.dofuscraft.registry.DofusEntityRegistry;
import com.dofuscraft.registry.DofusSoundEventRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class SpellItem extends Item {
    public SpellItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand == Hand.MAIN_HAND) {
            var hit = user.raycast(1000, 0, false);
            switch (hit.getType()) {
                case HitResult.Type.BLOCK:
                    var handEntity = new HandEntity(DofusEntityRegistry.HAND, world);
                    handEntity.setPosition(hit.getPos().x, hit.getPos().y, hit.getPos().z);
                    world.spawnEntity(handEntity);

                    world.playSound(null, ((BlockHitResult)hit).getBlockPos(), DofusSoundEventRegistry.HAND, SoundCategory.PLAYERS);
                    break;
            }
        }

        return super.use(world, user, hand);
    }
}
