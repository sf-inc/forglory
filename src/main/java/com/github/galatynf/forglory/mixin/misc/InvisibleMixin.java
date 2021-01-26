package com.github.galatynf.forglory.mixin.misc;

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
public abstract class InvisibleMixin extends LivingEntity {
    protected InvisibleMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void addInvisibleEffect(CallbackInfo ci) {
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER4);
        if (feat == null) return;
        if (feat.equals(Feats.INVISIBLE)) {
            if (((IAdrenalinMixin)this).getAdrenalin() > Tier.TIER4.threshold &&
                    !this.onGround) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 30, 0));
            }
        }
    }
}
