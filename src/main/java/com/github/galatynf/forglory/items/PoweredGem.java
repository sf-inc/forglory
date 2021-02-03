package com.github.galatynf.forglory.items;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.BlocksInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class PoweredGem extends Item {
    protected Feats feat;
    protected Gem gem;
    protected PoweredGem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        BlockPos pos = user.getBlockPos().down();

        if(!world.isClient()
                && MyComponents.ADRENALIN.get(user).getAdrenalin() == ConstantsConfig.MIN_AMOUNT
                && world.getBlockState(pos).isOf(BlocksInit.essenceInfuser)) {
            MyComponents.FEATS.get(user).addOrUpdateFeat(feat);
            return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
        }
        return new TypedActionResult<>(ActionResult.FAIL, user.getStackInHand(hand));
    }
}
