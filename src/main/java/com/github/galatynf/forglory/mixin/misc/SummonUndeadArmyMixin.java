package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.entity.HeroEntity;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.init.EntitiesInit;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class SummonUndeadArmyMixin extends LivingEntity {
    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    protected SummonUndeadArmyMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void summonOneZombie(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.UNDEAD_ARMY)) {
            Vec3i offset;
            for (int i = 0; i < ModConfig.get().featConfig.undeadArmyConfig.number_summoned; ++i) {
                offset = new Vec3i(Math.random() * 6 - 3, 1, Math.random() * 6 - 3);
                HeroEntity theHero = EntitiesInit.HERO.spawn((ServerWorld) world, null, null, null, this.getBlockPos().add(offset), SpawnReason.COMMAND, false, false);
                if (theHero == null) {
                    System.err.println("Couldn't create hero from undead army Mixin");
                    return;
                }
                MyComponents.SUMMONED.get(theHero).setPlayer(this.getUuid());
            }
            NetworkInit.playSound(SoundsInit.UNDEAD_ARMY_SPAWN_ID, (ServerPlayerEntity) (Object) this);
            if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.CENTURION) {
                NetworkInit.playSound(SoundsInit.UNDEAD_ARMY_VOICE_ID, (ServerPlayerEntity) (Object) this);
            }
            MyComponents.FEATS.get(this).setUniqueCooldown(Feats.UNDEAD_ARMY.tier);
        }
    }

    @Inject(at = @At("INVOKE"), method = "damage", cancellable = true)
    private void preventSummonedtoHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        if (attacker instanceof HeroEntity) {
            cir.cancel();
        }
    }
}
