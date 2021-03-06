package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.blocks.QuickFireBlock;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.init.BlocksInit;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class FireZoneMixin extends Entity {
    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    public FireZoneMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    float forglory_fireRadius;

    @Unique
    private boolean forglory_firstTime_FZ = true;

    @Inject(at = @At("INVOKE"), method = "tick")
    private void spawnFireZone(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.FIRE_ZONE)) {
            if(forglory_firstTime_FZ) {
                if(MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.PYROMANIAC) {
                    NetworkInit.playSoundWide(SoundsInit.FIRE_ZONE_VOICE_ID, (ServerPlayerEntity)(Object) this, true);
                }
                forglory_firstTime_FZ = false;
            }
            forglory_fireRadius += (double) ModConfig.get().featConfig.fireZoneConfig.radius / (100 * ModConfig.get().featConfig.fireZoneConfig.fire_speed);
            if (forglory_fireRadius >= ModConfig.get().featConfig.fireZoneConfig.radius) {
                NetworkInit.playSoundWide(SoundsInit.FIRE_ZONE_PULSE_ID, (ServerPlayerEntity) (Object) this, false);
                forglory_fireRadius = forglory_fireRadius % ModConfig.get().featConfig.fireZoneConfig.radius;
            }

            final int spawnFire = ModConfig.get().featConfig.fireZoneConfig.fire_rate * (int) forglory_fireRadius;
            final double angle = (2 * Math.PI) / spawnFire;
            BlockPos blockPos;

            for (int i = 0; i < spawnFire; i++) {
                blockPos = this.getBlockPos().add(forglory_fireRadius * Math.cos(i * angle),
                        0,
                        forglory_fireRadius * Math.sin(i * angle));
                spawnFireZ(blockPos);
            }
        }
        else {
            forglory_firstTime_FZ = true;
        }
    }

    private void spawnFireZ(BlockPos blockPos) {
        BlockPos belowBlockPos = blockPos.down();

        if (this.world.getBlockState(blockPos).isAir()
                && !this.world.getBlockState(belowBlockPos).isAir()
                && !this.world.getBlockState(belowBlockPos).getMaterial().isLiquid()
                && !this.world.getBlockState(belowBlockPos).getBlock().equals(BlocksInit.quickFireBlock)) {
            this.world.setBlockState(blockPos,
                    BlocksInit.quickFireBlock.getDefaultState().with(QuickFireBlock.SHORT, true));
        } else {
            for (int i = 1; i < 3; i++) {
                if (this.world.getBlockState(blockPos.down(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.down(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.down(i)).getMaterial().isLiquid()
                        && !this.world.getBlockState(belowBlockPos.down(i)).getBlock().equals(BlocksInit.quickFireBlock)) {
                    this.world.setBlockState(blockPos.down(i),
                            BlocksInit.quickFireBlock.getDefaultState().with(QuickFireBlock.SHORT, true));
                    break;
                } else if (this.world.getBlockState(blockPos.up(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.up(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.up(i)).getMaterial().isLiquid()
                        && !this.world.getBlockState(belowBlockPos.up(i)).getBlock().equals(BlocksInit.quickFireBlock)) {
                    this.world.setBlockState(blockPos.up(i),
                            BlocksInit.quickFireBlock.getDefaultState().with(QuickFireBlock.SHORT, true));
                    break;
                }
            }
        }
    }
}
