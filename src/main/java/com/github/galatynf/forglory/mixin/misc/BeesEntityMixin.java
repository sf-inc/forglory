package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IPlayerIDMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.UUID;

@Mixin(BeeEntity.class)
public abstract class BeesEntityMixin extends LivingEntity implements IPlayerIDMixin, Angerable {
    @Shadow public abstract void setAngryAt(@Nullable UUID uuid);

    @Shadow public abstract UUID getAngryAt();

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract void setAngerTime(int ticks);

    @Unique
    private UUID forglory_playerID;

    protected BeesEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setPlayerID(UUID playerID) {
        forglory_playerID = playerID;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void findTarget(CallbackInfo ci) {
        if (forglory_playerID != null) {
            if (((IAdrenalinMixin) Objects.requireNonNull(this.world.getPlayerByUuid(forglory_playerID))).getAdrenalin() < Tier.TIER4.threshold) {
                this.damage(DamageSource.OUT_OF_WORLD, 666);
            }
            else if (getAngryAt() == null) {
                double distance = this.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
                LivingEntity targetEntity = this.world.getClosestEntity(HostileEntity.class,
                        (new TargetPredicate()).setBaseMaxDistance(distance),
                        this,
                        this.getX(),
                        this.getEyeY(),
                        this.getZ(),
                        this.getBoundingBox().expand(distance, 4.0D, distance));
                if (targetEntity != null) {
                    this.setAngryAt(targetEntity.getUuid());
                    this.setTarget(targetEntity);
                    this.setAngerTime(200);
                }
            }
        }
    }

    @Inject(method = "tryAttack",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z", shift = At.Shift.AFTER))
    private void changeStungEffect(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (forglory_playerID != null) {
            ((LivingEntity)target).removeStatusEffect(StatusEffects.POISON);
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 250, 1));
        }
    }
}
