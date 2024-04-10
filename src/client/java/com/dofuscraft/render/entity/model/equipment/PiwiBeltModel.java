package com.dofuscraft.render.entity.model.equipment;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class PiwiBeltModel extends BipedEntityModel<LivingEntity> {
	public PiwiBeltModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.body.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0F);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
						.uv(8, 0).cuboid(-4.0F, 8.5F, -2.0F, 8.0F, 1.0F, 4.0F),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData beltPivot = Body.addChild("belt_pivot", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 9F, -3.5F));

		beltPivot.addChild("knot_1", ModelPartBuilder.create()
						.uv(0, 0).cuboid(-1.0F, -3.0F, 1.1F, 1.0F, 3.0F, 1.0F),
				ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

		beltPivot.addChild("knot_2", ModelPartBuilder.create()
						.uv(0, 0).cuboid(-1.0F, -3.0F, 1.0F, 1.0F, 3.0F, 1.0F),
				ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

		return TexturedModelData.of(modelData, 32, 8);
	}
}