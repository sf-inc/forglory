package com.github.galatynf.forglory;

import com.github.galatynf.forglory.config.constants.FeatsConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class NoMixinFeats {
    private NoMixinFeats() {}

    public static void mountainFeat(final PlayerEntity playerEntity) {
        BlockPos blockPos = playerEntity.getBlockPos();
        for (int i = 0; i < FeatsConfig.MOUNTAIN_HEIGHT; i++) {
            BlockPos blockPos2 = new BlockPos(blockPos.getX(), blockPos.getY() + i, blockPos.getZ());
            playerEntity.world.setBlockState(blockPos2, Blocks.DIRT.getDefaultState());
        }
        playerEntity.teleport(blockPos.getX(), blockPos.getY() + (FeatsConfig.MOUNTAIN_HEIGHT+1), blockPos.getZ(), true);
    }
}
