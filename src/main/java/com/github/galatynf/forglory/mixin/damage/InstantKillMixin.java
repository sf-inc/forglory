package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.SoundRegistry;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class InstantKillMixin {
    @Shadow public abstract float getHealth();
    @Shadow public abstract float getMaxHealth();

    @Shadow public abstract void playSound(@Nullable SoundEvent sound);

    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void injectedAmount(LivingEntity instance, DamageSource source, float amount, Operation<Void> original) {
        if (Utils.canUseFeat(source.getAttacker(), Feats.INSTANT_KILL)) {
            if (this.getHealth() <= Math.min((ModConfig.get().featConfig.instantKill.healthPercentage / 100.f) * this.getMaxHealth(),
                    ModConfig.get().featConfig.instantKill.maxDamage)) {
                amount = this.getMaxHealth();
                this.playSound(SoundRegistry.INSTANT_KILLED);
            }
        }

        original.call(instance, source, amount);
    }
}
