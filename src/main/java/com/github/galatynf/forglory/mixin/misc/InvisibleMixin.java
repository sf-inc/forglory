package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class InvisibleMixin extends LivingEntity {
    @Unique
    private boolean forglory_first_time = true;

    protected InvisibleMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void addInvisibleEffect(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.INVISIBLE)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 30));
            this.fallDistance = 0;
            if (this.forglory_first_time) {
                this.playSound(SoundRegistry.INVISIBLE);
                this.forglory_first_time = false;
            }
        } else {
            this.forglory_first_time = true;
        }
    }
}
