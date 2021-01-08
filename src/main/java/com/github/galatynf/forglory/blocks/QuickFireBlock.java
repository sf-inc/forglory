package com.github.galatynf.forglory.blocks;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Random;


public class QuickFireBlock extends AbstractFireBlock {
    private static final IntProperty AGE = Properties.AGE_25;
    public static final BooleanProperty SHORT = BooleanProperty.of("short");

    public QuickFireBlock(Settings settings) {
        super(settings, 1.5F);
        setDefaultState(getStateManager().getDefaultState().with(SHORT, false));
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        world.getBlockTickScheduler().schedule(pos, this, 1);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.getBlockTickScheduler().schedule(pos, this, 1);
        if (!state.canPlaceAt(world, pos)) {
            world.removeBlock(pos, false);
        }

        int age = state.get(AGE);
        int ageMax = Collections.max(AGE.getValues());
        if (state.get(SHORT)) {
            ageMax = 5;
        }
        if (age < ageMax) {
            state = state.with(AGE, age+1);
            world.setBlockState(pos, state, 4);
        }
        else {
            world.removeBlock(pos, false);
        }
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entity;
            Feats feat1 = ((IFeatsMixin)playerEntity).getFeat(Tier.TIER1);
            Feats feat2 = ((IFeatsMixin)playerEntity).getFeat(Tier.TIER3);
            if ((feat1 != null && feat1.equals(Feats.FIRE_TRAIL)) ||
                    (feat2 != null && feat2.equals(Feats.FIRE_ZONE))) {
                world.removeBlock(pos, false);
                return;
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(SHORT);
    }

    @Override
    protected boolean isFlammable(BlockState state) {
        return true;
    }
}
