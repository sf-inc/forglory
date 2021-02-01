package com.github.galatynf.forglory.items;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        float adrenalin = MyComponents.ADRENALIN.get(user).getAdrenalin();
        float toAdd = amountToAdd(adrenalin, user.isSneaking());
        MyComponents.ADRENALIN.get(user).addAdrenalin(toAdd);
        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }

    private float amountToAdd(final float adrenalin, final boolean toLoose) {
        float amount;
        if (adrenalin < ModConfig.get().adrenalinConfig.tier1_threshold) {
            if (toLoose) {
                amount = -adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.tier2_threshold - ModConfig.get().adrenalinConfig.tier1_threshold) / 2.0F
                        + ModConfig.get().adrenalinConfig.tier1_threshold;
                amount -= adrenalin;
            }
        } else if (adrenalin < ModConfig.get().adrenalinConfig.tier2_threshold) {
            if (toLoose) {
                amount = ModConfig.get().adrenalinConfig.tier1_threshold - adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.tier3_threshold - ModConfig.get().adrenalinConfig.tier2_threshold) / 2.0F
                        + ModConfig.get().adrenalinConfig.tier2_threshold;
                amount -= adrenalin;
            }
        } else if (adrenalin < ModConfig.get().adrenalinConfig.tier3_threshold) {
            if (toLoose) {
                amount = ModConfig.get().adrenalinConfig.tier2_threshold - adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.tier4_threshold - ModConfig.get().adrenalinConfig.tier3_threshold) / 2.0F
                        + ModConfig.get().adrenalinConfig.tier3_threshold;
                amount -= adrenalin;
            }
        } else {
            if (toLoose) {
                amount = ModConfig.get().adrenalinConfig.tier3_threshold - adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.max_amount - ModConfig.get().adrenalinConfig.tier4_threshold) / 2.0F
                        + ModConfig.get().adrenalinConfig.tier4_threshold;
                amount -= adrenalin;
            }
        }
        return amount;
    }
}
