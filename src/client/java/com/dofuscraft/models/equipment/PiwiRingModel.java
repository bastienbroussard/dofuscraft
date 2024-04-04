package com.dofuscraft.models.equipment;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

public class PiwiRingModel extends BipedEntityModel<LivingEntity> {
    public PiwiRingModel(ModelPart root) {
        super(root);
        this.setVisible(false);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0F);
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2F, 5.5F, -2.0F, 4.0F, 1.0F, 4.0F),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2F, 5.5F, -2.0F, 4.0F, 1.0F, 4.0F),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        return TexturedModelData.of(modelData, 16, 8);
    }

    public static TexturedModelData getSlimTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0F);
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2F, 5.5F, -2.0F, 3.0F, 1.0F, 4.0F),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2F, 5.5F, -2.0F, 4.0F, 1.0F, 4.0F),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        return TexturedModelData.of(modelData, 16, 8);
    }
}
