package net.lightglow.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.lightglow.client.renderer.*;
import net.lightglow.common.registry.SKEntityType;
import net.lightglow.common.registry.SKEntityRenderer;
import net.lightglow.util.SkeletalModelPredicateProvider;

public class SKClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SKEntityRenderer.entreg();

        SkeletalModelPredicateProvider.registerSKModels();

    }
}
