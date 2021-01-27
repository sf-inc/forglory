package com.github.galatynf.forglory;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.player.PlayerEntity;

public class Utils {
    private Utils() {}

    public static boolean canUseFeat (final Object object, final Feats feat) {
        if (object == null) return false;
        if (!(object instanceof PlayerEntity)) return false;
        PlayerEntity playerEntity = (PlayerEntity) object;

        Feats playerFeat = ((IFeatsMixin) playerEntity).getFeat(feat.tier);
        if (playerFeat == null) return false;

        if (playerFeat.equals(feat)) {
            if (((IAdrenalinMixin) playerEntity).getAdrenalin() > feat.tier.threshold
                    && ((IFeatsMixin) playerEntity).getCooldown(feat.tier) == 0) {
                ((IFeatsMixin) playerEntity).resetCooldown(feat.tier);
                return true;
            }
        }
        return false;
    }
}
