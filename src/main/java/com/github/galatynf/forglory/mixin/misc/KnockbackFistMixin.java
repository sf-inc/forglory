package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class KnockbackFistMixin {

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(at = @At("HEAD"), method = "damage")
    private void stunWhenPunched(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity
                && ((IKnockbackFistPlayerMixin) attacker).forglory$isKnockbackActivated()) {
            if (((PlayerEntity) attacker).getMainHandStack().equals(ItemStack.EMPTY)) {
                if (MyComponents.FEATS.get(attacker).getForgloryClass() == FeatsClass.CENTURION) {
                    // FIXME: Replace with world sound
                    //NetworkInit.playSoundWide(SoundsInit.DIBILIS_ID, (PlayerEntity) attacker, true);
                }
                // FIXME: Replace with world sound
                //NetworkInit.playSoundWide(SoundsInit.KNOCKBACK_FISTED_ID, (PlayerEntity) attacker, false);
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 100));
                ((IKnockbackFistPlayerMixin) attacker).forglory$setKnockBack(false);
            }
        }
    }
}
