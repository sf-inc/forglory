package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IShieldMixin;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ShieldPlayerMixin extends LivingEntity implements IShieldMixin {
    @Unique
    int forglory_lastBlocked = 0;

    protected ShieldPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void attack(Entity target);

    @Override
    public void resetBlockedTicks() {
        forglory_lastBlocked = ModConfig.get().featConfig.superShieldConfig.ticks_before_attack;
    }

    @Override
    public int getBlockedTicks() {
        return forglory_lastBlocked;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void decrementBlock(CallbackInfo ci) {
        forglory_lastBlocked--;
        if (forglory_lastBlocked <= 0)
            forglory_lastBlocked = 0;
    }

    @Inject(at = @At("HEAD"), method = "takeShieldHit")
    public void counterattack(LivingEntity attacker, CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.SUPER_SHIELD)) {
            if (forglory_lastBlocked != 0) {
                NetworkInit.playSound(SoundsInit.SHIELD_FLIP_ID, (ServerPlayerEntity) (Object) this);
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1));
                this.attack(attacker);
            }
        }
    }
}
