package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.ILastStandMixin;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
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
                PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                PlayerEntity player = world.getPlayerByUuid(this.getUuid());
                ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, Forglory.BERSERK_PACKET_ID, passedData);

                forglory_isInBerserkState = true;
                this.setHealth(0.5F);
                this.clearStatusEffects();
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void manageBerserkState(CallbackInfo ci) {
        if(this.getType().equals(EntityType.PLAYER)) {
            if (((IAdrenalinMixin) this).getAdrenalin() < Feats.LAST_STAND.tier.threshold) {
                forglory_isInBerserkState = false;
            }
            if (forglory_isInBerserkState) {
                // Invulnerable, strength 1, speed 2, , resistance 4, life steal, fire resistance ?
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 30, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30, 3));
                this.addStatusEffect(new StatusEffectInstance(Forglory.lifeStealStatusEffect, 30, 0));
            }
        }
    }
}
