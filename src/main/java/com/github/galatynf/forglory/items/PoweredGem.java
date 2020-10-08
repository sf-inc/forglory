package com.github.galatynf.forglory.items;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class PoweredGem extends Item {
    protected Feats feat;
    protected PoweredGem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ((IFeatsMixin)user).addOrUpdateFeat(feat);
        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }
}
