package com.github.galatynf.forglory.client;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.blocks.QuickFireBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import javax.print.attribute.standard.Fidelity;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Forglory.quickFireBlock, RenderLayer.getCutout());
    }
}
