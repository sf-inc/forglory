package com.github.galatynf.forglory.blocks;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.init.ItemsInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
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
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isChargeItem(stack) && canCharge(state)) {
            charge(player, world, pos, state);
            stack.decrementUnlessCreative(1, player);
            return ItemActionResult.success(world.isClient);
        } else {
            return hand == Hand.MAIN_HAND && isChargeItem(player.getStackInHand(Hand.OFF_HAND)) && canCharge(state)
                    ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION
                    : ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    // TODO: Replace with datagen?
    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (player.isCreative()) return state;

        boolean isCharged = state.get(CHARGED);
        boolean isInfinite = state.get(INFINITE);

        if (isCharged) {
            if (isInfinite) {
                Utils.dropEssence(world, pos, 1, 3);
            } else {
                Utils.dropEssence(world, pos, 0, 1);
            }
        }
        return state;
    }

    private static boolean isChargeItem(final ItemStack stack) {
        return stack.isOf(ItemsInit.essence);
    }

    private static boolean canCharge(final BlockState state) {
        return !state.get(INFINITE) && !state.get(CHARGED);
    }

    public static void charge(@Nullable Entity charger, World world, BlockPos pos, BlockState state) {
        BlockState newState = state.with(CHARGED, true);
        world.setBlockState(pos, newState, Block.NOTIFY_ALL);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(charger, newState));
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    public BlockState getState(final boolean isCharged, final boolean isInfinite) {
        return getStateManager().getDefaultState()
                .with(CHARGED, isCharged)
                .with(INFINITE, isInfinite);
    }
}
