package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import com.github.galatynf.forglory.imixin.ILastStandMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LastStandMixin extends Entity implements ILastStandMixin {

    public LastStandMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract boolean isDead();

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract void setHealth(float health);

    @Shadow public abstract boolean clearStatusEffects();

    @Unique
    private boolean isInBerserkState = false;

    @Override
    public boolean isBerserk() {
        return isInBerserkState;
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isDead()Z"), cancellable = true)
    private void becomeBerserk(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(this.getType().equals(EntityType.PLAYER)) {
            if (this.isDead()) {
                Feats feat = ((IFeatsMixin) this).getFeat(Tier.TIER4);
                if (feat == null) return;
                if (feat.equals(Feats.LAST_STAND)) {
                    if (((IAdrenalinMixin) this).getAdrenalin() > Tier.TIER4.threshold) {
                        if(!isInBerserkState) {
                            isInBerserkState = true;
                            this.setHealth(0.5F);
                            this.clearStatusEffects();
                            cir.setReturnValue(false);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void manageBerserkState(CallbackInfo ci) {
        //System.out.println("LS :" + isInBerserkState);
        if(this.getType().equals(EntityType.PLAYER)) {
            if (((IAdrenalinMixin) this).getAdrenalin() < Tier.TIER4.threshold) {
                isInBerserkState = false;
            }
            if (isInBerserkState) {
                // Invulnerable, strength 1, speed 2, , resistance 4, life steal, fire resistance ?
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20, 3));
                // TODO Life Steal status effect
            }
        }
    }
}
