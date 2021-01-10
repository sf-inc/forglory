package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import com.github.galatynf.forglory.imixin.IShieldMixin;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public class ShieldEnemyMixin {
    @ModifyArg(method = "damage", at=@At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private float moreDamageIfCountered(DamageSource source, float amount) {
        if(source.getAttacker() == null || !(source.getAttacker() instanceof PlayerEntity)) {
            return amount;
        }
        PlayerEntity attacker = (PlayerEntity) source.getAttacker();
        Feats feat = ((IFeatsMixin)attacker).getFeat(Tier.TIER3);
        if (feat == null) return amount;
        if (feat.equals(Feats.SHIELD)) {
            if (((IAdrenalinMixin) attacker).getAdrenalin() > Tier.TIER3.threshold) {
                if (((IShieldMixin)attacker).getBlockedTicks() != 0) {
                    ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
                    amount += config.superShieldConfig.damage_added_on_counterattack;
                }
            }
        }
        return amount;
    }
}
