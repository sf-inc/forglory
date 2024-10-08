package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.entity.HeroEntity;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.init.EntityRegistry;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class SummonUndeadArmyMixin extends LivingEntity {
    protected SummonUndeadArmyMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void summonHeroes(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.UNDEAD_ARMY)) {
            for (int i = 0; i < ModConfig.get().feats.undeadArmy.numberSummoned; ++i) {
                Vec3i offset = new Vec3i(this.random.nextBetween(-3, 3), 1, this.random.nextBetween(-3, 3));
                HeroEntity theHero = EntityRegistry.HERO.spawn((ServerWorld) this.getWorld(), this.getBlockPos().add(offset), SpawnReason.MOB_SUMMONED);
                if (theHero != null) {
                    MyComponents.SUMMONED.get(theHero).setPlayer(this.getUuid());
                }
            }
            this.playSound(SoundRegistry.UNDEAD_ARMY_SPAWN);
            if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.CENTURION) {
                this.playSound(SoundRegistry.UNDEAD_ARMY_VOICE);
            }
            MyComponents.FEATS.get(this).setUniqueCooldown(Feats.UNDEAD_ARMY.tier);
        }
    }
}
