package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class InvisibleMixin extends LivingEntity {
    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Unique
    private boolean forglory_first_time = true;

    protected InvisibleMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void addInvisibleEffect(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.INVISIBLE)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 30, 0));
            this.fallDistance = 0;
            if (forglory_first_time) {
                NetworkInit.playSoundWide(SoundsInit.INVISIBLE_ID, (ServerPlayerEntity) (Object) this, false);
                forglory_first_time = false;
            }
        } else {
            forglory_first_time = true;
        }
    }
}
