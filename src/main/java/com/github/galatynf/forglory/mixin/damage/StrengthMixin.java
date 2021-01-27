package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
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
public abstract class StrengthMixin extends LivingEntity {
    protected StrengthMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void addStrengthEffect(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.STRENGTH)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30, 0));
        }
    }
}
