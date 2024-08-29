package com.github.galatynf.forglory.mixin.mobility;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public class NoHungerMixin {
    @Shadow protected HungerManager hungerManager;

    @WrapOperation(method = "addExhaustion", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;addExhaustion(F)V"))
    private void preventHungerFromDropping(HungerManager instance, float exhaustion, Operation<Void> original) {
        if (Utils.canUseFeat(this, Feats.NO_HUNGER)
                && this.hungerManager.getFoodLevel() < 7) {
            this.hungerManager.setFoodLevel(7);
        } else if (this.hungerManager.getFoodLevel() > 7){
            original.call(instance, exhaustion);
        }
    }
}
