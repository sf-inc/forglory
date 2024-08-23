package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class KnockbackFistMixin {
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract void playSound(@Nullable SoundEvent sound);

    @Inject(at = @At("HEAD"), method = "damage")
    private void stunWhenPunched(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity
                && ((IKnockbackFistPlayerMixin) attacker).forglory$isKnockbackActivated()) {
            if (((PlayerEntity) attacker).getMainHandStack().equals(ItemStack.EMPTY)) {
                if (MyComponents.FEATS.get(attacker).getForgloryClass() == FeatsClass.CENTURION) {
                    this.playSound(SoundRegistry.DIBILIS);
                }
                this.playSound(SoundRegistry.KNOCKBACK_FISTED);
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 100));
                ((IKnockbackFistPlayerMixin) attacker).forglory$setKnockBack(false);
            }
        }
    }
}
