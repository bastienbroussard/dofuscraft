package com.dofuscraft.models.equipment;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

public class PiwiHatModel extends BipedEntityModel<LivingEntity> {
    public PiwiHatModel(ModelPart root) {
        super(root);
        this.setVisible(false);
        this.head.visible = true;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0F);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                        .uv(1, 3).cuboid(-1.0F, -6.0F, -5.0F, 2.0F, 1.0F, 1.0F)
                        .uv(24, 1).cuboid(-1.0F, -12.0F, -1.0F, 2.0F, 4.0F, 2.0F),
                ModelTransform.NONE);
        return TexturedModelData.of(modelData, 32, 16);
    }
}