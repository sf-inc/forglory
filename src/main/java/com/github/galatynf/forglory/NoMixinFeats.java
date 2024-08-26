package com.github.galatynf.forglory;

import com.github.galatynf.forglory.block.QuickFireBlock;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.init.BlockRegistry;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NoMixinFeats {
    private NoMixinFeats() {
    }

    public static void dashFeat(final PlayerEntity playerEntity) {
        Vec3d velocity = playerEntity.getVelocity();
        double x = velocity.x + (((ModConfig.get().featConfig.dashIntensity + 2) / 4.0D) * Math.sin((-playerEntity.getYaw() * Math.PI) / 180.0D));
        double y = velocity.y + (((ModConfig.get().featConfig.dashIntensity + 2) / 6.0D) * Math.sin((-playerEntity.getPitch() * Math.PI) / 180.0D));
        double z = velocity.z + (((ModConfig.get().featConfig.dashIntensity + 2) / 4.0D) * Math.cos((-playerEntity.getYaw() * Math.PI) / 180.0D));
        playerEntity.setVelocity(x, y, z);
        playerEntity.velocityModified = true;
        playerEntity.playSound(SoundRegistry.DASH);
    }

    public static void mountainFeat(final PlayerEntity playerEntity) {
        BlockPos blockPos = playerEntity.getBlockPos();
        BlockPos newBlockPos = blockPos;
        BlockPos sideBlockPos;
        int height = ModConfig.get().featConfig.mountain.height;
        for (int i = 0; i < height; ++i) {
            newBlockPos = new BlockPos(blockPos.getX(), blockPos.getY() + i, blockPos.getZ());
            BlockPos blockPosHead = new BlockPos(blockPos.getX(), blockPos.getY() + i + 2, blockPos.getZ());
            if (playerEntity.getWorld().getBlockState(blockPosHead).isAir()) {
                playerEntity.getWorld().setBlockState(newBlockPos, BlockRegistry.WITTY_DIRT.getDefaultState());
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
                    playerEntity.getWorld().setBlockState(sideBlockPos, BlockRegistry.WITTY_DIRT.getDefaultState());
                }
            }
        }
        playerEntity.teleport(newBlockPos.getX(), newBlockPos.getY() + 1, newBlockPos.getZ(), true);
        playerEntity.playSound(SoundRegistry.MOUNTAIN);
    }

    private static boolean spawnedQuickFire(final World world, final BlockPos blockPos, boolean isShort) {
        if (Utils.canSpawnQuickFire(world, blockPos)) {
            if (!world.getBlockState(blockPos).isOf(BlockRegistry.QUICK_FIRE)) {
                world.setBlockState(blockPos,
                        BlockRegistry.QUICK_FIRE.getDefaultState().with(QuickFireBlock.SHORT, isShort));
            }
            return true;
        } else {
            return false;
        }
    }

    public static void spawnQuickFire(final World world, final BlockPos blockPos, boolean isShort) {
        if (spawnedQuickFire(world, blockPos, isShort)) return;

        int searchRange = isShort ? 1 : 2;
        for (int i = 1; i <= searchRange; ++i) {
            if (spawnedQuickFire(world, blockPos.down(i), isShort)) break;
            if (spawnedQuickFire(world, blockPos.up(i), isShort)) break;
        }
    }
}
