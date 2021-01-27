package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IShieldMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public class ShieldEnemyMixin {
    @ModifyArg(method = "damage", at=@At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private float moreDamageIfCountered(DamageSource source, float amount) {
        if (Utils.canUseFeat(source.getAttacker(), Feats.SUPER_SHIELD)) {
            if (((IShieldMixin) source.getAttacker()).getBlockedTicks() != 0) {
                amount += ModConfig.get().featConfig.superShieldConfig.damage_added_on_counterattack;
            }
        }

        return amount;
    }
}
