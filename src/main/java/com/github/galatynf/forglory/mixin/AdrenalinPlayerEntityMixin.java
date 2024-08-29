package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class AdrenalinPlayerEntityMixin extends LivingEntity {
    @Shadow public abstract boolean isCreative();

    @Unique
    protected ArrayList<Boolean> forglory_soundPlayed = new ArrayList<>(List.of(false, false, false, false));

    protected AdrenalinPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void incrementWhenSprinting(CallbackInfo ci) {
        float threshold = Tier.TIER2.getThreshold() * 1.1f;
        if (MyComponents.ADRENALIN.get(this).getAdrenalin() < threshold
                && this.isSprinting()) {
            float amount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, ModConfig.get().adrenalinConfig.sprintGain / 10.F);
            MyComponents.ADRENALIN.get(this).addAdrenalin(amount);
        }
    }

    @Inject(method = "jump", at = @At("HEAD"))
    private void incrementWhenJumping(CallbackInfo ci) {
        float threshold = Tier.TIER2.getThreshold() * 1.1f;
        if (MyComponents.ADRENALIN.get(this).getAdrenalin() < threshold) {
            float amount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, ModConfig.get().adrenalinConfig.jumpGain);
            MyComponents.ADRENALIN.get(this).addAdrenalin(amount);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void incrementWhenAttacked(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof LivingEntity && !this.isCreative()) {
            float adrenalinAmount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, amount * ModConfig.get().adrenalinConfig.damageMultiplier);
            MyComponents.ADRENALIN.get(this).addAdrenalin(adrenalinAmount);
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"))
    private void incrementWhenFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource,
                                         CallbackInfoReturnable<Boolean> cir) {
        if (!this.isCreative() && fallDistance > 2.f) {
            float amount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this,
                    (fallDistance - 2.f) * ModConfig.get().adrenalinConfig.fallMultiplier);
            MyComponents.ADRENALIN.get(this).addAdrenalin(Math.min(amount, 50));
        }
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    private void incrementWhenEating(World world, ItemStack stack, FoodComponent foodComponent,
                                     CallbackInfoReturnable<ItemStack> cir) {
        if (MyComponents.ADRENALIN.get(this).getAdrenalin() < Tier.TIER1.getThreshold()
                && stack.isOf(Items.GOLDEN_APPLE)) {
            MyComponents.ADRENALIN.get(this).setAdrenalin(Tier.TIER1.getThreshold());
        } else if (MyComponents.ADRENALIN.get(this).getAdrenalin() < Tier.TIER3.getThreshold()
                && stack.isOf(Items.ENCHANTED_GOLDEN_APPLE)) {
            MyComponents.ADRENALIN.get(this).setAdrenalin(Tier.TIER3.getThreshold());
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void loseAdrenalin(CallbackInfo ci) {
        MyComponents.ADRENALIN.get(this).addAdrenalin(this.isSneaking()
                ? ModConfig.get().adrenalinConfig.quickLoss
                : ModConfig.get().adrenalinConfig.naturalLoss);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void playSounds(CallbackInfo ci) {
        if (!this.getWorld().isClient() || !ModConfig.get().guiSoundsConfig.enableTierJingles) return;

        for (Tier tier : Tier.values()) {
            if (MyComponents.ADRENALIN.get(this).getAdrenalin() > tier.getThreshold()) {
                if (!this.forglory_soundPlayed.get(tier.ordinal())) {
                    this.playSound(tier.getSoundEvent());
                    this.forglory_soundPlayed.set(tier.ordinal(), true);
                }
            } else {
                this.forglory_soundPlayed.set(tier.ordinal(), false);
            }
        }
    }
}
