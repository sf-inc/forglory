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
import com.github.galatynf.forglory.network.ActivateFeatPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class NetworkRegistry {
    public static final Identifier ACTIVATE_FEAT_PACKET_ID = Forglory.id("activate_feat");
    public static final Identifier BERSERK_PACKET_ID = Forglory.id("is_berserk");

    private NetworkRegistry() {
    }

    public static void initClient() {
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
        PayloadTypeRegistry.playC2S().register(ActivateFeatPayload.ID, ActivateFeatPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ActivateFeatPayload.ID, (payload, context) -> context.server().execute(() -> {
            ServerPlayerEntity player = context.player();
            Feats feat = MyComponents.FEATS.get(player).getFeat(Tier.TIER2);
            if (feat == null) return;
            if (MyComponents.ADRENALIN.get(player).getAdrenalin() > Tier.TIER2.threshold &&
                    MyComponents.FEATS.get(player).getCooldown(Tier.TIER2) == 0) {

                if (feat.equals(Feats.DASH)) {
                    NoMixinFeats.dashFeat(player);

                } else if (feat.equals(Feats.FIRE_TRAIL)) {
                    ((IFireTrailMixin) player).forglory$invertFireTrail();

                } else if (feat.equals(Feats.MACHINE_GUN)) {
                    ((IMachineGunMixin) player).forglory$setMachineGun(ModConfig.get().featConfig.machine_gun_arrows);

                } else if (feat.equals(Feats.MOUNTAIN)) {
                    NoMixinFeats.mountainFeat(player);
                } else if (feat.equals(Feats.HEALING_FIST)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffectRegistry.LIFE_STEAL, 100, 0));
                    player.playSound(SoundRegistry.VAMPIRISM);
                } else if (feat.equals(Feats.KNOCKBACK_FIST)) {
                    ((IKnockbackFistPlayerMixin) player).forglory$setKnockBack(true);
                }
                MyComponents.FEATS.get(player).resetCooldown(Tier.TIER2);
            }
        }));
    }
}