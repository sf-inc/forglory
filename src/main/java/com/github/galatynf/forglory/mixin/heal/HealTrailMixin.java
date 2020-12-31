package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.config.constants.FeatsConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
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

    protected HealTrailMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void spawnHealTrail(CallbackInfo ci) {
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER4);
        if (feat == null) return;
        if (feat.equals(Feats.HEAL_TRAIL)) {
            if (((IAdrenalinMixin) this).getAdrenalin() > Tier.TIER4.threshold) {
                if (forglory_lastSpawned >= FeatsConfig.HEAL_TRAIL_FREQUENCY && this.isOnGround()) {
                    AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
                    areaEffectCloudEntity.setOwner(this);
                    areaEffectCloudEntity.setRadius(FeatsConfig.HEAL_TRAIL_RADIUS);
                    areaEffectCloudEntity.setRadiusOnUse(-0.5F);
                    areaEffectCloudEntity.setWaitTime(FeatsConfig.HEAL_TRAIL_WAIT_TIME);
                    areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
                    areaEffectCloudEntity.setDuration(10);
                    areaEffectCloudEntity.setParticleType(ParticleTypes.HAPPY_VILLAGER);

                    areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0));

                    this.world.spawnEntity(areaEffectCloudEntity);
                    forglory_lastSpawned = 0;
                }
                forglory_lastSpawned++;
            }
        }
    }
}
