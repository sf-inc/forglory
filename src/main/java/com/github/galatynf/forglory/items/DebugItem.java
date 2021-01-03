package com.github.galatynf.forglory.items;

import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static com.github.galatynf.forglory.config.constants.AdrenalinConfig.*;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        float adrenalin = ((IAdrenalinMixin)user).getAdrenalin();
        float toAdd = amountToAdd(adrenalin, user.isSneaking());
        ((IAdrenalinMixin)user).addAdrenalin(toAdd);
        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }

    private float amountToAdd(final float adrenalin, final boolean toLoose) {
        float amount;
        if (adrenalin < TIER1_THRESHOLD) {
            if (toLoose) {
                amount = -adrenalin;
            } else {
                amount = (TIER2_THRESHOLD - TIER1_THRESHOLD) / 2.f + TIER1_THRESHOLD;
                amount -= adrenalin;
            }
        } else if (adrenalin < TIER2_THRESHOLD) {
            if (toLoose) {
                amount = TIER1_THRESHOLD - adrenalin;
            } else {
                amount = (TIER3_THRESHOLD - TIER2_THRESHOLD) / 2.f + TIER2_THRESHOLD;
                amount -= adrenalin;
            }
        } else if (adrenalin < TIER3_THRESHOLD) {
            if (toLoose) {
                amount = TIER2_THRESHOLD - adrenalin;
            } else {
                amount = (TIER4_THRESHOLD - TIER3_THRESHOLD) / 2.f + TIER3_THRESHOLD;
                amount -= adrenalin;
            }
        } else {
            if (toLoose) {
                amount = TIER3_THRESHOLD - adrenalin;
            } else {
                amount = (MAX_AMOUNT - TIER4_THRESHOLD) / 2.f + TIER4_THRESHOLD;
                amount -= adrenalin;
            }
        }
        return amount;
    }
}
