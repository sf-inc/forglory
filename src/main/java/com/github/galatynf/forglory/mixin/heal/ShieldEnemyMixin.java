package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IShieldMixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class ShieldEnemyMixin {
    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void moreDamageIfCountered(LivingEntity instance, DamageSource source, float amount, Operation<Void> original) {
        if (Utils.canUseFeat(source.getAttacker(), Feats.SUPER_SHIELD)
                && ((IShieldMixin) source.getAttacker()).forglory$getBlockedTicks() > 0) {
            amount += ModConfig.get().feats.superShield.damageAddedOnCounterattack;
        }

        original.call(instance, source, amount);
    }
}
