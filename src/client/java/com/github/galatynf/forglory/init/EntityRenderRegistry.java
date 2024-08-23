package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.entity.HeroEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityRenderRegistry {
    public static void init() {
        EntityRendererRegistry.register(EntityRegistry.HERO,  HeroEntityRenderer::new);
    }
}
