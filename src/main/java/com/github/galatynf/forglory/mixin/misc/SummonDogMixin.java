package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
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
    private void summonTheDog (CallbackInfo ci) {
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER1);
        if (feat == null) return;
        if (feat.equals(Feats.DOG) && ((IFeatsMixin)this).getCooldown(Tier.TIER1) == 0) {
            WolfEntity dog = (WolfEntity)EntityType.WOLF.create(world);
            if(dog==null) {
                System.err.println("Couldn't create dog from dog Mixin");
                return;
            }
            dog.setTamed(true);
            dog.setOwnerUuid(this.getUuid());
            dog.setInvulnerable(true);
            dog.setGlowing(true);
            dog.setInvisible(true);
            dog.initialize(world, world.getLocalDifficulty(this.getBlockPos()), SpawnReason.COMMAND, (EntityData)null, (CompoundTag)null);
            dog.refreshPositionAndAngles(this.getBlockPos(), 0.0F, 0.0F);
            world.spawnEntity(dog);
            ((IFeatsMixin)this).resetCooldown(Tier.TIER1);
        }
    }
}
