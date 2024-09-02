package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
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
        if (uuid == null) return;

        PlayerEntity playerEntity = this.getWorld().getPlayerByUuid(uuid);
        if (playerEntity == null) return;

        if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() < Feats.BEES.tier.getThreshold()) {
            this.kill();
        } else if (this.getAngryAt() == null) {
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

    @WrapOperation(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"))
    private boolean changeStungEffect(LivingEntity instance, StatusEffectInstance effect, Entity source, Operation<Boolean> original) {
        if (MyComponents.SUMMONED.get(this).getPlayer() != null) {
            StatusEffectInstance wither = new StatusEffectInstance(StatusEffects.WITHER, effect.getDuration(), 1);
            return original.call(instance, wither, source);
        }
        return original.call(instance, effect, source);
    }
}
