package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.imixin.IMachineGunMixin;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
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

    protected MachineGunMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    private int forglory_machineGun;
    @Unique
    private long forglory_nextArrow;

    @Override
    public void setMachineGun(final int machineGun) {
        forglory_machineGun = machineGun;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void shootArrows(CallbackInfo ci) {
        if (forglory_machineGun > 0) {
            if (forglory_nextArrow == world.getTime()
                    || forglory_machineGun == ModConfig.get().featConfig.machine_gun_arrows) {

                if (forglory_machineGun == ModConfig.get().featConfig.machine_gun_arrows) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2 * ModConfig.get().featConfig.machine_gun_arrows, 2));
                    NetworkInit.playSound(SoundsInit.MACHINE_GUN_ID, (ServerPlayerEntity) (Object) this);
                }
                forglory_machineGun -= 1;
                forglory_nextArrow = world.getTime() + 2;

                if (!world.isClient) {
                    PersistentProjectileEntity persistentProjectileEntity = ((ArrowItem) Items.ARROW).createArrow(world, new ItemStack(Items.BOW), this);
                    persistentProjectileEntity.setProperties(this, this.pitch, this.yaw, 0.0F, 3.0F, 1.0F);
                    persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;

                    world.spawnEntity(persistentProjectileEntity);
                }
                this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.5F);
            }
        } else {
            forglory_machineGun = 0;
        }
    }
}
