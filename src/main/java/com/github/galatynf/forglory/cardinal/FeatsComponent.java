package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.enumFeat.Tier;
import org.ladysnake.cca.api.v3.component.Component;

public interface FeatsComponent extends Component {
    Feats getFeat(Tier tier);
    Integer getCooldown(Tier tier);
    FeatsClass getForgloryClass();
    boolean hasAFeat();

    void addOrUpdateFeat(Feats feat);
    void removeFeat(Tier tier);
    void setUniqueCooldown(Tier tier);
    void resetCooldown(Tier tier);
    void decrementCooldowns();
}
