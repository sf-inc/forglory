package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.imixin.IMachineGunMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MachineGunMixin extends LivingEntity implements IMachineGunMixin {
    protected MachineGunMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow @Final public PlayerAbilities abilities;

    @Shadow @Final public PlayerInventory inventory;

    @Shadow public abstract void incrementStat(Stat<?> stat);

    @Unique
    private int forglory_machineGun;
    @Unique
    private long forglory_nextArrow;

    @Override
    public void setMachineGun(final int machineGun) {
        forglory_machineGun = machineGun;
    }

    @Inject(at=@At("HEAD"), method = "tick")
    private void shootArrows(CallbackInfo ci) {
        if (forglory_machineGun > 0) {
            ItemStack weapon = this.getMainHandStack();
            if (weapon.getItem().equals(Items.BOW) || weapon.getItem().equals(Items.CROSSBOW)) {
                if (weapon.getDamage() >= weapon.getMaxDamage() - 1) {
                    forglory_machineGun = 0;
                    return;
                }

                if (forglory_nextArrow == world.getTime()
                        || forglory_machineGun == ModConfig.get().featConfig.machine_gun_arrows) {

                    forglory_machineGun -= 1;
                    forglory_nextArrow = world.getTime() + 2;

                    ItemStack arrow = this.getArrowType(weapon);
                    if (!world.isClient) {
                        if (arrow.isEmpty()) {
                            return;
                        } else {
                            PersistentProjectileEntity persistentProjectileEntity = ((ArrowItem) Items.ARROW).createArrow(world, weapon, this);
                            persistentProjectileEntity.setProperties(this, this.pitch, this.yaw, 0.0F, 3.0F, 1.0F);

                            weapon.setDamage(weapon.getDamage() + 1);
                            world.spawnEntity(persistentProjectileEntity);
                        }
                    }
                    this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.5F);

                    if (!this.abilities.creativeMode) {
                        arrow.decrement(1);
                        if (arrow.isEmpty()) {
                            this.inventory.removeOne(arrow);
                        }
                    }
                    this.incrementStat(Stats.USED.getOrCreateStat(weapon.getItem()));
                }
            } else {
                forglory_machineGun = 0;
            }
        }
    }
}
