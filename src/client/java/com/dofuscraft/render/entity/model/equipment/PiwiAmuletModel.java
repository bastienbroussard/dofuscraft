package com.dofuscraft.render.entity.model.equipment;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class PiwiAmuletModel extends BipedEntityModel<LivingEntity> {
    public PiwiAmuletModel(ModelPart root) {
        super(root);
        this.setVisible(false);
        this.body.visible = true;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0F);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-7.0F, -1.0F, -6.0F, 13.0F, 13.0F, 8.0F)
                        .uv(40, 1).cuboid(-2.0F, 5.0F, 2F, 3.0F, 3.0F, 1.0F)
                        .uv(35, 1).cuboid(-1.0F, 7.0F, 3F, 1.0F, 1.0F, 1.0F),
                ModelTransform.NONE);
        return TexturedModelData.of(modelData, 48, 24);
    }
}
