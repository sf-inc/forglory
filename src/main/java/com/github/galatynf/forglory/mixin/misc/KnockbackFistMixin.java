package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.github.galatynf.forglory.enumFeat.Tier.TIER2;

@Mixin(LivingEntity.class)
public abstract class KnockbackFistMixin extends Entity{
    @Shadow public abstract void applyStatusEffect(StatusEffectInstance effect);

    public KnockbackFistMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at=@At("HEAD"), method="damage")
    private void stunWhenPunched(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity sourceAttacker = source.getAttacker();
        if(sourceAttacker instanceof PlayerEntity
            && ((PlayerEntity) sourceAttacker).getMainHandStack() == ItemStack.EMPTY) {
            Feats feat = ((IFeatsMixin) sourceAttacker).getFeat(TIER2);
            if (feat != null && feat.equals(Feats.KNOCKBACK_FIST)) {
                if (((IAdrenalinMixin) sourceAttacker).getAdrenalin() > TIER2.threshold
                        && ((IFeatsMixin) sourceAttacker).getCooldown(TIER2) == 0) {
                    this.applyStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 100));
                    ((IFeatsMixin) sourceAttacker).resetCooldown(TIER2);
                }
            }
        }
    }
}