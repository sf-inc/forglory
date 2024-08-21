package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.NoMixinFeats;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IFireTrailMixin;
import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.imixin.IMachineGunMixin;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class NetworkInit {
    private NetworkInit() {
    }

    public static final Identifier PLAY_SOUND_ID = Forglory.id("play_sound");

    public static final Identifier ACTIVATE_FEAT_PACKET_ID = Forglory.id("activate_feat");
    public static final Identifier BERSERK_PACKET_ID = Forglory.id("is_berserk");

    public static void initClient() {
        // FIXME: Make the keybind send a packet
        /*ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyInit.activateFeatKey.wasPressed()) {
                ClientPlayNetworking.send( ACTIVATE_FEAT_PACKET_ID, PacketByteBufs.empty());
            }
        });*/

        // TODO: Check why we have a berserk packet
        /*ClientPlayNetworking.registerGlobalReceiver(BERSERK_PACKET_ID, (client, handler, buf, responseSender) -> {
            boolean setter = buf.readBoolean();
            client.execute(() -> {
                if (client.player != null) {
                    ((ILastStandMixin) client.player).setBerserk(setter);
                }
            });

        });*/

        // FIXME: Rework sound system to not use packets
        /*ClientPlayNetworking.registerGlobalReceiver(NetworkInit.PLAY_SOUND_ID, (client, handler, buf, responseSender) -> {
            Identifier id = buf.readIdentifier();
            boolean isClassSound = buf.readBoolean();
            float distance = buf.readFloat();
            if(!isClassSound || ModConfig.get().guiSoundsConfig.enable_class_sounds) {
                if (client.player != null) {
                    client.player.playSound(Registry.SOUND_EVENT.get(id), (float) (1 / Math.sqrt(distance + 1)), 1);
                }
            }
            client.execute(() -> {
            });
        });*/
    }

    /*public static void playSoundWide(Identifier sound, ServerPlayerEntity player, boolean isClassSound) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        buffy.writeBoolean(isClassSound);
        for (ServerPlayerEntity aPlayer : PlayerLookup.tracking((ServerWorld) player.world, player.getBlockPos())) {
            if (aPlayer.distanceTo(player) < 15) {
                buffy.writeFloat(aPlayer.distanceTo(player));
                ServerPlayNetworking.send(aPlayer, NetworkInit.PLAY_SOUND_ID, buffy);
            }
        }
    }

    public static void playSoundWide(Identifier sound, PlayerEntity player, boolean isClassSound) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        buffy.writeBoolean(isClassSound);
        for (ServerPlayerEntity aPlayer : PlayerLookup.tracking((ServerWorld) player.world, player.getBlockPos())) {
            if (aPlayer.distanceTo(player) < 15) {
                buffy.writeFloat(aPlayer.distanceTo(player));
                ServerPlayNetworking.send((ServerPlayerEntity) player, NetworkInit.PLAY_SOUND_ID, buffy);
            }
        }
    }

    public static void playSound(Identifier sound, ServerPlayerEntity player) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        buffy.writeBoolean(false);
        buffy.writeFloat(1);
        ServerPlayNetworking.send(player, NetworkInit.PLAY_SOUND_ID, buffy);
    }

    public static void playSound(Identifier sound, ServerPlayerEntity player, boolean isClassSound) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        buffy.writeBoolean(isClassSound);
        buffy.writeFloat(1);
        ServerPlayNetworking.send(player, NetworkInit.PLAY_SOUND_ID, buffy);
    }

    public static void playSound(Identifier sound, PlayerEntity player) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        buffy.writeBoolean(false);
        buffy.writeFloat(1);
        ServerPlayNetworking.send((ServerPlayerEntity) player, NetworkInit.PLAY_SOUND_ID, buffy);
    }

    public static void playSound(Identifier sound, PlayerEntity player, boolean isClassSound) {
        PacketByteBuf buffy = PacketByteBufs.create();
        buffy.writeIdentifier(sound);
        buffy.writeBoolean(isClassSound);
        buffy.writeFloat(1);
        ServerPlayNetworking.send((ServerPlayerEntity) player, NetworkInit.PLAY_SOUND_ID, buffy);
    }*/

    public static void init() {
        // FIXME: Handle the receive packet from the keybind
        /*ServerPlayNetworking.registerGlobalReceiver(ACTIVATE_FEAT_PACKET_ID, (payload, context) -> context.server().execute(() -> {
            ServerPlayerEntity player = context.player();
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
                    // FIXME: Do proper registration for effects
                    //player.addStatusEffect(new StatusEffectInstance(StatusEffectsInit.lifeStealStatusEffect, 100, 0));
                    // FIXME: Play vampirism sound
                    //playSound(SoundsInit.VAMPIRISM_ID, player);
                } else if (feat.equals(Feats.KNOCKBACK_FIST)) {
                    ((IKnockbackFistPlayerMixin) player).setKnockBack(true);
                }
                MyComponents.FEATS.get(player).resetCooldown(Tier.TIER2);
            }
        }));*/
    }
}
