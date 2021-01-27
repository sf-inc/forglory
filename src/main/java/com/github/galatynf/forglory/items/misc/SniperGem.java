package com.github.galatynf.forglory.items.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class SniperGem extends PoweredGem {
    public SniperGem(Settings settings) {
        super(settings);
        feat = Feats.SNIPER;
    }
}
