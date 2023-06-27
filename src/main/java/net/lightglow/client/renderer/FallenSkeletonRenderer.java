package net.lightglow.client.renderer;

import net.lightglow.client.model.FallenSkeletonModel;
import net.lightglow.client.model.OvergrownSkeletonModel;
import net.lightglow.common.entity.CharredSkeleton;
import net.lightglow.common.entity.FallenSkeleton;
import net.lightglow.common.registry.SKEntityRenderer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FallenSkeletonRenderer extends BipedEntityRenderer<FallenSkeleton, FallenSkeletonModel<FallenSkeleton>> {
    public FallenSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, SKEntityRenderer.FALLEN_SKELETON_LAYER, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public FallenSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new FallenSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new FallenSkeletonModel(ctx.getPart(legArmorLayer)), new FallenSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
    }

    @Override
    public Identifier getTexture(FallenSkeleton entity) {
        return new Identifier("skeletalremains", "textures/entity/fallenskeleton/fallenskeleton.png");
    }

    public void scale(FallenSkeleton charredSkeleton, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.1F, 1.1F, 1.1F);
    }
}


