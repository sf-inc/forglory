package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IFireTrailMixin;
import com.github.galatynf.forglory.init.BlockRegistry;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class FireTrailMixin extends LivingEntity implements IFireTrailMixin {
    @Unique
    private boolean forglory_doFireTrail;
    @Unique
    private boolean forglory_startFireTrail;

    protected FireTrailMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void forglory$invertFireTrail() {
        this.forglory_doFireTrail = !this.forglory_doFireTrail;
        this.forglory_startFireTrail = this.forglory_doFireTrail;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    void spawnFireTrail(CallbackInfo ci) {
        if (Utils.canUseFeat(this, Feats.FIRE_TRAIL)) {
            if (this.forglory_startFireTrail) {
                this.forglory_startFireTrail = false;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        BlockPos blockPos = this.getBlockPos().add(i, 0, j);
                        spawnFireT(blockPos);
                        this.playSound(SoundRegistry.FIRE_TRAIL_ACT);
                    }
                }
            } else if (this.forglory_doFireTrail) {
                BlockPos blockPos = this.getBlockPos().offset(this.getMovementDirection().getOpposite());
                spawnFireT(blockPos);
            }
        } else {
            this.forglory_doFireTrail = false;
        }
    }

    @Unique
    private void spawnFireT(BlockPos blockPos) {
        BlockPos belowBlockPos = blockPos.down();
        World world = this.getWorld();

        if (world.getBlockState(blockPos).isAir()
                && !world.getBlockState(belowBlockPos).isAir()
                && !world.getBlockState(belowBlockPos).getFluidState().isEmpty()
                && !world.getBlockState(belowBlockPos).getBlock().equals(BlockRegistry.QUICK_FIRE)) {
            world.setBlockState(blockPos, BlockRegistry.QUICK_FIRE.getDefaultState());
        } else if (world.getBlockState(blockPos.down()).isAir()
                && !world.getBlockState(belowBlockPos.down()).isAir()
                && !world.getBlockState(belowBlockPos.down()).getFluidState().isEmpty()
                && !world.getBlockState(belowBlockPos.down()).getBlock().equals(BlockRegistry.QUICK_FIRE)) {
            world.setBlockState(blockPos.down(), BlockRegistry.QUICK_FIRE.getDefaultState());
        }
    }
}
