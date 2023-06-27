package net.lightglow.client.renderer;

import net.lightglow.common.entity.projectile.TidalArrow;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class TidalArrowRenderer extends ProjectileEntityRenderer<TidalArrow> {

    public TidalArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(TidalArrow entity) {
        return new Identifier("skeletalremains", "textures/entity/projectile/tidal_arrow.png");
    }
}
