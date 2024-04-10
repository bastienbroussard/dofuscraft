package com.dofuscraft.render.entity.model.equipment;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class PiwiCapeModel extends BipedEntityModel<LivingEntity> {
    public PiwiCapeModel(ModelPart root) {
        super(root);
        this.setVisible(false);
        this.body.visible = true;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0F);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
                        .uv(12, 16).cuboid(-5.0F, 0.0F, 1.0F, 10.0F, 16.0F, 0.0F, new Dilation(0.0F))
                        .uv(0, 0).cuboid(-5.0F, 3.0F, 0.0F, 10.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 6).cuboid(-4.0F, 2.0F, 0.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 10).cuboid(-3.0F, 1.0F, 0.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 12).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 8).cuboid(-4.0F, 6.0F, 0.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 4).cuboid(-5.0F, 7.0F, 0.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 14).cuboid(2.0F, 8.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(10, 12).cuboid(-5.0F, 8.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(14, 10).cuboid(-5.0F, 9.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(8, 14).cuboid(3.0F, 9.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 16).cuboid(4.0F, 10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                        .uv(14, 14).cuboid(-5.0F, 10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 8.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
}
