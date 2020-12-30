package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class HealTrailGem extends PoweredGem {
    public HealTrailGem(Settings settings) {
        super(settings);
        feat = Feats.HEAL_TRAIL;
    }
}
