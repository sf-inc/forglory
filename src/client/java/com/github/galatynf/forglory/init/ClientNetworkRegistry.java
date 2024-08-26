package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.imixin.ILastStandMixin;
import com.github.galatynf.forglory.network.ActivateFeatPayload;
import com.github.galatynf.forglory.network.BerserkPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;

public class ClientNetworkRegistry {
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindingRegistry.ACTIVATE_FEAT.wasPressed()) {
                ClientPlayNetworking.send(ActivateFeatPayload.INSTANCE);
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(BerserkPayload.ID, (payload, context) -> context.client().execute(() -> {
            ClientPlayerEntity player = context.player();
            if (player != null) {
                ((ILastStandMixin) player).forglory$setBerserk(payload.isBerserk());
            }
        }));
    }
}
