package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class AdrenalinMixin extends LivingEntity implements IAdrenalinMixin {

    @Shadow protected abstract SoundEvent getHighSpeedSplashSound();

    @Shadow public abstract float getMovementSpeed();

    @Unique
    protected double forglory_adrenalin = 0;

    protected AdrenalinMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void incrementWhenSprinting(CallbackInfo ci) {
        if (this.isSprinting())
            addAdrenalin(1.1);
    }

    @Inject(at = @At("HEAD"), method = "jump")
    private void incrementWhenJumping(CallbackInfo ci) {
        addAdrenalin(10);
    }

    @Inject(at = @At("HEAD"), method = "damage")
    private void incrementWhenAttacked(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(source.getAttacker() instanceof LivingEntity)
            addAdrenalin(amount * 10);
    }

    @Inject(at = @At("HEAD"), method = "handleFallDamage")
    private void incrementWhenFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> cir) {
        addAdrenalin(fallDistance * 5);
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void loseAdrenalin(CallbackInfo ci) {
        if (isSneaking())
            addAdrenalin(-50);
        else
            addAdrenalin(-1);
    }

    @Override
    public double getAdrenalin() {
        return forglory_adrenalin;
    }

    @Override
    public void addAdrenalin(final double amount) {
        forglory_adrenalin += amount;
        if(forglory_adrenalin > 10000) {
            forglory_adrenalin = 10000;
        }
        if(forglory_adrenalin < 0) {
            forglory_adrenalin = 0;
        }
    }
}
