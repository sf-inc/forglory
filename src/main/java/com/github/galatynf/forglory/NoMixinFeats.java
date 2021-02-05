package com.github.galatynf.forglory;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class NoMixinFeats {
    private NoMixinFeats() {
    }

    public static void dashFeat(final PlayerEntity playerEntity) {
        Vec3d velocity = playerEntity.getVelocity();
        double x = velocity.x + (((ModConfig.get().featConfig.dash_intensity + 2) / 4.0D) * Math.sin((-playerEntity.yaw * Math.PI) / 180.0D));
        double y = velocity.y + (((ModConfig.get().featConfig.dash_intensity + 2) / 6.0D) * Math.sin((-playerEntity.pitch * Math.PI) / 180.0D));
        double z = velocity.z + (((ModConfig.get().featConfig.dash_intensity + 2) / 4.0D) * Math.cos((-playerEntity.yaw * Math.PI) / 180.0D));
        playerEntity.setVelocity(x, y, z);
        playerEntity.velocityModified = true;
        NetworkInit.playSoundWide(SoundsInit.DASH_ID, (ServerPlayerEntity) playerEntity, false);
    }

    public static void mountainFeat(final PlayerEntity playerEntity) {
        BlockPos blockPos = playerEntity.getBlockPos();
        BlockPos newBlockPos = blockPos;
        BlockPos sideBlockPos;
        int height = ModConfig.get().featConfig.mountain_height;
        for (int i = 0; i < height; i++) {
            newBlockPos = new BlockPos(blockPos.getX(), blockPos.getY() + i, blockPos.getZ());
            BlockPos blockPosHead = new BlockPos(blockPos.getX(), blockPos.getY() + i + 2, blockPos.getZ());
            if (playerEntity.world.getBlockState(blockPosHead).isAir()) {
                playerEntity.world.setBlockState(newBlockPos, Blocks.DIRT.getDefaultState());
            } else {
                break;
            }
        }
        for (Direction direction : Direction.values()) {
            if (direction.getHorizontal() != -1) {
                int heightMax = (int) (height * ((direction.getHorizontal() + 1) / 5F));
                for (int i = 0; i < heightMax; i++) {
                    sideBlockPos = new BlockPos(blockPos.getX(), blockPos.getY() + i, blockPos.getZ());
                    sideBlockPos = sideBlockPos.offset(direction);
                    playerEntity.world.setBlockState(sideBlockPos, Blocks.DIRT.getDefaultState());
                }
            }
        }
        playerEntity.teleport(newBlockPos.getX(), newBlockPos.getY() + 1, newBlockPos.getZ(), true);
        NetworkInit.playSoundWide(SoundsInit.MOUNTAIN_ID, (ServerPlayerEntity) playerEntity, false);

    }
}
