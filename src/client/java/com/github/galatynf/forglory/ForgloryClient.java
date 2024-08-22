package com.github.galatynf.forglory;

import com.github.galatynf.forglory.entity.HeroEntityRenderer;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.gui.AdrenalinBar;
import com.github.galatynf.forglory.gui.FeatLabel;
import com.github.galatynf.forglory.init.*;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderRegistry.initClient();
        KeyBindingRegistry.initClient();
        NetworkInit.initClient();
        EntityRendererRegistry.register(EntityRegistry.HERO,  HeroEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.quickFireBlock, RenderLayer.getCutout());

        CottonHud.add(new AdrenalinBar(), 0, -3, 9, 34);

        for (int i = 0; i < Tier.values().length; ++i) {
            CottonHud.add(new FeatLabel(Tier.values()[i]), 0, 20 * (i+1), 100, 15);
        }
    }
}
