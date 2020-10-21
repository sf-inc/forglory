package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class FireTrailMixin extends Entity {
    public FireTrailMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at=@At("INVOKE"), method = "tick")
    void addFireTrail(CallbackInfo ci) {
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER1);
        if (feat == null) return;
        if (feat.equals(Feats.FIRE_TRAIL)) {
            if (((IAdrenalinMixin)this).getAdrenalin() > Tier.TIER1.threshold) {
                BlockPos blockPos = this.getBlockPos().offset(this.getMovementDirection().getOpposite());
                blockPos = blockPos.offset(this.getMovementDirection().getOpposite());
                WorldAccess worldAccess = this.world;
                if (AbstractFireBlock.method_30032(worldAccess, blockPos)) {
                    BlockState blockState = AbstractFireBlock.getState(worldAccess, blockPos);
                    worldAccess.setBlockState(blockPos, blockState, 11);
                }
            }
        }
    }
}
