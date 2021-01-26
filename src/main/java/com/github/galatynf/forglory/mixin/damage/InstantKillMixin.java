package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class InstantKillMixin {
    @Shadow public abstract float getHealth();

    @Shadow public abstract float getMaxHealth();

    @ModifyArg(method = "damage", at=@At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private float injectedAmount(DamageSource source, float amount) {
        if (source.getAttacker() == null) return amount;
        if (source.getAttacker() instanceof PlayerEntity) {
            Feats feat = ((IFeatsMixin) source.getAttacker()).getFeat(Tier.TIER4);
            if (feat == null) return amount;
            if (feat.equals(Feats.INSTANT_KILL)) {
                if (((IAdrenalinMixin) source.getAttacker()).getAdrenalin() > Tier.TIER4.threshold
                        && ((IFeatsMixin) source.getAttacker()).getCooldown(Tier.TIER4) == 0) {
                    ((IFeatsMixin) source.getAttacker()).resetCooldown(Tier.TIER4);
                    if (this.getHealth() <= Math.min((ModConfig.get().featConfig.instantKillConfig.health_percentage / 100.f) * this.getMaxHealth(),
                            ModConfig.get().featConfig.instantKillConfig.max_damage)) {
                        amount = this.getMaxHealth();
                    }
                }
            }
        }
        return amount;
    }
}
