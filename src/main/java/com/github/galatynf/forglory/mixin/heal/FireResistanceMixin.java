package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class FireResistanceMixin extends LivingEntity {
    protected FireResistanceMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at=@At("INVOKE"), method = "tick")
    void addSpeedEffect(CallbackInfo ci) {
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER2);
        if (feat == null) return;
        if (feat.equals(Feats.FIRE_RESISTANCE)) {
            if (((IAdrenalinMixin)this).getAdrenalin() > Tier.TIER2.threshold) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1, 0));
            }
        }
    }
}
