package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.block.QuickFireBlock;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.init.BlockRegistry;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class FireZoneMixin extends LivingEntity {
    @Unique
    float forglory_fireRadius;
    @Unique
    private boolean forglory_firstTime_FZ = true;

    protected FireZoneMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void spawnFireZone(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.FIRE_ZONE)) {
            if (this.forglory_firstTime_FZ) {
                if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.PYROMANIAC) {
                    this.playSound(SoundRegistry.FIRE_ZONE_VOICE);
                }
                this.forglory_firstTime_FZ = false;
            }
            this.forglory_fireRadius += (float) ModConfig.get().featConfig.fireZoneConfig.radius / (100 * ModConfig.get().featConfig.fireZoneConfig.fire_speed);
            if (this.forglory_fireRadius >= ModConfig.get().featConfig.fireZoneConfig.radius) {
                this.playSound(SoundRegistry.FIRE_ZONE_PULSE);
                this.forglory_fireRadius = this.forglory_fireRadius % ModConfig.get().featConfig.fireZoneConfig.radius;
            }

            final int spawnFire = ModConfig.get().featConfig.fireZoneConfig.fire_rate * (int) this.forglory_fireRadius;
            final double angle = (2 * Math.PI) / spawnFire;
            BlockPos blockPos;

            for (int i = 0; i < spawnFire; i++) {
                blockPos = this.getBlockPos().add((int) (this.forglory_fireRadius * Math.cos(i * angle)),
                        0,
                        (int) (this.forglory_fireRadius * Math.sin(i * angle)));
                spawnFireZ(blockPos);
            }
        }
        else {
            this.forglory_firstTime_FZ = true;
        }
    }

    @Unique
    private void spawnFireZ(BlockPos blockPos) {
        BlockPos belowBlockPos = blockPos.down();
        World world = this.getWorld();

        if (world.getBlockState(blockPos).isAir()
                && !world.getBlockState(belowBlockPos).isAir()
                && !world.getBlockState(belowBlockPos).getFluidState().isEmpty()
                && !world.getBlockState(belowBlockPos).getBlock().equals(BlockRegistry.QUICK_FIRE)) {
            world.setBlockState(blockPos,
                    BlockRegistry.QUICK_FIRE.getDefaultState().with(QuickFireBlock.SHORT, true));
        } else {
            for (int i = 1; i < 3; i++) {
                if (world.getBlockState(blockPos.down(i)).isAir()
                        && !world.getBlockState(belowBlockPos.down(i)).isAir()
                        && !world.getBlockState(belowBlockPos.down(i)).getFluidState().isEmpty()
                        && !world.getBlockState(belowBlockPos.down(i)).getBlock().equals(BlockRegistry.QUICK_FIRE)) {
                    world.setBlockState(blockPos.down(i),
                            BlockRegistry.QUICK_FIRE.getDefaultState().with(QuickFireBlock.SHORT, true));
                    break;
                } else if (world.getBlockState(blockPos.up(i)).isAir()
                        && !world.getBlockState(belowBlockPos.up(i)).isAir()
                        && !world.getBlockState(belowBlockPos.up(i)).getFluidState().isEmpty()
                        && !world.getBlockState(belowBlockPos.up(i)).getBlock().equals(BlockRegistry.QUICK_FIRE)) {
                    world.setBlockState(blockPos.up(i),
                            BlockRegistry.QUICK_FIRE.getDefaultState().with(QuickFireBlock.SHORT, true));
                    break;
                }
            }
        }
    }
}
