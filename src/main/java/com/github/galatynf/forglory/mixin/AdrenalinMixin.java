package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.config.constants.AdrenalinConfig;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
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

    @Unique
    protected float forglory_adrenalin = 0;

    protected AdrenalinMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void incrementWhenSprinting(CallbackInfo ci) {
        if (forglory_adrenalin < AdrenalinConfig.TIER2_THRESHOLD
                && this.isSprinting()) {
            addAdrenalin(AdrenalinConfig.SPRINT_GAIN);
        }
    }

    @Inject(at = @At("HEAD"), method = "jump")
    private void incrementWhenJumping(CallbackInfo ci) {
        if (forglory_adrenalin < AdrenalinConfig.TIER2_THRESHOLD) {
            addAdrenalin(AdrenalinConfig.JUMP_GAIN);
        }
    }

    @Inject(at = @At("HEAD"), method = "damage")
    private void incrementWhenAttacked(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof LivingEntity) {
            addAdrenalin(amount * AdrenalinConfig.DAMAGE_MULTIPLIER);
        }
    }

    @Inject(at = @At("HEAD"), method = "handleFallDamage")
    private void incrementWhenFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> cir) {
        addAdrenalin(fallDistance * AdrenalinConfig.FALL_MULTIPLIER);
    }

    @Inject(at = @At("HEAD"), method = "eatFood")
    private void incrementWhenEating(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (forglory_adrenalin < AdrenalinConfig.TIER1_THRESHOLD
                && Objects.equals(stack.getItem().getFoodComponent(), FoodComponents.GOLDEN_APPLE)) {
            forglory_adrenalin = AdrenalinConfig.TIER1_THRESHOLD;
        }
        else if (forglory_adrenalin < AdrenalinConfig.TIER3_THRESHOLD
                && Objects.equals(stack.getItem().getFoodComponent(), FoodComponents.ENCHANTED_GOLDEN_APPLE)) {
            forglory_adrenalin = AdrenalinConfig.TIER3_THRESHOLD;
        }
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void loseAdrenalin(CallbackInfo ci) {
        if (isSneaking()) {
            addAdrenalin(AdrenalinConfig.QUICK_LOSS);
        } else {
            addAdrenalin(AdrenalinConfig.NATURAL_LOSS);
        }
    }

    @Override
    public float getAdrenalin() {
        return forglory_adrenalin;
    }

    @Override
    public void addAdrenalin(final float amount) {
        forglory_adrenalin += amount;
        if(forglory_adrenalin > AdrenalinConfig.MAX_AMOUNT) {
            forglory_adrenalin = AdrenalinConfig.MAX_AMOUNT;
        }
        if(forglory_adrenalin < AdrenalinConfig.MIN_AMOUNT) {
            forglory_adrenalin = AdrenalinConfig.MIN_AMOUNT;
        }
    }
}
