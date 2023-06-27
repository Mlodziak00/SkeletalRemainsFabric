package net.lightglow.client.renderer.features;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lightglow.client.model.SwampedSkeletonModel;
import net.lightglow.common.entity.SwampedSkeleton;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

public class SwampedEyesFeatureRenderer<T extends BipedEntityRenderer> extends EyesFeatureRenderer<SwampedSkeleton, SwampedSkeletonModel<SwampedSkeleton>> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(new Identifier("skeletalremains", "textures/entity/swampedskeleton/swampedskeleton_e.png"));

    public SwampedEyesFeatureRenderer(FeatureRendererContext<SwampedSkeleton, SwampedSkeletonModel<SwampedSkeleton>> featureRendererContext) {
        super(featureRendererContext);
    }


    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}
