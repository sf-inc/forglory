package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class CompanionHealGem extends PoweredGem {
    public CompanionHealGem(Settings settings) {
        super(settings);
        feat = Feats.COMPANION_HEAL;
    }
}
