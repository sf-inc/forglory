package com.github.galatynf.forglory;

import com.github.galatynf.forglory.config.constants.FeatsConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class NoMixinFeats {
    private NoMixinFeats() {}

    public static void mountainFeat(final PlayerEntity playerEntity) {
        BlockPos blockPos = playerEntity.getBlockPos();
        BlockPos blockPos2 = blockPos;
        for (int i = 0; i < FeatsConfig.MOUNTAIN_HEIGHT; i++) {
            blockPos2 = new BlockPos(blockPos.getX(), blockPos.getY() + i, blockPos.getZ());
            BlockPos blockPos3 = new BlockPos(blockPos.getX(), blockPos.getY() + i + 2, blockPos.getZ());
            if (playerEntity.world.getBlockState(blockPos3).isAir()) {
                playerEntity.world.setBlockState(blockPos2, Blocks.DIRT.getDefaultState());
            } else {
                break;
            }
        }
        playerEntity.teleport(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ(), true);
    }
}
