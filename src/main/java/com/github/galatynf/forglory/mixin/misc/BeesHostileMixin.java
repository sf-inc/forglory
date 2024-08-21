package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class BeesHostileMixin extends Entity {
    public BeesHostileMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void transformIntoBee(DamageSource source, CallbackInfo ci) {
        LivingEntity thisEntity = (LivingEntity) (Object) this;
        if (Utils.canUseFeat(source.getAttacker(), Feats.BEES)) {
            if (thisEntity instanceof HostileEntity) {
                for (int i = 0; i < 3; i++) {
                    BeeEntity beeEntity = EntityType.BEE.spawn((ServerWorld) this.getWorld(), this.getBlockPos().up(), SpawnReason.TRIGGERED);
                    if (beeEntity != null) {
                        MyComponents.SUMMONED.get(beeEntity).setPlayer(source.getAttacker().getUuid());
                    }
                }
            }
        } else if (source.getAttacker() instanceof BeeEntity
                && MyComponents.SUMMONED.get(source.getAttacker()).getPlayer() != null) {
            BeeEntity beeEntity = EntityType.BEE.spawn((ServerWorld) this.getWorld(), this.getBlockPos().up(), SpawnReason.TRIGGERED);
            if (beeEntity != null) {
                MyComponents.SUMMONED.get(beeEntity).setPlayer(MyComponents.SUMMONED.get(source.getAttacker()).getPlayer());
            }
        }
    }
}
