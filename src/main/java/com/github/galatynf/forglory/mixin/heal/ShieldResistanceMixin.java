package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ShieldResistanceMixin extends Entity {

    public ShieldResistanceMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("INVOKE"), method = "blockedByShield", cancellable = true)
    private void blockEverywhere(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if(this.getType().equals(EntityType.PLAYER)) {
            Feats feat = ((IFeatsMixin) this).getFeat(Tier.TIER3);
            if (feat == null) return;
            if (feat.equals(Feats.SHIELD_RESISTANCE)) {
                if (((IAdrenalinMixin) this).getAdrenalin() > Tier.TIER3.threshold) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
