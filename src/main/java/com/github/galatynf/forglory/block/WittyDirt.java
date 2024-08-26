package com.github.galatynf.forglory.block;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class WittyDirt extends Block {
    public WittyDirt(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int maxDistance = ModConfig.get().featConfig.mountain.maxDistance;
        Vec3d centerPos = pos.toCenterPos();
        List<ServerPlayerEntity> players = world.getPlayers(
                serverPlayerEntity -> serverPlayerEntity.getPos().isInRange(centerPos, maxDistance));

        boolean shouldRemove = true;
        for (ServerPlayerEntity player : players) {
            if (!MyComponents.FEATS.get(player).getFeat(Tier.TIER2).equals(Feats.MOUNTAIN)) continue;
            if (MyComponents.ADRENALIN.get(player).getAdrenalin() < Tier.TIER2.threshold) continue;

            shouldRemove = false;
            break;
        }

        if (shouldRemove) {
            world.removeBlock(pos, false);
        }
    }
}
