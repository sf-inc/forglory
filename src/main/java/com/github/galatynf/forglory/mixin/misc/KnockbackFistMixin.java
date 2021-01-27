package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class KnockbackFistMixin extends Entity{
    @Shadow public abstract void applyStatusEffect(StatusEffectInstance effect);

    public KnockbackFistMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at=@At("HEAD"), method="damage")
    private void stunWhenPunched(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (Utils.canUseFeat(source.getAttacker(), Feats.KNOCKBACK_FIST)) {
            if (((PlayerEntity) source.getAttacker()).getMainHandStack().equals(ItemStack.EMPTY)) {
                this.applyStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 100));
            }
        }
    }
}
