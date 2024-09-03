package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class DamageToSlowedMixin extends Entity {
    @Shadow public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);
    @Shadow @Nullable public abstract StatusEffectInstance getStatusEffect(RegistryEntry<StatusEffect> effect);

    public DamageToSlowedMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void injectedAmount(LivingEntity instance, DamageSource source, float amount, Operation<Void> original) {
        if (Utils.canUseFeat(source.getAttacker(), Feats.DAMAGE_SLOWED)
                && this.hasStatusEffect(StatusEffects.SLOWNESS)) {
            float mult = Objects.requireNonNull(this.getStatusEffect(StatusEffects.SLOWNESS)).getAmplifier() / 2.0F;
            amount = amount * (1 + Math.min(mult, 2));
        }

        original.call(instance, source, amount);
    }
}
