package com.github.galatynf.forglory.block;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Collections;


public class QuickFireBlock extends AbstractFireBlock {
    public static final MapCodec<QuickFireBlock> CODEC = createCodec(QuickFireBlock::new);
    private static final IntProperty AGE = Properties.AGE_25;
    public static final BooleanProperty SHORT = BooleanProperty.of("short");
    private static final float forglory_damage = 1.5F;
    private static final int forglory_short = 5;

    @Override
    public MapCodec<QuickFireBlock> getCodec() {
        return CODEC;
    }

    public QuickFireBlock(Settings settings) {
        super(settings, forglory_damage);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(AGE, 0)
                .with(SHORT, false));
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        world.scheduleBlockTick(pos, this, 1);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.scheduleBlockTick(pos, this, 1);
        if (!state.canPlaceAt(world, pos)) {
            world.removeBlock(pos, false);
        }

        int age = state.get(AGE);
        int ageMax = state.get(SHORT)
                ? forglory_short
                : Collections.max(AGE.getValues());

        if (age < ageMax) {
            state = state.with(AGE, age + 1);
            world.setBlockState(pos, state, Block.NO_REDRAW);
        } else {
            world.removeBlock(pos, false);
        }
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof PlayerEntity playerEntity) {
            Feats feat1 = MyComponents.FEATS.get(playerEntity).getFeat(Feats.FIRE_TRAIL.tier);
            Feats feat2 = MyComponents.FEATS.get(playerEntity).getFeat(Feats.FIRE_ZONE.tier);
            if ((feat1 != null && feat1.equals(Feats.FIRE_TRAIL)) ||
                    (feat2 != null && feat2.equals(Feats.FIRE_ZONE))) {
                world.removeBlock(pos, false);
                return;
            }
        }
        if (entity instanceof LivingEntity) {
            if (!entity.isFireImmune()) {
                entity.setFireTicks(entity.getFireTicks() + 1);
                if (entity.getFireTicks() == 0) {
                    entity.setOnFireFor(8);
                }

                entity.damage(world.getDamageSources().inFire(), forglory_damage);
            }
        }
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
