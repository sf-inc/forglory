package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(BeeEntity.class)
public abstract class BeesEntityMixin extends LivingEntity implements Angerable {
    @Shadow public abstract UUID getAngryAt();

    @Shadow public abstract void setAngryAt(@Nullable UUID uuid);
    @Shadow public abstract void setAngerTime(int ticks);

    protected BeesEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void findTarget(CallbackInfo ci) {
        UUID uuid = MyComponents.SUMMONED.get(this).getPlayer();
        if (uuid != null) {
            PlayerEntity playerEntity = this.getWorld().getPlayerByUuid(uuid);
            if (playerEntity != null) {
                if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() < Feats.BEES.tier.threshold) {
                    this.kill();
                } else if (getAngryAt() == null) {
                    double distance = this.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
                    LivingEntity targetEntity = this.getWorld().getClosestEntity(HostileEntity.class,
                            TargetPredicate.DEFAULT.setBaseMaxDistance(distance),
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
    }

    @Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", shift = At.Shift.AFTER))
    private void changeStungEffect(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (MyComponents.SUMMONED.get(this).getPlayer() != null) {
            ((LivingEntity) target).removeStatusEffect(StatusEffects.POISON);
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 250, 1));
        }
    }
}
