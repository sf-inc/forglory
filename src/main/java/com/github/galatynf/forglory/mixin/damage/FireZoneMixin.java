package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.blocks.QuickFireBlock;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.BlocksInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class FireZoneMixin extends Entity {
    public FireZoneMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    int forglory_playerTick;
    @Unique
    int forglory_fireTick;

    @Inject(at=@At("INVOKE"), method = "tick")
    private void spawnFireZone(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.FIRE_ZONE)) {
            forglory_playerTick = (forglory_playerTick +1) % ModConfig.get().featConfig.fireZoneConfig.circle_rate;
            if ((forglory_playerTick % (ModConfig.get().featConfig.fireZoneConfig.circle_rate / ModConfig.get().featConfig.fireZoneConfig.fire_rate)) == 0) {
                forglory_fireTick = (forglory_fireTick +1) % ModConfig.get().featConfig.fireZoneConfig.fire_rate;
                double xOffset = Math.cos(forglory_fireTick * ((2*Math.PI) / ModConfig.get().featConfig.fireZoneConfig.fire_rate));
                double zOffset = Math.sin(forglory_fireTick * ((2*Math.PI) / ModConfig.get().featConfig.fireZoneConfig.fire_rate));

                BlockPos blockPos = this.getBlockPos().add(ModConfig.get().featConfig.fireZoneConfig.radius * xOffset,
                        0,
                        ModConfig.get().featConfig.fireZoneConfig.radius * zOffset);

                spawnFireZ(blockPos);
            }
        }
    }

    private void spawnFireZ(BlockPos blockPos) {
        BlockPos belowBlockPos = blockPos.down();

        if (this.world.getBlockState(blockPos).isAir()
                && !this.world.getBlockState(belowBlockPos).isAir()
                && !this.world.getBlockState(belowBlockPos).getBlock().equals(BlocksInit.quickFireBlock)) {
            this.world.setBlockState(blockPos,
                    BlocksInit.quickFireBlock.getDefaultState().with(QuickFireBlock.SHORT, true));
        } else {
            for (int i = 1; i < 3; i++) {
                if (this.world.getBlockState(blockPos.down(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.down(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.down(i)).getBlock().equals(BlocksInit.quickFireBlock)) {
                    this.world.setBlockState(blockPos.down(i),
                            BlocksInit.quickFireBlock.getDefaultState().with(QuickFireBlock.SHORT, true));
                    break;
                } else if (this.world.getBlockState(blockPos.up(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.up(i)).isAir()
                        && !this.world.getBlockState(belowBlockPos.up(i)).getBlock().equals(BlocksInit.quickFireBlock)) {
                    this.world.setBlockState(blockPos.up(i),
                            BlocksInit.quickFireBlock.getDefaultState().with(QuickFireBlock.SHORT, true));
                    break;
                }
            }
        }
    }
}
