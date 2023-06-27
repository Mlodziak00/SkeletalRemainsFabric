package net.lightglow.client.renderer;

import net.lightglow.client.model.OvergrownSkeletonModel;
import net.lightglow.common.entity.OvergrownSkeleton;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class OvergrownSkeletonRenderer extends BipedEntityRenderer<OvergrownSkeleton, OvergrownSkeletonModel<OvergrownSkeleton>> {
    public OvergrownSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public OvergrownSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new OvergrownSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new OvergrownSkeletonModel(ctx.getPart(legArmorLayer)), new OvergrownSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
    }

    @Override
    public Identifier getTexture(OvergrownSkeleton entity) {
        return new Identifier("skeletalremains", "textures/entity/overgrownskeleton/overgrownskeleton.png");
    }

    protected boolean isShaking(OvergrownSkeleton overgrownSkeleton) {
        return overgrownSkeleton.isShaking();
    }
}

