package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class AdrenalinMixin extends LivingEntity implements IAdrenalinMixin {

    @Shadow protected abstract SoundEvent getHighSpeedSplashSound();

    @Shadow public abstract float getMovementSpeed();

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Unique
    protected float forglory_adrenalin = 0;

    @Unique
    protected boolean[] forglory_soundPlayed = this.initialiseSoundPlayed();

    protected AdrenalinMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private boolean[] initialiseSoundPlayed() {
        boolean[] ret = new boolean[4];
        for(int i = 0; i<4;++i) {
            ret[i] = false;
        }
        return ret;
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void incrementWhenSprinting(CallbackInfo ci) {
        if (forglory_adrenalin < ModConfig.get().adrenalinConfig.tier2_threshold
                && this.isSprinting()) {
            addAdrenalin(ModConfig.get().adrenalinConfig.sprint_gain);
        }
    }

    @Inject(at = @At("HEAD"), method = "jump")
    private void incrementWhenJumping(CallbackInfo ci) {
        if (forglory_adrenalin < ModConfig.get().adrenalinConfig.tier2_threshold) {
            addAdrenalin(ModConfig.get().adrenalinConfig.jump_gain);
        }
    }

    @Inject(at = @At("HEAD"), method = "damage")
    private void incrementWhenAttacked(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof LivingEntity) {
            addAdrenalin(amount * ModConfig.get().adrenalinConfig.damage_multiplier);
        }
    }

    @Inject(at = @At("HEAD"), method = "handleFallDamage")
    private void incrementWhenFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> cir) {
        addAdrenalin(fallDistance * ModConfig.get().adrenalinConfig.fall_multiplier);
    }

    @Inject(at = @At("HEAD"), method = "eatFood")
    private void incrementWhenEating(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (forglory_adrenalin < ModConfig.get().adrenalinConfig.tier1_threshold
                && Objects.equals(stack.getItem().getFoodComponent(), FoodComponents.GOLDEN_APPLE)) {
            forglory_adrenalin = ModConfig.get().adrenalinConfig.tier1_threshold;
        }
        else if (forglory_adrenalin < ModConfig.get().adrenalinConfig.tier3_threshold
                && Objects.equals(stack.getItem().getFoodComponent(), FoodComponents.ENCHANTED_GOLDEN_APPLE)) {
            forglory_adrenalin = ModConfig.get().adrenalinConfig.tier3_threshold;
        }
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void loseAdrenalin(CallbackInfo ci) {
        if (isSneaking()) {
            addAdrenalin(ModConfig.get().adrenalinConfig.quick_loss);
        } else {
            addAdrenalin(ModConfig.get().adrenalinConfig.natural_loss);
        }
    }

    @Override
    public float getAdrenalin() {
        return forglory_adrenalin;
    }

    @Override
    public void addAdrenalin(final float amount) {
        float multiplier = 1;

        Feats feat = MyComponents.FEATS.get(this).getFeat(Feats.BLOODLUST.tier);
        if(feat != null) {
            if (feat.equals(Feats.BLOODLUST) && amount > 0 && this.getHealth() > 0) {
                if (((IAdrenalinMixin) this).getAdrenalin() > Tier.TIER1.threshold) {
                    float value = ModConfig.get().featConfig.bloodlust_multiplier;
                    multiplier = this.getMaxHealth() / (this.getHealth() * value) + 1 - (1 / value);
                }
            }
        }

        forglory_adrenalin += amount*multiplier;

        if(forglory_adrenalin > ModConfig.get().adrenalinConfig.max_amount) {
            forglory_adrenalin = ModConfig.get().adrenalinConfig.max_amount;
        }
        if(forglory_adrenalin < ConstantsConfig.MIN_AMOUNT) {
            forglory_adrenalin = ConstantsConfig.MIN_AMOUNT;
        }
    }

    @Inject(at=@At("HEAD"), method = "tick")
    public void playSoundtest(CallbackInfo ci) {
        if(world.isClient()) {
            if(ModConfig.get().guiSoundsConfig.enable_tier_jingles) {
                if (forglory_adrenalin >= ModConfig.get().adrenalinConfig.tier1_threshold) {
                    if (!forglory_soundPlayed[0]) {
                        playSound(SoundsInit.tier_1_whoosh_event, 1.2F, 1F);
                        forglory_soundPlayed[0] = true;
                    }
                } else {
                    forglory_soundPlayed[0] = false;
                }
                if (forglory_adrenalin >= ModConfig.get().adrenalinConfig.tier2_threshold) {
                    if (!forglory_soundPlayed[1]) {
                        playSound(SoundsInit.tier_2_bass_event, 1F, 1F);
                        forglory_soundPlayed[1] = true;
                    }
                } else {
                    forglory_soundPlayed[1] = false;
                }
                if (forglory_adrenalin >= ModConfig.get().adrenalinConfig.tier3_threshold) {
                    if (!forglory_soundPlayed[2]) {
                        playSound(SoundsInit.tier_3_strong_bass_event, 1F, 1F);
                        forglory_soundPlayed[2] = true;
                    }
                } else {
                    forglory_soundPlayed[2] = false;
                }
                if (forglory_adrenalin >= ModConfig.get().adrenalinConfig.tier4_threshold) {
                    if (!forglory_soundPlayed[3]) {
                        playSound(SoundsInit.tier_4_overcharged_event, 1F, 1F);
                        forglory_soundPlayed[3] = true;
                    }
                } else {
                    forglory_soundPlayed[3] = false;
                }
            }
        }
    }
}
