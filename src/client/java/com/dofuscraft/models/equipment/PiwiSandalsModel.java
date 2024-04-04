package com.dofuscraft.models.equipment;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

public class PiwiSandalsModel extends BipedEntityModel<LivingEntity> {
	public PiwiSandalsModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.leftLeg.visible = true;
		this.rightLeg.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0F);
		ModelPartData modelPartData = modelData.getRoot();

		modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create()
						.uv(0, 0).cuboid(-2.1F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F)
						.uv(21, 6).cuboid(-2.1F, 9.0F, -3.0F, 4.0F, 1.0F, 1.0F)
						.uv(21, 6).cuboid(-2.1F, 9.0F, 0.0F, 4.0F, 1.0F, 1.0F),
				ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

		modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create()
						.uv(0, 0).cuboid(-2F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F)
						.uv(21, 6).cuboid(-2F, 9.0F, -3.0F, 4.0F, 1.0F, 1.0F)
						.uv(21, 6).cuboid(-2F, 9.0F, 0.0F, 4.0F, 1.0F, 1.0F),
				ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

		return TexturedModelData.of(modelData, 32, 8);
	}
}