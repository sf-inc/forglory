package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class SummonDogMixin extends LivingEntity {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    protected SummonDogMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    Integer forglory_theDog;

    @Inject(method = "tick", at = @At("HEAD"))
    private void summonTheDog (CallbackInfo ci) {
        if(!world.isClient()) {
            Feats feat = ((IFeatsMixin) this).getFeat(Tier.TIER1);
            if (feat == null) return;
            if (feat.equals(Feats.DOG)) {
                if (((IAdrenalinMixin) this).getAdrenalin() > Tier.TIER1.threshold) {
                    if (((IFeatsMixin) this).getCooldown(Tier.TIER1).equals(Feats.DOG.cooldown)) {
                        WolfEntity dog = EntityType.WOLF.create(world);
                        if (dog == null) {
                            System.err.println("Couldn't create dog from dog Mixin");
                            return;
                        }
                        dog.setTamed(true);
                        dog.setOwnerUuid(this.getUuid());
                        dog.setInvulnerable(true);
                        dog.setGlowing(true);
                        dog.initialize(world, world.getLocalDifficulty(this.getBlockPos()), SpawnReason.COMMAND, null, null);
                        dog.refreshPositionAndAngles(this.getBlockPos(), 0.0F, 0.0F);
                        world.spawnEntity(dog);
                        this.forglory_theDog = dog.getEntityId();
                        ((IFeatsMixin) this).setUniqueCooldown(Tier.TIER1);
                    }
                    WolfEntity dog = (WolfEntity) world.getEntityById(this.forglory_theDog);
                    if (dog == null) {
                        System.err.println("Couldn't get dog from dog Mixin");
                        return;
                    }
                    dog.applyStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 10, 0));
                } else {
                    if (((IFeatsMixin) this).getCooldown(Tier.TIER1) == 0) {
                        WolfEntity dog = (WolfEntity) world.getEntityById(this.forglory_theDog);
                        if (dog == null) {
                            System.err.println("Couldn't remove dog from dog Mixin");
                            return;
                        }
                        dog.setInvulnerable(false);
                        dog.setHealth(4);
                        dog.applyStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200, 1));
                        dog.applyStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 0));
                        dog.applyStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200, 4));
                        ((IFeatsMixin) this).resetCooldown(Tier.TIER1);
                        this.forglory_theDog = null;
                    }
                }
            }
        }
    }
}
