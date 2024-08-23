package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IShieldMixin;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ShieldPlayerMixin extends LivingEntity implements IShieldMixin {
    @Shadow public abstract void attack(Entity target);

    @Unique
    int forglory_lastBlocked = 0;

    protected ShieldPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void forglory$resetBlockedTicks() {
        this.forglory_lastBlocked = ModConfig.get().featConfig.superShieldConfig.ticks_before_attack;
    }

    @Override
    public int forglory$getBlockedTicks() {
        return this.forglory_lastBlocked;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void decrementBlock(CallbackInfo ci) {
        this.forglory_lastBlocked--;
        if (this.forglory_lastBlocked <= 0)
            this.forglory_lastBlocked = 0;
    }

    @Inject(method = "takeShieldHit", at = @At("HEAD"))
    public void counterattack(LivingEntity attacker, CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.SUPER_SHIELD)) {
            if (this.forglory_lastBlocked != 0) {
                this.playSound(SoundRegistry.SHIELD_FLIP);
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1));
                this.attack(attacker);
            }
        }
    }
}
