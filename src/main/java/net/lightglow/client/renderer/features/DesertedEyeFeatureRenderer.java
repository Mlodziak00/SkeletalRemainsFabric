package net.lightglow.client.renderer.features;

import net.lightglow.client.model.DesertedSkeletonModel;
import net.lightglow.client.model.SwampedSkeletonModel;
import net.lightglow.common.entity.DesertedSkeleton;
import net.lightglow.common.entity.SwampedSkeleton;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

public class DesertedEyeFeatureRenderer<T extends BipedEntityRenderer> extends EyesFeatureRenderer<DesertedSkeleton, DesertedSkeletonModel<DesertedSkeleton>> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(new Identifier("skeletalremains", "textures/entity/desertedskeleton/desertedskeleton_e.png"));

    public DesertedEyeFeatureRenderer(FeatureRendererContext<DesertedSkeleton, DesertedSkeletonModel<DesertedSkeleton>> featureRendererContext) {
        super(featureRendererContext);
    }


    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}
