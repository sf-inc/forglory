package com.github.galatynf.forglory.network;

import com.github.galatynf.forglory.init.NetworkRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record BerserkPayload(boolean isBerserk) implements CustomPayload {
    public static final Id<BerserkPayload> ID = new Id<>(NetworkRegistry.BERSERK_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, BerserkPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, BerserkPayload::isBerserk,
            BerserkPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
