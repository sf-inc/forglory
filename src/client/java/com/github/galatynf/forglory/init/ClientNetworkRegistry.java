package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.network.ActivateFeatPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientNetworkRegistry {
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindingRegistry.ACTIVATE_FEAT.wasPressed()) {
                ClientPlayNetworking.send(ActivateFeatPayload.INSTANCE);
            }
        });
    }
}
