package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class SummonDogMixin extends LivingEntity {
    protected SummonDogMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void summonTheDog(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.DOG)) {
            WolfEntity wolfEntity = EntityType.WOLF.spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.MOB_SUMMONED);
            if (wolfEntity == null) return;

            wolfEntity.setTamed(true, false);
            wolfEntity.setOwnerUuid(this.getUuid());
            wolfEntity.setInvulnerable(true);
            wolfEntity.setGlowing(true);
            wolfEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, -1));

            MyComponents.SUMMONED.get(wolfEntity).setPlayer(this.getUuid());
            MyComponents.FEATS.get(this).setUniqueCooldown(Feats.DOG.tier);
        }
    }
}
