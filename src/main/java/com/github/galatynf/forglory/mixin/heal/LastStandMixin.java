package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.ILastStandMixin;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import com.github.galatynf.forglory.init.StatusEffectsInit;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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
    private boolean forglory_isInBerserkState = false;

    @Override
    public boolean isBerserk() {
        return forglory_isInBerserkState;
    }

    @Override
    public void setBerserk() {
        forglory_isInBerserkState = true;
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isDead()Z"), cancellable = true)
    private void becomeBerserk(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (Utils.canUseFeat(this, Feats.LAST_STAND)) {
            if (this.isDead() && !forglory_isInBerserkState) {
                ServerPlayerEntity player = (ServerPlayerEntity) world.getPlayerByUuid(this.getUuid());
                if (player == null) return;
                ServerPlayNetworking.send(player, NetworkInit.BERSERK_PACKET_ID, PacketByteBufs.empty());

                forglory_isInBerserkState = true;
                this.setHealth(0.5F);
                this.clearStatusEffects();
                NetworkInit.playSound(SoundsInit.LAST_STANDING_ID, (PlayerEntity)(Object) this);
                if(MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.BERSERKER) {
                    NetworkInit.playSound(SoundsInit.LAST_STANDING_VOICE_ID, (PlayerEntity)(Object) this);
                }
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void manageBerserkState(CallbackInfo ci) {
        if(this.getType().equals(EntityType.PLAYER)) {
            if (MyComponents.ADRENALIN.get(this).getAdrenalin() < Feats.LAST_STAND.tier.threshold) {
                forglory_isInBerserkState = false;
            }
            if (forglory_isInBerserkState) {
                // Invulnerable, strength 1, speed 2, , resistance 4, life steal, fire resistance ?
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 30, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30, 3));
                this.addStatusEffect(new StatusEffectInstance(StatusEffectsInit.lifeStealStatusEffect, 30, 0));
            }
        }
    }
}
