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

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void turnIntoBee(DamageSource source, CallbackInfo ci) {
        LivingEntity thisEntity = (LivingEntity) (Object) this;
        Entity attacker = source.getAttacker();
        if (Utils.canUseFeat(attacker, Feats.BEES)) {
            if (thisEntity instanceof HostileEntity) {
                for (int i = 0; i < 3; ++i) {
                    BeeEntity beeEntity = EntityType.BEE.spawn((ServerWorld) this.getWorld(), this.getBlockPos().up(), SpawnReason.MOB_SUMMONED);
                    if (beeEntity != null) {
                        MyComponents.SUMMONED.get(beeEntity).setPlayer(source.getAttacker().getUuid());
                    }
                }
            }
        } else if (attacker instanceof BeeEntity
                && MyComponents.SUMMONED.get(attacker).getPlayer() != null) {
            BeeEntity beeEntity = (BeeEntity) attacker.getType().spawn((ServerWorld) this.getWorld(), this.getBlockPos().up(), SpawnReason.MOB_SUMMONED);
            if (beeEntity != null) {
                MyComponents.SUMMONED.get(beeEntity).setPlayer(MyComponents.SUMMONED.get(attacker).getPlayer());
            }
        }
    }
}
