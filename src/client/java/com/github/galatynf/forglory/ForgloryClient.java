package com.github.galatynf.forglory;

import com.github.galatynf.forglory.init.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderRegistry.init();
        EntityRenderRegistry.init();
        KeyBindingRegistry.init();
        ClientNetworkRegistry.init();
        GuiRegistry.init();
    }
}
