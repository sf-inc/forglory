package com.github.galatynf.forglory.mixin.mobility;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class StridersGraceMixin extends LivingEntity {
    protected StridersGraceMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void addStridersGraceEffect(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.STRIDERS_GRACE)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 30, 0));
            if (this.isInLava()) {
                double x = (ModConfig.get().featConfig.striders_grace_speed / 4.0D) * Math.sin((-this.yaw * Math.PI) / 180.0D);
                double y = (ModConfig.get().featConfig.striders_grace_speed / 6.0D) * Math.sin((-this.pitch * Math.PI) / 180.0D);
                y = (0 < this.pitch && this.pitch < 20) ? 0.05 : y;
                double z = (ModConfig.get().featConfig.striders_grace_speed / 4.0D) * Math.cos((-this.yaw * Math.PI) / 180.0D);
                this.setVelocity(x, y, z);
                this.velocityModified = true;
            }
        }
    }
}
