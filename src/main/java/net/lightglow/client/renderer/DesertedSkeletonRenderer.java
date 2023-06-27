package net.lightglow.client.renderer;

import net.lightglow.client.model.DesertedSkeletonModel;
import net.lightglow.client.model.FallenSkeletonModel;
import net.lightglow.client.renderer.features.DesertedEyeFeatureRenderer;
import net.lightglow.client.renderer.features.SwampedEyesFeatureRenderer;
import net.lightglow.common.entity.DesertedSkeleton;
import net.lightglow.common.entity.FallenSkeleton;
import net.lightglow.common.registry.SKEntityRenderer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DesertedSkeletonRenderer extends BipedEntityRenderer<DesertedSkeleton, DesertedSkeletonModel<DesertedSkeleton>> {
    public DesertedSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, SKEntityRenderer.DESERTED_SKELETON_LAYER, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public DesertedSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new DesertedSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new DesertedSkeletonModel(ctx.getPart(legArmorLayer)), new DesertedSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
        this.addFeature(new DesertedEyeFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(DesertedSkeleton entity) {
        return new Identifier("skeletalremains", "textures/entity/desertedskeleton/desertedskeleton.png");
    }
}


