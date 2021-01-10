package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import com.github.galatynf.forglory.imixin.IPlayerIDMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        if (source.getAttacker() == null) return;
        if (source.getAttacker() instanceof PlayerEntity) {
            Feats feat = ((IFeatsMixin) source.getAttacker()).getFeat(Tier.TIER4);
            if (feat == null) return;
            if (feat.equals(Feats.BEES)) {
                if (((IAdrenalinMixin) source.getAttacker()).getAdrenalin() > Tier.TIER4.threshold) {
                    if (this.world.getEntityById(this.getEntityId()) instanceof HostileEntity) {
                        for (int i = 0; i < 3; i++) {
                            BeeEntity beeEntity = EntityType.BEE.spawn(world, null, null, null, this.getBlockPos().up(), SpawnReason.COMMAND, false, false);
                            if (beeEntity != null) {
                                ((IPlayerIDMixin) beeEntity).setPlayerID(source.getAttacker().getUuid());
                            }
                        }
                    }
                }
            }
        } else if (source.getAttacker() instanceof BeeEntity
                && ((IPlayerIDMixin) source.getAttacker()).getPlayerID() != null) {
            BeeEntity beeEntity = EntityType.BEE.spawn(world, null, null, null, this.getBlockPos().up(), SpawnReason.COMMAND, false, false);
            if (beeEntity != null) {
                ((IPlayerIDMixin) beeEntity).setPlayerID(((IPlayerIDMixin) source.getAttacker()).getPlayerID());
            }
        }
    }
}
