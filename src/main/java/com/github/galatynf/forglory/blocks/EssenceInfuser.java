package com.github.galatynf.forglory.blocks;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.init.ItemsInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EssenceInfuser extends Block {
    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");
    public static final BooleanProperty INFINITE = BooleanProperty.of("infinite");

    public EssenceInfuser(Settings settings) {
        super(settings);
        setDefaultState(getState(true, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
        builder.add(INFINITE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getState(false, false);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(INFINITE)) {
            return ActionResult.PASS;
        } else {
            ItemStack itemStack = player.getStackInHand(hand);
            if (hand == Hand.MAIN_HAND && !isChargeItem(itemStack) && isChargeItem(player.getStackInHand(Hand.OFF_HAND))) {
                return ActionResult.PASS;
            } else if (isChargeItem(itemStack) && canCharge(state)) {
                charge(world, pos, state);
                if (!player.abilities.creativeMode) {
                    itemStack.decrement(1);
                }

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (player.isCreative()) return;

        boolean isCharged = state.get(CHARGED);
        boolean isInfinite = state.get(INFINITE);

        if (isCharged) {
            if (isInfinite) {
                Utils.dropEssence(world, pos, 1, 3);
            } else {
                Utils.dropEssence(world, pos, 0, 1);
            }
        }
    }

    private static boolean isChargeItem(final ItemStack stack) {
        return stack.getItem() == ItemsInit.essence;
    }

    private static boolean canCharge(final BlockState state) {
        return !state.get(CHARGED);
    }

    public static void charge(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(CHARGED, true), 3);
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    public BlockState getState(final boolean isCharged, final boolean isInfinite) {
        return getStateManager().getDefaultState()
                .with(CHARGED, isCharged)
                .with(INFINITE, isInfinite);
    }
}
