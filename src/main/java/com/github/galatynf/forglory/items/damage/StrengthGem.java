package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class StrengthGem extends PoweredGem {
    public StrengthGem(Settings settings) {
        super(settings);
        feat = Feats.STRENGTH;
    }
}
