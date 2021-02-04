package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(WolfEntity.class)
public abstract class DogMixin extends LivingEntity {
    protected DogMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void invincibleDog(CallbackInfo ci) {
        UUID uuid = MyComponents.SUMMONED.get(this).getPlayer();
        if (uuid != null) {
            PlayerEntity playerEntity = this.world.getPlayerByUuid(uuid);
            if (playerEntity != null) {
                if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() < Feats.DOG.tier.threshold) {
                    this.setInvulnerable(false);
                    this.kill();
                    MyComponents.FEATS.get(playerEntity).resetCooldown(Feats.DOG.tier);
                } else {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 30, 0));
                }
            }
        }
    }
}
