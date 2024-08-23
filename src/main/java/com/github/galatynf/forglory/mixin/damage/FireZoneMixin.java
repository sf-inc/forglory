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
                NoMixinFeats.spawnQuickFire(this.getWorld(), blockPos, true);
            }
        }
        else {
            this.forglory_firstTime_FZ = true;
        }
    }
}
