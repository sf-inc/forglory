package com.github.galatynf.forglory.mixin.mobility;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class JumpBoostMixin extends LivingEntity {
    protected JumpBoostMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at=@At("INVOKE"), method = "tick")
    void addSpeedEffect(CallbackInfo ci) {
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER3);
        if (feat == null) return;
        if (feat.equals(Feats.JUMP_BOOST)) {
            if (((IAdrenalinMixin)this).getAdrenalin() > Tier.TIER3.threshold) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10, 4));
            }
        }
    }
}
