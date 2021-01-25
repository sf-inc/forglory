package com.github.galatynf.forglory.client;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.gui.AdrenalinBar;
import com.github.galatynf.forglory.imixin.ILastStandMixin;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {
    private static KeyBinding activateFeatKey;
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Forglory.quickFireBlock, RenderLayer.getCutout());

        AdrenalinBar adrenalinBar = new AdrenalinBar();
        CottonHud.INSTANCE.add(adrenalinBar, 135, -37, 9, 34);

        activateFeatKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.forglory.activateFeatKey",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                "category.forglory.keys"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (activateFeatKey.wasPressed()) {
                PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                ClientSidePacketRegistry.INSTANCE.sendToServer(Forglory.ACTIVATE_FEAT_PACKET_ID, passedData);
            }
        });

        ClientSidePacketRegistry.INSTANCE.register(Forglory.BERSERK_PACKET_ID, (packetContext, attachedData) -> {
            packetContext.getTaskQueue().execute(() -> {
                assert MinecraftClient.getInstance().player != null;
                ((ILastStandMixin)MinecraftClient.getInstance().player).setBerserk();
            });
        });
    }
}
