package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IArrowComboMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public abstract class ArrowComboMixin {

    @Shadow @Nullable public abstract Entity getOwner();

    @Inject(at = @At("TAIL"), method="onBlockHit")
    private void resetCombo(BlockHitResult blockHitResult, CallbackInfo ci) {
        Entity owner = this.getOwner();
        if(owner instanceof PlayerEntity && Utils.canUseFeat(owner, Feats.ARROW_COMBO)) {
            ((IArrowComboMixin)owner).resetCombo();
        }
    }

    @Inject(at = @At("HEAD"), method="onEntityHit")
    private void incrementCombo(EntityHitResult entityHitResult, CallbackInfo ci) {
        Entity owner = getOwner();
        if(owner instanceof PlayerEntity && Utils.canUseFeat(owner, Feats.ARROW_COMBO) && entityHitResult.getEntity() instanceof LivingEntity) {
            ((IArrowComboMixin)owner).incrementCombo();
        }
    }
}
