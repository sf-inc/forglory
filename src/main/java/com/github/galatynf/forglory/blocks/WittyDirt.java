package com.github.galatynf.forglory.blocks;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class WittyDirt extends Block {
    public WittyDirt(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int maxDistance = ModConfig.get().featConfig.mountainConfig.max_distance;
        PlayerEntity closestPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), maxDistance, false);

        if (closestPlayer == null
                || !(MyComponents.FEATS.get(closestPlayer).getFeat(Tier.TIER2).equals(Feats.MOUNTAIN)
                    && MyComponents.ADRENALIN.get(closestPlayer).getAdrenalin() > Tier.TIER2.threshold)) {
            world.removeBlock(pos, false);
        }
    }
}
