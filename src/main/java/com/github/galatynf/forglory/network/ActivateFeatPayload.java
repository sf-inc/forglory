package com.github.galatynf.forglory.network;

import com.github.galatynf.forglory.init.NetworkRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public enum ActivateFeatPayload implements CustomPayload {
    INSTANCE;

    public static final CustomPayload.Id<ActivateFeatPayload> ID = new CustomPayload.Id<>(NetworkRegistry.ACTIVATE_FEAT_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, ActivateFeatPayload> CODEC = PacketCodec.unit(INSTANCE);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
