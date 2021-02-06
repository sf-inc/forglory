package com.github.galatynf.forglory.items;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.init.ItemsInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
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
    Tier tier;

    public AntiGem(Settings settings, Tier tier) {
        super(settings);
        this.tier = tier;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (MyComponents.ADRENALIN.get(user).getAdrenalin() == ConstantsConfig.MIN_AMOUNT) {
                BlockPos pos = user.getBlockPos();
                MyComponents.FEATS.get(user).removeFeat(this.tier);
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                user.getStackInHand(hand).damage(1, user,
                        playerEntity -> playerEntity.sendEquipmentBreakStatus(hand.equals(Hand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));

                ItemEntity itemEntity;
                ItemStack loot = new ItemStack(ItemsInit.essence);
                loot.setCount(world.random.nextInt(2));
                if (!loot.isEmpty()) {
                    itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), loot);
                    itemEntity.setToDefaultPickupDelay();
                    world.spawnEntity(itemEntity);
                }

                return new TypedActionResult<>(ActionResult.CONSUME, user.getStackInHand(hand));
            }
            return new TypedActionResult<>(ActionResult.FAIL, user.getStackInHand(hand));
        }
        return new TypedActionResult<>(ActionResult.PASS, user.getStackInHand(hand));
    }
}
