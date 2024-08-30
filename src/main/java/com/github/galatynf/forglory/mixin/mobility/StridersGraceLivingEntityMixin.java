package com.github.galatynf.forglory.mixin.mobility;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class StridersGraceLivingEntityMixin extends Entity {
    @Shadow public abstract Vec3d applyFluidMovingSpeed(double gravity, boolean falling, Vec3d motion);

    public StridersGraceLivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @WrapOperation(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;multiply(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 1))
    private Vec3d updateTravelOutOfLava(Vec3d instance, double x, double y, double z, Operation<Vec3d> original) {
        if (Utils.canUseFeat(this, Feats.STRIDERS_GRACE, false)) {
            double newX = x + 0.1 * ModConfig.get().featConfig.stridersGraceSpeed;
            double newZ = z + 0.1 * ModConfig.get().featConfig.stridersGraceSpeed;
            if (this.isSprinting()) {
                newX += 0.1;
                newZ += 0.1;
            }
            return original.call(instance, newX, y, newZ);
        } else {
            return original.call(instance, x, y, z);
        }
    }

    @WrapOperation(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", ordinal = 4))
    private void updateTravelInLava(LivingEntity instance, Vec3d vec3d, Operation<Void> original) {
        if (Utils.canUseFeat(this, Feats.STRIDERS_GRACE, false)) {
            boolean falling = this.getVelocity().y <= 0.0;
            double multiplier = 0.5 + 0.1 * ModConfig.get().featConfig.stridersGraceSpeed;
            if (this.isSprinting()) {
                multiplier += 0.1;
            }
            original.call(instance, this.getVelocity().multiply(multiplier, 0.8, multiplier));
            this.setVelocity(this.applyFluidMovingSpeed(this.getFinalGravity(), falling, this.getVelocity()));
        } else {
            original.call(instance, vec3d);
        }
    }
}
