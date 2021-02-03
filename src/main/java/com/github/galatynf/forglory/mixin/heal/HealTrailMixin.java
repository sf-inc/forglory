package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class HealTrailMixin extends LivingEntity {
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Unique
    private int forglory_lastSpawned = 0;

    @Unique
    private boolean firstTime = true;

    protected HealTrailMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void spawnHealTrail(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.HEAL_TRAIL)) {
            if(firstTime) {
                playSound(SoundsInit.heal_trail, 1, 1);
                firstTime = false;
            }
            if (forglory_lastSpawned >= ModConfig.get().featConfig.healTrailConfig.heal_trail_frequency && this.isOnGround()) {
                AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
                areaEffectCloudEntity.setOwner(this);
                areaEffectCloudEntity.setRadius(ModConfig.get().featConfig.healTrailConfig.heal_trail_radius);
                areaEffectCloudEntity.setRadiusOnUse(-0.5F);
                areaEffectCloudEntity.setWaitTime(ModConfig.get().featConfig.healTrailConfig.heal_trail_wait_time);
                areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
                areaEffectCloudEntity.setDuration(10);
                areaEffectCloudEntity.setParticleType(ParticleTypes.HAPPY_VILLAGER);

                areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));

                this.world.spawnEntity(areaEffectCloudEntity);
                forglory_lastSpawned = 0;
            }
            forglory_lastSpawned++;
        }
        else {
            firstTime = true;
        }
    }
}
