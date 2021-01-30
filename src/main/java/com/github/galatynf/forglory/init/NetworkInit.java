package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.NoMixinFeats;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.*;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class NetworkInit {
    private NetworkInit() {}

    public static final Identifier ACTIVATE_FEAT_PACKET_ID = new Identifier("forglory", "activate_feat");
    public static final Identifier BERSERK_PACKET_ID = new Identifier("forglory", "is_berserk");

    public static void initClient () {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyInit.activateFeatKey.wasPressed()) {
                PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                ClientSidePacketRegistry.INSTANCE.sendToServer(ACTIVATE_FEAT_PACKET_ID, passedData);
            }
        });

        ClientSidePacketRegistry.INSTANCE.register(BERSERK_PACKET_ID, (packetContext, attachedData) -> packetContext.getTaskQueue().execute(() -> {
            assert MinecraftClient.getInstance().player != null;
            ((ILastStandMixin)MinecraftClient.getInstance().player).setBerserk();
        }));
    }

    public static void init () {
        ServerSidePacketRegistry.INSTANCE.register(ACTIVATE_FEAT_PACKET_ID,
                (packetContext, attachedData) -> packetContext.getTaskQueue().execute(() -> {
                    PlayerEntity playerEntity = packetContext.getPlayer();
                    Feats feat = MyComponents.FEATS.get(playerEntity).getFeat(Tier.TIER2);
                    if (feat == null) return;
                    if (((IAdrenalinMixin) playerEntity).getAdrenalin() > Tier.TIER2.threshold &&
                            MyComponents.FEATS.get(playerEntity).getCooldown(Tier.TIER2) == 0 ) {

                        if (feat.equals(Feats.DASH)) {
                            NoMixinFeats.dashFeat(playerEntity);

                        } else if (feat.equals(Feats.FIRE_TRAIL)) {
                            ((IFireTrailMixin) playerEntity).invertFireTrail();

                        } else if (feat.equals(Feats.MACHINE_GUN)) {
                            ((IMachineGunMixin) playerEntity).setMachineGun(ModConfig.get().featConfig.machine_gun_arrows);

                        } else if (feat.equals(Feats.MOUNTAIN)) {
                            NoMixinFeats.mountainFeat(playerEntity);
                        }

                        else if(feat.equals(Feats.HEALING_FIST)) {
                            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsInit.lifeStealStatusEffect, 100, 0));
                        }
                        else if(feat.equals(Feats.KNOCKBACK_FIST)) {
                            //playsound(INCRE)
                            ((IKnockbackFistPlayerMixin)playerEntity).setKnockBack(true);
                        }
                        MyComponents.FEATS.get(playerEntity).resetCooldown(Tier.TIER2);
                    }
                }));
    }
}
