package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.NoMixinFeats;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IFireTrailMixin;
import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.imixin.ILastStandMixin;
import com.github.galatynf.forglory.imixin.IMachineGunMixin;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetworkInit {
    private NetworkInit() {
    }

    public static final Identifier PLAY_SOUND_ID = new Identifier("forglory", "play_sound");

    public static final Identifier ACTIVATE_FEAT_PACKET_ID = new Identifier("forglory", "activate_feat");
    public static final Identifier BERSERK_PACKET_ID = new Identifier("forglory", "is_berserk");

    public static void initClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyInit.activateFeatKey.wasPressed()) {
                ClientPlayNetworking.send(ACTIVATE_FEAT_PACKET_ID, PacketByteBufs.empty());
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(BERSERK_PACKET_ID, (client, handler, buf, responseSender) -> {
            boolean setter = buf.readBoolean();
            client.execute(() -> {
                if (client.player != null) {
                    ((ILastStandMixin) client.player).setBerserk(setter);
                }
            });

        });

        ClientPlayNetworking.registerGlobalReceiver(NetworkInit.PLAY_SOUND_ID, (client, handler, buf, responseSender) -> {
            Identifier id = buf.readIdentifier();
            if (client.player != null) {
                client.player.playSound(Registry.SOUND_EVENT.get(id), 0.7f, 1);
            }
            client.execute(() -> {
            });
        });
    }

    public static void playSoundWide(Identifier sound, ServerPlayerEntity player) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        for (ServerPlayerEntity aPlayer : PlayerLookup.tracking((ServerWorld) player.world, player.getBlockPos())) {
            if (aPlayer.distanceTo(player) > 30) {
                ServerPlayNetworking.send(aPlayer, NetworkInit.PLAY_SOUND_ID, buffy);
            }
        }
    }

    public static void playSound(Identifier sound, ServerPlayerEntity player) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        ServerPlayNetworking.send(player, NetworkInit.PLAY_SOUND_ID, buffy);
    }


    public static void playSound(Identifier sound, PlayerEntity player) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        ServerPlayNetworking.send((ServerPlayerEntity) player, NetworkInit.PLAY_SOUND_ID, buffy);
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(ACTIVATE_FEAT_PACKET_ID, (server, player, handler, buf, responseSender) -> server.execute(() -> {
            Feats feat = MyComponents.FEATS.get(player).getFeat(Tier.TIER2);
            if (feat == null) return;
            if (MyComponents.ADRENALIN.get(player).getAdrenalin() > Tier.TIER2.threshold &&
                    MyComponents.FEATS.get(player).getCooldown(Tier.TIER2) == 0) {

                if (feat.equals(Feats.DASH)) {
                    NoMixinFeats.dashFeat(player);

                } else if (feat.equals(Feats.FIRE_TRAIL)) {
                    ((IFireTrailMixin) player).invertFireTrail();

                } else if (feat.equals(Feats.MACHINE_GUN)) {
                    ((IMachineGunMixin) player).setMachineGun(ModConfig.get().featConfig.machine_gun_arrows);

                } else if (feat.equals(Feats.MOUNTAIN)) {
                    NoMixinFeats.mountainFeat(player);
                } else if (feat.equals(Feats.HEALING_FIST)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffectsInit.lifeStealStatusEffect, 100, 0));
                    playSound(SoundsInit.VAMPIRISM_ID, player);
                } else if (feat.equals(Feats.KNOCKBACK_FIST)) {
                    ((IKnockbackFistPlayerMixin) player).setKnockBack(true);
                }
                MyComponents.FEATS.get(player).resetCooldown(Tier.TIER2);
            }
        }));
    }
}
