package com.github.galatynf.forglory;

import com.github.galatynf.forglory.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class NoMixinFeats {
    private NoMixinFeats() {}

    public static void dashFeat (final PlayerEntity playerEntity) {
        Vec3d velocity = playerEntity.getVelocity();
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        double x = velocity.x + ( ((config.dash_intensity+2) / 4.0D) * Math.sin((-playerEntity.yaw * Math.PI) / 180.0D));
        double y = velocity.y + ( ((config.dash_intensity+2) / 6.0D) * Math.sin((-playerEntity.pitch * Math.PI) / 180.0D));
        double z = velocity.z + ( ((config.dash_intensity+2) / 4.0D) * Math.cos((-playerEntity.yaw * Math.PI) / 180.0D));
        playerEntity.setVelocity(x, y, z);
        playerEntity.velocityModified = true;
    }

    public static void mountainFeat(final PlayerEntity playerEntity) {
        BlockPos blockPos = playerEntity.getBlockPos();
        BlockPos blockPos2 = blockPos;
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        int height = config.mountain_height;
        for (int i = 0; i < height; i++) {
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
