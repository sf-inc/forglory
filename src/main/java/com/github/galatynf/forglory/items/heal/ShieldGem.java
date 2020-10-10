package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class ShieldGem extends PoweredGem {
    public ShieldGem(Settings settings) {
        super(settings);
        feat = Feats.SHIELD;
    }
}
