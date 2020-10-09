package com.github.galatynf.forglory.items.tier2;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class StrengthGem extends PoweredGem {
    public StrengthGem(Settings settings) {
        super(settings);
        feat = Feats.STRENGTH;
    }
}
