package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(WolfEntity.class)
public abstract class DogMixin extends TameableEntity {
    protected DogMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void invincibleDog(CallbackInfo ci) {
        UUID uuid = MyComponents.SUMMONED.get(this).getPlayer();
        if (uuid == null) return;

        PlayerEntity playerEntity = this.getWorld().getPlayerByUuid(uuid);
        if (playerEntity == null) return;

        if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() < Feats.DOG.tier.getThreshold()) {
            this.setInvulnerable(false);
            this.setOwnerUuid(null);
            this.kill();
            MyComponents.FEATS.get(playerEntity).resetCooldown(Feats.DOG.tier);
        }
    }
}
