package com.github.galatynf.forglory.imixin;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;

public interface IFeatsMixin {
    Feats getFeat(Tier tier);
    Integer getCooldown(Tier tier);
    void addOrUpdateFeat(Feats feat);
    void resetCooldown(Tier tier);
    void setUniqueCooldown(Tier tier);
}
