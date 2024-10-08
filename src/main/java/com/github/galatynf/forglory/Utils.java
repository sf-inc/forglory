package com.github.galatynf.forglory;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.init.ItemRegistry;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Utils {
    private Utils() {
    }

    public static boolean canUseFeat(final Object object, final Feats feat) {
        return canUseFeat(object, feat, true);
    }

    public static boolean canUseFeat(final Object object, final Feats feat, boolean serverFeat) {
        if (object == null) return false;
        if (!(object instanceof PlayerEntity playerEntity)) return false;
        if (serverFeat == playerEntity.getWorld().isClient()) return false;

        Feats playerFeat = MyComponents.FEATS.get(playerEntity).getFeat(feat.tier);
        if (playerFeat == null) return false;

        if (playerFeat.equals(feat)) {
            if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() > feat.tier.getThreshold()
                    && MyComponents.FEATS.get(playerEntity).getCooldown(feat.tier) == 0) {
                MyComponents.FEATS.get(playerEntity).resetCooldown(feat.tier);
                return true;
            }
        }
        return false;
    }

    public static boolean canSpawnQuickFire(final World world, final BlockPos fromPos) {
        BlockPos toPos = fromPos.down();
        return world.getBlockState(fromPos).isReplaceable()
                && !world.getBlockState(toPos).isReplaceable()
                && world.getBlockState(toPos).getFluidState().isEmpty();
    }

    public static float adrenalinMultiplier(final PlayerEntity playerEntity, float amount) {
        if (amount < 0) return amount;

        Feats feat = MyComponents.FEATS.get(playerEntity).getFeat(Feats.BLOODLUST.tier);
        if (feat != null) {
            if (feat.equals(Feats.BLOODLUST) && playerEntity.getHealth() > 0) {
                if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() > Tier.TIER1.getThreshold()) {
                    float value = ModConfig.get().feats.bloodlustMultiplier;
                    amount *= playerEntity.getMaxHealth() / (playerEntity.getHealth() * value) + 1 - (1 / value);
                }
            }
        }

        if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() > Tier.TIER3.getThreshold()) {
            int maxArmor = Math.max(playerEntity.getArmor(), 20);
            float multiplier = 1 + (maxArmor - playerEntity.getArmor()) / (float) maxArmor;
            amount *= multiplier;
        }
        return amount;
    }

    public static void dropEssence(final World world, final BlockPos blockPos, final int min, final int max) {
        ItemEntity itemEntity;
        ItemStack loot = new ItemStack(ItemRegistry.ESSENCE);
        loot.setCount(world.random.nextInt((max - min) + 1) + min);
        if (!loot.isEmpty()) {
            itemEntity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), loot);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
    }
}
