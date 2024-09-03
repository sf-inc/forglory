package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.IMachineGunMixin;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MachineGunMixin extends LivingEntity implements IMachineGunMixin {
    @Unique
    private int forglory_arrowCount = 0;
    @Unique
    private int forglory_delay = 0;

    protected MachineGunMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void forglory$setMachineGunCount(final int machineGun) {
        this.forglory_arrowCount = machineGun;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void shootArrows(CallbackInfo ci) {
        if (this.getWorld().isClient()
                || this.forglory_arrowCount <= 0
                || --this.forglory_delay > 0) {
            return;
        }

        int shootSpeed = 3;

        if (this.forglory_arrowCount == ModConfig.get().feats.machineGunArrows) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, shootSpeed * ModConfig.get().feats.machineGunArrows, 2));
            this.playSound(SoundRegistry.MACHINE_GUN);
            if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.JUGGERNAUT) {
                this.playSound(SoundRegistry.MACHINE_GUN_VOICE);
            }
        }

        this.forglory_arrowCount--;
        this.forglory_delay = shootSpeed;

        ArrowItem arrowItem = (ArrowItem) Items.ARROW;
        ItemStack arrowStack = new ItemStack(arrowItem);
        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(this.getWorld(), arrowStack, this, new ItemStack(Items.BOW));
        persistentProjectileEntity.setVelocity(this, this.getPitch(), this.getYaw(),0.0F, 3.0F, 1.0F);
        persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;

        this.getWorld().spawnEntity(persistentProjectileEntity);
        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.5F);
    }
}
