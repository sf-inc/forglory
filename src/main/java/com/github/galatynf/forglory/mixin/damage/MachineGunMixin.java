package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.IMachineGunMixin;
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
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MachineGunMixin extends LivingEntity implements IMachineGunMixin {
    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Unique
    private int forglory_machineGun;
    @Unique
    private long forglory_nextArrow;

    protected MachineGunMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void forglory$setMachineGun(final int machineGun) {
        this.forglory_machineGun = machineGun;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void shootArrows(CallbackInfo ci) {
        if (this.forglory_machineGun > 0) {
            if (this.forglory_nextArrow == this.getWorld().getTime()
                    || this.forglory_machineGun == ModConfig.get().featConfig.machine_gun_arrows) {

                int shootSpeed = 3;

                if (this.forglory_machineGun == ModConfig.get().featConfig.machine_gun_arrows) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, shootSpeed * ModConfig.get().featConfig.machine_gun_arrows, 2));
                    // FIXME: Replace with world sound
                    //NetworkInit.playSoundWide(SoundsInit.MACHINE_GUN_ID, (ServerPlayerEntity) (Object) this, false);
                    if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.JUGGERNAUT) {
                        // FIXME: Replace with world sound
                        //NetworkInit.playSoundWide(SoundsInit.MACHINE_GUN_VOICE_ID, (ServerPlayerEntity) (Object) this, true);
                    }
                }
                this.forglory_machineGun -= 1;
                this.forglory_nextArrow = this.getWorld().getTime() + shootSpeed;

                if (!this.getWorld().isClient()) {
                    ArrowItem arrowItem = (ArrowItem) Items.ARROW;
                    ItemStack arrowStack = new ItemStack(arrowItem);
                    PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(this.getWorld(), arrowStack, this, new ItemStack(Items.BOW));
                    persistentProjectileEntity.setVelocity(this, this.getPitch(), this.getYaw(),0.0F, 3.0F, 1.0F);
                    persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;

                    this.getWorld().spawnEntity(persistentProjectileEntity);
                }
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.5F);
            }
        } else {
            this.forglory_machineGun = 0;
        }
    }
}
