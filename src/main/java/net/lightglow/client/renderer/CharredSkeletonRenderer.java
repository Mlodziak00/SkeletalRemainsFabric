package net.lightglow.client.renderer;

import net.lightglow.client.model.CharredSkeletonModel;
import net.lightglow.client.model.OvergrownSkeletonModel;
import net.lightglow.common.entity.CharredSkeleton;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;

public class CharredSkeletonRenderer extends BipedEntityRenderer<CharredSkeleton, CharredSkeletonModel<CharredSkeleton>> {
    public CharredSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public CharredSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new CharredSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new CharredSkeletonModel(ctx.getPart(legArmorLayer)), new CharredSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
    }

    @Override
    public Identifier getTexture(CharredSkeleton entity) {
        return new Identifier("skeletalremains", "textures/entity/charredskeleton/charredskeleton.png");
    }

    public void scale(CharredSkeleton charredSkeleton, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.1F, 1.1F, 1.1F);
    }
}

