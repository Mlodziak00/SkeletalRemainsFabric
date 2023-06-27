package net.lightglow.client.renderer;

import net.lightglow.client.model.FallenSkeletonModel;
import net.lightglow.client.model.OvergrownSkeletonModel;
import net.lightglow.client.model.SwampedSkeletonModel;
import net.lightglow.client.renderer.features.SwampedEyesFeatureRenderer;
import net.lightglow.common.entity.FallenSkeleton;
import net.lightglow.common.entity.SwampedSkeleton;
import net.lightglow.common.registry.SKEntityRenderer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SwampedSkeletonRenderer extends BipedEntityRenderer<SwampedSkeleton, SwampedSkeletonModel<SwampedSkeleton>> {
    public SwampedSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, SKEntityRenderer.SWAMPED_SKELETON_LAYER, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public SwampedSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new SwampedSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new SwampedSkeletonModel(ctx.getPart(legArmorLayer)), new SwampedSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
        this.addFeature(new SwampedEyesFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(SwampedSkeleton entity) {
        return new Identifier("skeletalremains", "textures/entity/swampedskeleton/swampedskeleton.png");
    }

}


