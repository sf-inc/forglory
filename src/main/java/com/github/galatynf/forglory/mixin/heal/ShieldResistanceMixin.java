package com.github.galatynf.forglory.mixin.heal;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ShieldResistanceMixin extends Entity {

    @Shadow
    public abstract boolean isBlocking();

    @Shadow
    protected ItemStack activeItemStack;

    public ShieldResistanceMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("INVOKE"), method = "blockedByShield", cancellable = true)
    private void blockEverywhere(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (Utils.canUseFeat(this, Feats.SHIELD_RESISTANCE)
                && this.isBlocking()
                && !source.isOutOfWorld()) {
            if (this.activeItemStack.getItem() == Items.SHIELD) {
                this.activeItemStack.setDamage(this.activeItemStack.getDamage() - 10);
            }
            NetworkInit.playSoundWide(SoundsInit.SHIELD_RES_HITS_ID, (PlayerEntity) (Object) this, false);
            cir.setReturnValue(true);
        }
    }

    //Serves to counteract the natural increase when damaged
    @Inject(at = @At("HEAD"), method = "damage")
    private void decreaseAdrenalinWhenAttacked(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (Utils.canUseFeat(this, Feats.SHIELD_RESISTANCE)
                && this.isBlocking()
                && !source.isOutOfWorld()
                && !source.equals(DamageSource.DROWN) ) {
            float adrenalinAmount = Utils.adrenalinMultiplier((PlayerEntity) (Object) this, amount * ModConfig.get().adrenalinConfig.damage_multiplier);
            MyComponents.ADRENALIN.get(this).addAdrenalin(-adrenalinAmount);
        }
    }
}
