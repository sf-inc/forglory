package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.enumFeat.Tier;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;

public interface FeatsComponent extends ComponentV3 {
    Feats getFeat(Tier tier);

    Integer getCooldown(Tier tier);

    void addOrUpdateFeat(Feats feat);

    void removeFeat(Tier tier);

    void resetCooldown(Tier tier);

    void setUniqueCooldown(Tier tier);

    void decrementCooldowns();

    FeatsClass getForgloryClass();

    boolean hasAFeat();
}
