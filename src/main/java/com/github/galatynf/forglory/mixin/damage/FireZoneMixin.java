package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.NoMixinFeats;
import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
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
    int forglory_distance = 0;
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

            if (this.getWorld().getTime() % ModConfig.get().featConfig.fireZoneConfig.speed != 0) return;

            if (++this.forglory_distance >= ModConfig.get().featConfig.fireZoneConfig.range) {
                this.playSound(SoundRegistry.FIRE_ZONE_PULSE);
                this.forglory_distance = this.forglory_distance % ModConfig.get().featConfig.fireZoneConfig.range;
            }

            BlockPos blockPos;
            for (int i = -this.forglory_distance; i <= this.forglory_distance; ++i) {
                for (int j = -this.forglory_distance; j <= this.forglory_distance; ++j) {
                    if (Math.abs(i) != this.forglory_distance && Math.abs(j) != this.forglory_distance) continue;

                    blockPos = this.getBlockPos().add(i, 0, j);
                    NoMixinFeats.spawnQuickFire(this.getWorld(), blockPos, true);
                }
            }
        }
        else {
            this.forglory_firstTime_FZ = true;
        }
    }
}
