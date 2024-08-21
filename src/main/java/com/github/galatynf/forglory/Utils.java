package com.github.galatynf.forglory;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.init.ItemsInit;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Utils {
    private Utils() {
    }

    public static boolean canUseFeat(final Object object, final Feats feat) {
        if (object == null) return false;
        if (!(object instanceof PlayerEntity playerEntity)) return false;
        if (playerEntity.getWorld().isClient()) return false;

        Feats playerFeat = MyComponents.FEATS.get(playerEntity).getFeat(feat.tier);
        if (playerFeat == null) return false;

        if (playerFeat.equals(feat)) {
            if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() > feat.tier.threshold
                    && MyComponents.FEATS.get(playerEntity).getCooldown(feat.tier) == 0) {
                MyComponents.FEATS.get(playerEntity).resetCooldown(feat.tier);
                return true;
            }
        }
        return false;
    }

    public static float adrenalinMultiplier(final PlayerEntity playerEntity, float amount) {
        if (amount < 0) return amount;

        Feats feat = MyComponents.FEATS.get(playerEntity).getFeat(Feats.BLOODLUST.tier);
        if (feat != null) {
            if (feat.equals(Feats.BLOODLUST) && playerEntity.getHealth() > 0) {
                if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() > Tier.TIER1.threshold) {
                    float value = ModConfig.get().featConfig.bloodlust_multiplier;
                    amount *= playerEntity.getMaxHealth() / (playerEntity.getHealth() * value) + 1 - (1 / value);
                }
            }
        }

        if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() > ModConfig.get().adrenalinConfig.tier3_threshold) {
            int maxArmor = Math.max(playerEntity.getArmor(), 20);
            float multiplier = 1 + (maxArmor - playerEntity.getArmor()) / (float) maxArmor;
            amount *= multiplier;
        }
        return amount;
    }

    public static void dropEssence(final World world, final BlockPos blockPos, final int min, final int max) {
        ItemEntity itemEntity;
        ItemStack loot = new ItemStack(ItemsInit.essence);
        loot.setCount(world.random.nextInt((max - min) + 1) + min);
        if (!loot.isEmpty()) {
            itemEntity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), loot);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
    }
}
