package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IPlayerIDMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfEntity.class)
public abstract class DogMixin extends LivingEntity implements IPlayerIDMixin {
    protected DogMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    private Integer forglory_playerID;

    @Override
    public Integer getPlayerID () {
        return forglory_playerID;
    }

    @Override
    public void setPlayerID (final Integer playerID) {
        forglory_playerID = playerID;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void invincibleDog(CallbackInfo ci) {
        if (forglory_playerID != null) {
            if (this.world.getEntityById(forglory_playerID) instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) this.world.getEntityById(forglory_playerID);
                if (playerEntity == null) return;

                if (((IAdrenalinMixin) playerEntity).getAdrenalin() < Feats.DOG.tier.threshold) {
                    this.setInvulnerable(false);
                    this.damage(DamageSource.OUT_OF_WORLD, 666);
                    MyComponents.FEATS.get(playerEntity).resetCooldown(Feats.DOG.tier);
                } else {
                    this.applyStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 30, 0));
                }
            }
        } else {
            this.setInvulnerable(false);
            this.damage(DamageSource.OUT_OF_WORLD, 666);
        }
    }
}
