package com.github.galatynf.forglory.init;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BlockRenderRegistry {
    public static void initClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.quickFireBlock, RenderLayer.getCutout());
    }
}
