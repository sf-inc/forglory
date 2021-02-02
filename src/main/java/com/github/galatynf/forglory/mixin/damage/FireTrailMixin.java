package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IFireTrailMixin;
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
public abstract class FireTrailMixin extends Entity implements IFireTrailMixin {
    public FireTrailMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    private boolean forglory_doFireTrail;

    @Unique
    private boolean forglory_startFireTrail;


    @Override
    public void invertFireTrail() {
        forglory_doFireTrail = !forglory_doFireTrail;
        forglory_startFireTrail = forglory_doFireTrail;
    }

    @Inject(at=@At("INVOKE"), method = "tick")
    void spawnFireTrail(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.FIRE_TRAIL)) {
            if (forglory_startFireTrail) {
                forglory_startFireTrail = false;
                for (int i=-1; i < 2; i++) {
                    for (int j =-1; j < 2; j++) {
                        BlockPos blockPos = this.getBlockPos().add(i, 0, j);
                        spawnFireT(blockPos);
                    }
                }
            }
            else if (forglory_doFireTrail) {
                BlockPos blockPos = this.getBlockPos().offset(this.getMovementDirection().getOpposite());
                spawnFireT(blockPos);
            }
        } else {
            forglory_doFireTrail = false;
        }
    }

    private void spawnFireT(BlockPos blockPos) {
        BlockPos belowBlockPos = blockPos.down();

        if (this.world.getBlockState(blockPos).isAir()
                && !this.world.getBlockState(belowBlockPos).isAir()
                && !this.world.getBlockState(belowBlockPos).getBlock().equals(BlocksInit.quickFireBlock)) {
            this.world.setBlockState(blockPos, BlocksInit.quickFireBlock.getDefaultState());
        } else if (this.world.getBlockState(blockPos.down()).isAir()
                && !this.world.getBlockState(belowBlockPos.down()).isAir()
                && !this.world.getBlockState(belowBlockPos.down()).getBlock().equals(BlocksInit.quickFireBlock)) {
            this.world.setBlockState(blockPos.down(), BlocksInit.quickFireBlock.getDefaultState());
        }
    }
}
