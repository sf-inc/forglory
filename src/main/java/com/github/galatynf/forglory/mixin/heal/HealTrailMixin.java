package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

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
                if (forglory_lastSpawned >= 20 && this.isOnGround()) {
                    AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
                    areaEffectCloudEntity.setOwner(this);
                    areaEffectCloudEntity.setRadius(2.0F);
                    areaEffectCloudEntity.setRadiusOnUse(-0.5F);
                    areaEffectCloudEntity.setWaitTime(10);
                    areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
                    areaEffectCloudEntity.setDuration(10);
                    areaEffectCloudEntity.setParticleType(ParticleTypes.HAPPY_VILLAGER);
                    //areaEffectCloudEntity.setPotion(potion);

                    areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0));

                    this.world.spawnEntity(areaEffectCloudEntity);
                    forglory_lastSpawned = 0;
                }
                forglory_lastSpawned++;
            }
        }
    }
}
