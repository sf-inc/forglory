package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.cardinal.MyComponents;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class FeatsMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void updateCooldown(CallbackInfo ci) {
        MyComponents.FEATS.get(this).decrementCooldowns();
    }
}
