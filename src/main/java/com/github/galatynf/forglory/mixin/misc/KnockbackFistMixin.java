package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.init.SoundsInit;
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

@Mixin(LivingEntity.class)
public abstract class KnockbackFistMixin extends Entity{
    @Shadow public abstract void applyStatusEffect(StatusEffectInstance effect);

    public KnockbackFistMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at=@At("HEAD"), method="damage")
    private void stunWhenPunched(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity
            && ((IKnockbackFistPlayerMixin)attacker).isKnockbackActivated()) {
            if (((PlayerEntity) attacker).getMainHandStack().equals(ItemStack.EMPTY)) {
                if(MyComponents.FEATS.get(attacker).getForgloryClass() == FeatsClass.CENTURION) {
                    playSound(SoundsInit.dibilis, 1F, 1F);
                }
                playSound(SoundsInit.knockback_fisted, 1, 1);
                this.applyStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 100));
                ((IKnockbackFistPlayerMixin)attacker).setKnockBack(false);
            }
        }
    }
}
