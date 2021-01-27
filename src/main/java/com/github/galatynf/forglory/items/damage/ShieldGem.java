package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class ShieldGem extends PoweredGem {
    public ShieldGem(Settings settings) {
        super(settings);
        feat = Feats.SUPER_SHIELD;
    }
}
