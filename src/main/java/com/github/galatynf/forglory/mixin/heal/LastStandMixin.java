package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.ILastStandMixin;
import com.github.galatynf.forglory.init.SoundRegistry;
import com.github.galatynf.forglory.init.StatusEffectRegistry;
import com.github.galatynf.forglory.mixin.LivingEntityMixin;
import com.github.galatynf.forglory.network.BerserkPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class LastStandMixin extends LivingEntityMixin implements ILastStandMixin {
    @Unique
    private boolean forglory_isInBerserkState = false;
    @Unique
    private int forglory_berserkTimer = ModConfig.get().featConfig.secondsOfLastStanding * 20;

    public LastStandMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public boolean forglory$isBerserk() {
        return this.forglory_isInBerserkState;
    }

    @Override
    public void forglory$setBerserk(boolean berserk) {
        this.forglory_isInBerserkState = berserk;
    }

    @Override
    protected void beforeDeathOnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (Utils.canUseFeat(this, Feats.LAST_STAND)) {
            if (this.isDead() && !this.forglory_isInBerserkState) {
                ServerPlayerEntity player = (ServerPlayerEntity) this.getWorld().getPlayerByUuid(this.getUuid());
                if (player == null) return;

                ServerPlayNetworking.send(player, new BerserkPayload(true));
                this.forglory_isInBerserkState = true;

                // TODO: Maybe add a timer before or after decreasing adrenalin to avoid
                //       the berserk state lasting only one tick (and so the overlay)
                MyComponents.ADRENALIN.get(this).setAdrenalin(Tier.getValueBetween(Tier.TIER3, Tier.TIER4));
                this.setHealth(0.5f);
                this.clearStatusEffects();
                this.playSound(SoundRegistry.LAST_STANDING);
                if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.BERSERKER) {
                    this.playSound(SoundRegistry.LAST_STANDING_VOICE);
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void manageBerserkState(CallbackInfo ci) {
        if (MyComponents.ADRENALIN.get(this).getAdrenalin() < Feats.LAST_STAND.tier.getThreshold()) {
            this.forglory_isInBerserkState = false;

            if (!this.getWorld().isClient()) {
                ServerPlayerEntity player = (ServerPlayerEntity) this.getWorld().getPlayerByUuid(this.getUuid());
                if (player != null) {
                    ServerPlayNetworking.send(player, new BerserkPayload(false));
                }
            }
        }
        if (this.forglory_isInBerserkState) {
            // Invulnerable, strength 1, speed 2, , resistance 4, life steal, fire resistance ?
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30, 0));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 30, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30, 3));
            this.addStatusEffect(new StatusEffectInstance(StatusEffectRegistry.LIFE_STEAL, 30, 0));
            this.forglory_berserkTimer--;
            if (this.forglory_berserkTimer == 0) {
                this.forglory_berserkTimer = ModConfig.get().featConfig.secondsOfLastStanding * 20;
                this.forglory_isInBerserkState = false;
                MyComponents.ADRENALIN.get(this).setAdrenalin(
                        (Tier.TIER4.getThreshold() - Tier.TIER3.getThreshold()) / 2.f);
                if (this.getHealth() != this.getMaxHealth()) {
                    //Dude, you ran away from the fight, that's not really brave
                    this.setHealth(1);
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 300, 2));
                } else {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                }
            }
        }
    }
}
