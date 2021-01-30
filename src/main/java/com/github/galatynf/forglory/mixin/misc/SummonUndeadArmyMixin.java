package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.entity.HeroEntity;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.EntitiesInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class SummonUndeadArmyMixin extends LivingEntity {
    protected SummonUndeadArmyMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void summonOneZombie (CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.UNDEAD_ARMY)) {
            for(int i = 0; i<4; ++i) {
                HeroEntity theHero = EntitiesInit.HERO.spawn((ServerWorld) world, null, null, null, this.getBlockPos(), SpawnReason.COMMAND, false, false);
                if (theHero == null) {
                    System.err.println("Couldn't create hero from undead army Mixin");
                    return;
                }
                MyComponents.SUMMONED.get(theHero).setSummoner(this.getUuid());
                theHero.setOwner((PlayerEntity)(Object)this);
            }

            MyComponents.FEATS.get(this).setUniqueCooldown(Feats.UNDEAD_ARMY.tier);
        }
    }

    @Inject(at=@At("INVOKE"), method = "damage", cancellable = true)
    private void preventSummonedtoHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        if(attacker instanceof HeroEntity) {
            cir.cancel();
        }
    }
}
