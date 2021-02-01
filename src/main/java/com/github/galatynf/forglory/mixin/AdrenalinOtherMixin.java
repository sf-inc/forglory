package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class AdrenalinOtherMixin {
    @Inject(at = @At("HEAD"), method = "damage")
    private void incrementPlayerWhenAttacking(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if (source.getAttacker() instanceof PlayerEntity) {
            float adrenalin = Math.min(amount * ModConfig.get().adrenalinConfig.attack_multiplier, 25);
            ((IAdrenalinMixin) source.getAttacker()).addAdrenalin(adrenalin);
        }
    }
}
