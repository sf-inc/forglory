package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class InstantKillMixin {
    @Shadow public abstract float getHealth();
    @Shadow public abstract float getMaxHealth();

    @ModifyArg(method = "damage", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private float injectedAmount(DamageSource source, float amount) {
        if (Utils.canUseFeat(source.getAttacker(), Feats.INSTANT_KILL)) {
            if (this.getHealth() <= Math.min((ModConfig.get().featConfig.instantKillConfig.health_percentage / 100.f) * this.getMaxHealth(),
                    ModConfig.get().featConfig.instantKillConfig.max_damage)) {
                amount = this.getMaxHealth();
                // FIXME: Replace with world sound
                //NetworkInit.playSound(SoundsInit.INSTANT_KILLED_ID, (ServerPlayerEntity) source.getAttacker());
            }
        }

        return amount;
    }
}
