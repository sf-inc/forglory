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
    public static final Identifier ACTIVATE_FEAT_PACKET_ID = Forglory.id("activate_feat");
    public static final Identifier BERSERK_PACKET_ID = Forglory.id("is_berserk");

    private NetworkInit() {
    }

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
    }

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
