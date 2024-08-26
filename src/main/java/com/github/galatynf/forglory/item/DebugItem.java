package com.github.galatynf.forglory.item;

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
        if (adrenalin < ModConfig.get().adrenalinConfig.threshold.tier1) {
            if (toLoose) {
                amount = -adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.threshold.tier2 - ModConfig.get().adrenalinConfig.threshold.tier1) / 2.0F
                        + ModConfig.get().adrenalinConfig.threshold.tier1;
                amount -= adrenalin;
            }
        } else if (adrenalin < ModConfig.get().adrenalinConfig.threshold.tier2) {
            if (toLoose) {
                amount = ModConfig.get().adrenalinConfig.threshold.tier1 - adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.threshold.tier3 - ModConfig.get().adrenalinConfig.threshold.tier2) / 2.0F
                        + ModConfig.get().adrenalinConfig.threshold.tier2;
                amount -= adrenalin;
            }
        } else if (adrenalin < ModConfig.get().adrenalinConfig.threshold.tier3) {
            if (toLoose) {
                amount = ModConfig.get().adrenalinConfig.threshold.tier2 - adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.threshold.tier4 - ModConfig.get().adrenalinConfig.threshold.tier3) / 2.0F
                        + ModConfig.get().adrenalinConfig.threshold.tier3;
                amount -= adrenalin;
            }
        } else {
            if (toLoose) {
                amount = ModConfig.get().adrenalinConfig.threshold.tier3 - adrenalin;
            } else {
                amount = (ModConfig.get().adrenalinConfig.maxAmount - ModConfig.get().adrenalinConfig.threshold.tier4) / 2.0F
                        + ModConfig.get().adrenalinConfig.threshold.tier4;
                amount -= adrenalin;
            }
        }
        return amount;
    }
}
