package com.github.galatynf.forglory;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import net.minecraft.entity.player.PlayerEntity;

public class Utils {
    private Utils() {}

    public static boolean canUseFeat (final Object object, final Feats feat) {
        if (object == null) return false;
        if (!(object instanceof PlayerEntity)) return false;
        PlayerEntity playerEntity = (PlayerEntity) object;
        if (playerEntity.world.isClient) return false;

        Feats playerFeat = MyComponents.FEATS.get(playerEntity).getFeat(feat.tier);
        if (playerFeat == null) return false;

        if (playerFeat.equals(feat)) {
            if (((IAdrenalinMixin) playerEntity).getAdrenalin() > feat.tier.threshold
                    && MyComponents.FEATS.get(playerEntity).getCooldown(feat.tier) == 0) {
                MyComponents.FEATS.get(playerEntity).resetCooldown(feat.tier);
                return true;
            }
        }
        return false;
    }
}
