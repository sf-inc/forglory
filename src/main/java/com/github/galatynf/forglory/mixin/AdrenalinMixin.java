package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
public abstract class AdrenalinMixin extends LivingEntity {

    @Shadow protected abstract SoundEvent getHighSpeedSplashSound();
    @Shadow public abstract float getMovementSpeed();
    @Shadow public abstract boolean isCreative();

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Unique
    protected boolean[] forglory_soundPlayed = this.initialiseSoundPlayed();

    protected AdrenalinMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    private boolean[] initialiseSoundPlayed() {
        boolean[] ret = new boolean[4];
        for (int i = 0; i < 4; ++i) {
            ret[i] = false;
        }
        return ret;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void incrementWhenSprinting(CallbackInfo ci) {
        int threshold = ModConfig.get().adrenalinConfig.tier2_threshold;
        if (MyComponents.ADRENALIN.get(this).getAdrenalin() < threshold + (float)threshold/10
                && this.isSprinting()) {
            float amount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, ModConfig.get().adrenalinConfig.sprint_gain / 10.F);
            MyComponents.ADRENALIN.get(this).addAdrenalin(amount);
        }
    }

    @Inject(method = "jump", at = @At("HEAD"))
    private void incrementWhenJumping(CallbackInfo ci) {
        int threshold = ModConfig.get().adrenalinConfig.tier2_threshold;
        if (MyComponents.ADRENALIN.get(this).getAdrenalin() < threshold + (float)threshold/10) {
            float amount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, ModConfig.get().adrenalinConfig.jump_gain);
            MyComponents.ADRENALIN.get(this).addAdrenalin(amount);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void incrementWhenAttacked(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof LivingEntity && !this.isCreative()) {
            float adrenalinAmount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, amount * ModConfig.get().adrenalinConfig.damage_multiplier);
            MyComponents.ADRENALIN.get(this).addAdrenalin(adrenalinAmount);
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"))
    private void incrementWhenFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource,
                                         CallbackInfoReturnable<Boolean> cir) {
        if (!this.isCreative()) {
            float amount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, fallDistance * ModConfig.get().adrenalinConfig.fall_multiplier);
            MyComponents.ADRENALIN.get(this).addAdrenalin(amount>50 ? 50 : amount);
        }
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    private void incrementWhenEating(World world, ItemStack stack, FoodComponent foodComponent,
                                     CallbackInfoReturnable<ItemStack> cir) {
        if (MyComponents.ADRENALIN.get(this).getAdrenalin() < ModConfig.get().adrenalinConfig.tier1_threshold
                && stack.isOf(Items.GOLDEN_APPLE)) {
            MyComponents.ADRENALIN.get(this).setAdrenalin(ModConfig.get().adrenalinConfig.tier1_threshold);
        } else if (MyComponents.ADRENALIN.get(this).getAdrenalin() < ModConfig.get().adrenalinConfig.tier3_threshold
                && stack.isOf(Items.ENCHANTED_GOLDEN_APPLE)) {
            MyComponents.ADRENALIN.get(this).setAdrenalin(ModConfig.get().adrenalinConfig.tier3_threshold);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void loseAdrenalin(CallbackInfo ci) {
        if (isSneaking()) {
            MyComponents.ADRENALIN.get(this).addAdrenalin(ModConfig.get().adrenalinConfig.quick_loss);
        } else {
            MyComponents.ADRENALIN.get(this).addAdrenalin(ModConfig.get().adrenalinConfig.natural_loss);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void playSounds(CallbackInfo ci) {
        if (this.getWorld().isClient()) {
            if (ModConfig.get().guiSoundsConfig.enable_tier_jingles) {
                if (MyComponents.ADRENALIN.get(this).getAdrenalin() > ModConfig.get().adrenalinConfig.tier1_threshold) {
                    if (!forglory_soundPlayed[0]) {
                        playSound(SoundRegistry.tier_1_whoosh_event, 1.2F, 1F);
                        forglory_soundPlayed[0] = true;
                    }
                } else {
                    forglory_soundPlayed[0] = false;
                }
                if (MyComponents.ADRENALIN.get(this).getAdrenalin() > ModConfig.get().adrenalinConfig.tier2_threshold) {
                    if (!forglory_soundPlayed[1]) {
                        playSound(SoundRegistry.tier_2_bass_event, 1F, 1F);
                        forglory_soundPlayed[1] = true;
                    }
                } else {
                    forglory_soundPlayed[1] = false;
                }
                if (MyComponents.ADRENALIN.get(this).getAdrenalin() > ModConfig.get().adrenalinConfig.tier3_threshold) {
                    if (!forglory_soundPlayed[2]) {
                        playSound(SoundRegistry.tier_3_strong_bass_event, 1F, 1F);
                        forglory_soundPlayed[2] = true;
                    }
                } else {
                    forglory_soundPlayed[2] = false;
                }
                if (MyComponents.ADRENALIN.get(this).getAdrenalin() > ModConfig.get().adrenalinConfig.tier4_threshold) {
                    if (!forglory_soundPlayed[3]) {
                        playSound(SoundRegistry.tier_4_overcharged_event, 1F, 1F);
                        forglory_soundPlayed[3] = true;
                    }
                } else {
                    forglory_soundPlayed[3] = false;
                }
            }
        }
    }
}
