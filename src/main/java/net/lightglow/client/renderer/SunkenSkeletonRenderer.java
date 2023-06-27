package net.lightglow.client.renderer;

import net.lightglow.client.model.OvergrownSkeletonModel;
import net.lightglow.client.model.SunkenSkeletonModel;
import net.lightglow.common.entity.SunkenSkeleton;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SunkenSkeletonRenderer extends BipedEntityRenderer<SunkenSkeleton, SunkenSkeletonModel<SunkenSkeleton>> {
    public SunkenSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public SunkenSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new SunkenSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new SunkenSkeletonModel(ctx.getPart(legArmorLayer)), new SunkenSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));    }

    @Override
    public Identifier getTexture(SunkenSkeleton entity) {
        return new Identifier("skeletalremains", "textures/entity/sunkenskeleton/sunkenskeleton.png");
    }

}

