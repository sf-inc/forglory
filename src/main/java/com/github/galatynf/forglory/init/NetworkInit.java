package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.NoMixinFeats;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.*;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetworkInit {
    private NetworkInit() {}

    public static final Identifier PLAY_SOUND_ID = new Identifier("forglory", "play_sound");

    public static final Identifier PLAY_INCRE_ID = new Identifier("forglory", "play_incre");

    public static final Identifier PLAY_VAMP_ID = new Identifier("forglory", "play_vamp");

    public static final Identifier PLAY_KNOCKBACK_FIST_ACT_ID = new Identifier("forglory", "play_knockback_fist_act");

    public static final Identifier ACTIVATE_FEAT_PACKET_ID = new Identifier("forglory", "activate_feat");
    public static final Identifier BERSERK_PACKET_ID = new Identifier("forglory", "is_berserk");

    public static void initClient () {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyInit.activateFeatKey.wasPressed()) {
                ClientPlayNetworking.send(ACTIVATE_FEAT_PACKET_ID, PacketByteBufs.empty());
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(BERSERK_PACKET_ID, (client, handler, buf, responseSender) -> client.execute(() -> {
            if (client.player != null) {
                ((ILastStandMixin) client.player).setBerserk();
            }
        }));

        ClientPlayNetworking.registerGlobalReceiver(NetworkInit.PLAY_SOUND_ID, (client, handler, buf, responseSender) -> client.execute(() -> {
            if (client.player != null) {
                client.player.playSound(Registry.SOUND_EVENT.get(buf.readIdentifier()), 1, 1);
            }
        }));

        ClientPlayNetworking.registerGlobalReceiver(NetworkInit.PLAY_INCRE_ID, (client, handler, buf, responseSender) -> client.execute(() -> {
            if (client.player != null) {
                client.player.playSound(SoundsInit.incre, 1, 1);
            }
        }));

        ClientPlayNetworking.registerGlobalReceiver(NetworkInit.PLAY_VAMP_ID, (client, handler, buf, responseSender) -> client.execute(() -> {
            if (client.player != null) {
                client.player.playSound(SoundsInit.vampirism, 1, 1);
            }
        }));

        ClientPlayNetworking.registerGlobalReceiver(NetworkInit.PLAY_KNOCKBACK_FIST_ACT_ID, (client, handler, buf, responseSender) -> client.execute(() -> {
            if (client.player != null) {
                client.player.playSound(SoundsInit.knockback_fist_act, 1, 1);
            }
        }));
    }

    public static void init () {
        ServerPlayNetworking.registerGlobalReceiver(ACTIVATE_FEAT_PACKET_ID, (server, player, handler, buf, responseSender) -> server.execute(() -> {
            Feats feat = MyComponents.FEATS.get(player).getFeat(Tier.TIER2);
            if (feat == null) return;
            if (MyComponents.ADRENALIN.get(player).getAdrenalin() > Tier.TIER2.threshold &&
                    MyComponents.FEATS.get(player).getCooldown(Tier.TIER2) == 0 ) {

                if (feat.equals(Feats.DASH)) {
                    NoMixinFeats.dashFeat(player);

                } else if (feat.equals(Feats.FIRE_TRAIL)) {
                    ((IFireTrailMixin) player).invertFireTrail();

                } else if (feat.equals(Feats.MACHINE_GUN)) {
                    ((IMachineGunMixin) player).setMachineGun(ModConfig.get().featConfig.machine_gun_arrows);

                } else if (feat.equals(Feats.MOUNTAIN)) {
                    NoMixinFeats.mountainFeat(player);
                }

                else if(feat.equals(Feats.HEALING_FIST)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffectsInit.lifeStealStatusEffect, 100, 0));
                    //ServerPlayNetworking.send(player, NetworkInit.PLAY_VAMP_ID, buf);
                    PacketByteBuf buffy = PacketByteBufs.create();
                    buf.writeIdentifier(SoundsInit.VAMPIRISM_ID);
                    ServerPlayNetworking.send(player, NetworkInit.PLAY_SOUND_ID, buffy);
                }
                else if(feat.equals(Feats.KNOCKBACK_FIST)) {
                    ((IKnockbackFistPlayerMixin)player).setKnockBack(true);
                }
                MyComponents.FEATS.get(player).resetCooldown(Tier.TIER2);
            }
        }));
    }
}
