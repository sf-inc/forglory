package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class HealTrailMixin extends LivingEntity {
    @Unique
    private int forglory_lastSpawned = 0;
    @Unique
    private boolean forglory_firstTime_HT = true;

    protected HealTrailMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void spawnHealTrail(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.HEAL_TRAIL)) {
            if (this.forglory_firstTime_HT) {
                this.playSound(SoundRegistry.HEAL_TRAIL);
                if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.PALADIN) {
                    this.playSound(SoundRegistry.HEAL_TRAIL_VOICE);
                }
                this.forglory_firstTime_HT = false;
            }

            if (this.forglory_lastSpawned >= ModConfig.get().featConfig.healTrail.healTrailFrequency
                    && this.isOnGround()) {
                AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
                areaEffectCloudEntity.setOwner(this);
                areaEffectCloudEntity.setRadius(ModConfig.get().featConfig.healTrail.healTrailRadius);
                areaEffectCloudEntity.setRadiusOnUse(-0.5F);
                areaEffectCloudEntity.setWaitTime(ModConfig.get().featConfig.healTrail.healTrailWaitTime);
                areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
                areaEffectCloudEntity.setDuration(10);
                areaEffectCloudEntity.setParticleType(ParticleTypes.HAPPY_VILLAGER);
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));

                this.getWorld().spawnEntity(areaEffectCloudEntity);
                this.forglory_lastSpawned = 0;
            }
            this.forglory_lastSpawned++;
        } else {
            this.forglory_firstTime_HT = true;
        }
    }
}
