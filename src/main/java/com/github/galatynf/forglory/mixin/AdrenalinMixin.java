package com.github.galatynf.forglory.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class AdrenalinMixin extends LivingEntity {

    @Shadow protected abstract SoundEvent getHighSpeedSplashSound();

    @Shadow public abstract float getMovementSpeed();

    @Unique
    protected double adrenalin = 0;

    @Unique
    protected boolean overcharged = false;

    protected AdrenalinMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private void incrementAdrenalin(double amount) {
        adrenalin += amount;
        if(adrenalin >= 10000) {
            adrenalin = 10000;
        }
        if(adrenalin <= 0) {
            adrenalin = 0;
        }
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void addAdrenalin(CallbackInfo ci) {
        System.out.println(adrenalin);
        if (this.isSprinting()) {
            incrementAdrenalin(1.1);
        }
    }

    @Inject(at = @At("HEAD"), method = "jump")
    private void incrementWhenJumping(CallbackInfo ci) {
        incrementAdrenalin(10);
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void subtractAdrenalin(CallbackInfo ci) {
        incrementAdrenalin(-1);
        if (isSneaking()) {
            incrementAdrenalin(-50);
        }
    }
}
