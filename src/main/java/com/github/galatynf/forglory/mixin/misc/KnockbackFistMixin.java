package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.init.SoundRegistry;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class KnockbackFistMixin extends LivingEntity {
    protected KnockbackFistMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyExpressionValue(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getKnockbackAgainst(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;)F"))
    private float updateKnockbackAndSlow(float original, Entity target) {
        if (Utils.canUseFeat(this, Feats.KNOCKBACK_FIST)
                && this.getMainHandStack().isEmpty()
                && target instanceof LivingEntity livingEntity) {
            if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.CENTURION) {
                this.playSound(SoundRegistry.INCRE);
                livingEntity.playSound(SoundRegistry.DIBILIS);
            }
            this.playSound(SoundRegistry.KNOCKBACK_FIST_ACT);
            livingEntity.playSound(SoundRegistry.KNOCKBACK_FISTED);

            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 100));

            return original + 0.5f;
        }

        return original;
    }
}
