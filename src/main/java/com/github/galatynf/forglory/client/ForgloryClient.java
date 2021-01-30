package com.github.galatynf.forglory.client;

import com.github.galatynf.forglory.entity.HeroEntityRenderer;
import com.github.galatynf.forglory.gui.AdrenalinBar;
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
import net.minecraft.entity.EntityType;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {
    public static EntityType<?> entityType;
    @Override
    public void onInitializeClient() {
        BlocksInit.initClient();
        KeyInit.initClient();
        NetworkInit.initClient();
        EntityRendererRegistry.INSTANCE.register(EntitiesInit.HERO, (r, c)->new HeroEntityRenderer(r));

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksInit.quickFireBlock, RenderLayer.getCutout());

        AdrenalinBar adrenalinBar = new AdrenalinBar();
        CottonHud.INSTANCE.add(adrenalinBar, 135, -37, 9, 34);
    }
}
