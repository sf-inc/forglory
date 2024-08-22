package com.github.galatynf.forglory.item;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AntiGem extends Item {
    private final Tier tier;

    public AntiGem(Settings settings, Tier tier) {
        super(settings);
        this.tier = tier;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient()) {
            if (MyComponents.ADRENALIN.get(user).getAdrenalin() == ConstantsConfig.MIN_AMOUNT
                && !MyComponents.FEATS.get(user).getFeat(this.tier).equals(Feats.NO_FEAT)) {
                BlockPos pos = user.getBlockPos();
                MyComponents.FEATS.get(user).removeFeat(this.tier);
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                stack.damage(1, user, LivingEntity.getSlotForHand(hand));

                Utils.dropEssence(world, pos, 0, 1);

                return new TypedActionResult<>(ActionResult.CONSUME, stack);
            }
            return new TypedActionResult<>(ActionResult.FAIL, stack);
        }
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }
}
