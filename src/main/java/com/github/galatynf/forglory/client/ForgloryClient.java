package com.github.galatynf.forglory.client;

import com.github.galatynf.forglory.entity.HeroEntityRenderer;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.gui.AdrenalinBar;
import com.github.galatynf.forglory.gui.FeatLabel;
import com.github.galatynf.forglory.init.BlocksInit;
import com.github.galatynf.forglory.init.EntitiesInit;
import com.github.galatynf.forglory.init.KeyInit;
import com.github.galatynf.forglory.init.NetworkInit;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlocksInit.initClient();
        KeyInit.initClient();
        NetworkInit.initClient();
        EntityRendererRegistry.INSTANCE.register(EntitiesInit.HERO, (r, c) -> new HeroEntityRenderer(r));

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksInit.quickFireBlock, RenderLayer.getCutout());

        CottonHud.INSTANCE.add(new AdrenalinBar(), 0, -3, 9, 34);

        for (int i=0; i < Tier.values().length; i++) {
            CottonHud.INSTANCE.add(new FeatLabel(Tier.values()[i]), 0, 20 * (i+1), 100, 15);
        }
    }
}
