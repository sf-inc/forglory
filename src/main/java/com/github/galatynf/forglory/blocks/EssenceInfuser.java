package com.github.galatynf.forglory.blocks;

import com.github.galatynf.forglory.init.ItemsInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EssenceInfuser extends Block {
    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");
    public static final BooleanProperty INFINITE = BooleanProperty.of("infinite");

    public EssenceInfuser(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(CHARGED, true)
                .with(INFINITE, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
        builder.add(INFINITE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getStateManager().getDefaultState()
                .with(CHARGED, false)
                .with(INFINITE, false);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ItemStack essence;
        int essenceNumber;
        boolean isCharged = state.get(CHARGED);
        boolean isInfinite = state.get(INFINITE);

        if (isCharged) {
            if (isInfinite) {
                essenceNumber = world.random.nextInt(3) + 1;
            } else {
                essenceNumber = world.random.nextInt(2);
            }
            essence = new ItemStack(ItemsInit.essence);
            essence.setCount(essenceNumber);

            if (!essence.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), essence);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }
    }
}
