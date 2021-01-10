package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import com.github.galatynf.forglory.imixin.IShieldMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ShieldPlayerMixin implements IShieldMixin {

    @Shadow public abstract void attack(Entity target);

    @Override
    public void resetBlockedTicks() {
        forglory_lastBlocked = 15;
    }

    int forglory_lastBlocked = 0;

    @Inject(at = @At("HEAD"), method = "tick")
    public void decrementBlock(CallbackInfo ci) {
        forglory_lastBlocked--;
        if(forglory_lastBlocked <= 0)
            forglory_lastBlocked = 0;
    }

    @Inject(at=@At("HEAD"), method = "takeShieldHit")
    public void counterAttack(LivingEntity attacker, CallbackInfo ci) {
        System.out.println(forglory_lastBlocked);
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER3);
        if (feat == null) return;
        if (feat.equals(Feats.SHIELD)) {
            if (((IAdrenalinMixin) this).getAdrenalin() > Tier.TIER3.threshold) {
                System.out.println(forglory_lastBlocked);
                if(forglory_lastBlocked != 0) {
                    this.attack(attacker);
                }
            }
        }
    }
}
