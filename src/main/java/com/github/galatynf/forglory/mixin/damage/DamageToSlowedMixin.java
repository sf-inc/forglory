package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class DamageToSlowedMixin extends Entity {
    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public @Nullable abstract StatusEffectInstance getStatusEffect(StatusEffect effect);

    public DamageToSlowedMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyArg(method = "damage", at=@At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private float injectedAmount(DamageSource source, float amount) {
        if (Utils.canUseFeat(source.getAttacker(), Feats.DAMAGE_SLOWED)) {
            if (this.hasStatusEffect(StatusEffects.SLOWNESS)) {
                return (amount * (1 + Objects.requireNonNull(this.getStatusEffect(StatusEffects.SLOWNESS)).getAmplifier() / 2.0F));
            }
        }

        return amount;
    }
}
