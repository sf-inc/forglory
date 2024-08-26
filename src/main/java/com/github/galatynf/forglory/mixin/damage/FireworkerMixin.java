package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.init.SoundRegistry;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Mixin(PlayerEntity.class)
public abstract class FireworkerMixin extends LivingEntity {
    protected FireworkerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getProjectileType", at = @At("RETURN"))
    private ItemStack setFireworkProjectile(ItemStack original, ItemStack stack) {
        if (!(stack.getItem() instanceof RangedWeaponItem) || !Utils.canUseFeat(this, Feats.FIREWORKER)) {
            return original;
        }

        if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.HUNTER) {
            this.playSound(SoundRegistry.FIREWORKER_VOICE);
        }

        FireworkExplosionComponent explosion = new FireworkExplosionComponent(
                FireworkExplosionComponent.Type.BURST,
                IntList.of(DyeColor.RED.getFireworkColor(), DyeColor.WHITE.getFireworkColor()),
                IntList.of(),
                false,
                false);
        ArrayList<FireworkExplosionComponent> explosionsList = new ArrayList<>();
        for (int i = 0; i < ModConfig.get().featConfig.fireworkerPower; ++i) {
            explosionsList.add(explosion);
        }

        ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        firework.set(
                DataComponentTypes.FIREWORKS,
                new FireworksComponent((byte) 3, explosionsList));

        return firework;
    }
}
