package com.github.galatynf.forglory.item;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.block.EssenceInfuser;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class PoweredGem extends Item {
    private final Feats feat;
    private final Item gem;

    private static final Map<Tier, Rarity> TIER_RARITY = Map.of(
            Tier.TIER1, Rarity.COMMON,
            Tier.TIER2, Rarity.UNCOMMON,
            Tier.TIER3, Rarity.RARE,
            Tier.TIER4, Rarity.EPIC
    );

    public PoweredGem(Settings settings, Feats feat, Item gem) {
        super(settings.rarity(TIER_RARITY.get(feat.tier)));
        this.feat = feat;
        this.gem = gem;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        BlockPos pos = user.getBlockPos().down();
        BlockState blockState = world.getBlockState(pos);
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            if (user.isCreative()) {
                MyComponents.FEATS.get(user).addOrUpdateFeat(feat);
                return new TypedActionResult<>(ActionResult.SUCCESS, stack);

            } else if (MyComponents.ADRENALIN.get(user).getAdrenalin() == ConstantsConfig.MIN_AMOUNT
                    && blockState.isOf(BlockRegistry.essenceInfuser)
                    && blockState.get(EssenceInfuser.CHARGED)) {

                if (!blockState.get(EssenceInfuser.INFINITE)) {
                    world.setBlockState(pos, ((EssenceInfuser) BlockRegistry.essenceInfuser).getState(false, false));
                }
                MyComponents.FEATS.get(user).addOrUpdateFeat(feat);
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
                stack.decrement(1);

                Utils.dropEssence(world, pos, 0, 1);
                ItemStack loot = new ItemStack(this.gem);
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), loot);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);

                return new TypedActionResult<>(ActionResult.CONSUME, stack);
            }
            return new TypedActionResult<>(ActionResult.FAIL, stack);
        }
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }
}
