package com.github.galatynf.forglory.client;

import com.github.galatynf.forglory.Forglory;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {
    private static KeyBinding activateFeatKey;
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Forglory.quickFireBlock, RenderLayer.getCutout());

        activateFeatKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.forglory.activateFeatKey",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                "category.forglory.keys"
        ));
    }
}
