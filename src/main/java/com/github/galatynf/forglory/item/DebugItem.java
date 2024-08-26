package com.github.galatynf.forglory.item;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Tier;
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
        MyComponents.ADRENALIN.get(user).setAdrenalin(getNewAmount(adrenalin, user.isSneaking()));
        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }

    private static float getNewAmount(final float adrenalin, final boolean toLoose) {
        if (adrenalin < Tier.TIER1.getThreshold()) {
            return toLoose
                    ? 0.f
                    : Tier.getValueBetween(Tier.TIER1, Tier.TIER2);
        } else if (adrenalin < Tier.TIER2.getThreshold()) {
            return toLoose
                    ? (ConstantsConfig.MIN_AMOUNT + Tier.TIER1.getThreshold()) / 2.f
                    : Tier.getValueBetween(Tier.TIER2, Tier.TIER3);
        } else if (adrenalin < Tier.TIER3.getThreshold()) {
            return toLoose
                    ? Tier.getValueBetween(Tier.TIER1, Tier.TIER2)
                    : Tier.getValueBetween(Tier.TIER3, Tier.TIER4);
        } else {
            return toLoose
                    ? Tier.getValueBetween(Tier.TIER2, Tier.TIER3)
                    : ModConfig.get().adrenalinConfig.maxAmount;
        }
    }
}
