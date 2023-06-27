package net.lightglow.client.renderer;

import net.lightglow.client.model.OvergrownSkeletonModel;
import net.lightglow.client.model.SharpshooterSkeletonModel;
import net.lightglow.common.entity.SharpshooterSkeleton;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SharpshooterSkeletonRenderer extends BipedEntityRenderer<SharpshooterSkeleton, SharpshooterSkeletonModel<SharpshooterSkeleton>> {
    public SharpshooterSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public SharpshooterSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new SharpshooterSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new SharpshooterSkeletonModel(ctx.getPart(legArmorLayer)), new SharpshooterSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));    }

    @Override
    public Identifier getTexture(SharpshooterSkeleton entity) {
        return new Identifier("skeletalremains", "textures/entity/sharpshooterskeleton/sharpshooterskeleton.png");
    }

    protected boolean isShaking(SharpshooterSkeleton sharpshooterSkeleton) {
        return sharpshooterSkeleton.isShaking();
    }
}

