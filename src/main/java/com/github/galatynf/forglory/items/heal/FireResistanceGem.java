package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class FireResistanceGem extends PoweredGem {
    public FireResistanceGem(Settings settings) {
        super(settings);
        feat = Feats.FIRE_RESISTANCE;
    }
}
