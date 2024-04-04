package com.dofuscraft;

import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DofusEquipmentItem extends Item {
    private final DofusEquipmentType type;
    private final int level;
    private final List<EquipmentEffect> effects;
    private final SoundEvent equipSound;

    public DofusEquipmentItem(DofusEquipmentType type, int level, List<EquipmentEffect> effects, SoundEvent equipSound) {
        super(new FabricItemSettings().maxCount(1));
        this.type = type;
        this.level = level;
        this.effects = effects;
        this.equipSound = equipSound;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.modifiers." + this.type.getName()).formatted(Formatting.GRAY));

        var effectsNbt = stack.getSubNbt("effects");
        for (var effect : this.effects) {
            var characteristic = effect.getEffect();
            var key = characteristic.getTranslationKey();

            String value;
            if (effectsNbt == null) {
                // item doesn't have nbt: we display possible values
                if (effect.getMinValue() == effect.getMaxValue()) {
                    value = String.valueOf(effect.getMinValue());
                }
                else {
                    value = effect.getMinValue() + "-" + effect.getMaxValue();
                }
            }
            else {
                // item does have nbt: we display its value
                value = String.valueOf(effectsNbt.getInt(characteristic.getName()));
            }

            var text = MutableText.of(new PlainTextContent.Literal("+" + value + " "))
                    .append(Text.translatable(key)
                    .formatted(characteristic.getColor()));

            tooltip.add(text);
        }
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return super.getAttributeModifiers(slot);
    }

    public DofusEquipmentType getType() {
        return this.type;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public List<EquipmentEffect> getEffects() {
        return this.effects;
    }
}
