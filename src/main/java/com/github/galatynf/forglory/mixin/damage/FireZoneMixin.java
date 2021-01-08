package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.blocks.QuickFireBlock;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
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
        Feats feat = ((IFeatsMixin)this).getFeat(Tier.TIER3);
        if (feat == null) return;
        if (feat.equals(Feats.FIRE_ZONE)) {
            if (((IAdrenalinMixin)this).getAdrenalin() > Tier.TIER3.threshold) {
                forglory_playerTick = (forglory_playerTick +1) % 750;
                int tickRate = 25;
                if ((forglory_playerTick % (750/ tickRate)) == 0) {
                    forglory_fireTick = (forglory_fireTick +1) % tickRate;
                    int distance = 5;
                    double xOffset = Math.cos(forglory_fireTick * ((2*Math.PI) / tickRate));
                    double zOffset = Math.sin(forglory_fireTick * ((2*Math.PI) / tickRate));

                    BlockPos playerPos = this.getBlockPos();
                    BlockPos blockPos = playerPos.add(distance * xOffset, 0, distance * zOffset);
                    this.world.setBlockState(blockPos,
                            Forglory.quickFireBlock.getDefaultState().with(QuickFireBlock.SHORT, true));
                }
            }
        }
    }
}
