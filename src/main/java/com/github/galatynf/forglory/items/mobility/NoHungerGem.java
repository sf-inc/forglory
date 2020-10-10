package com.github.galatynf.forglory.items.mobility;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class NoHungerGem extends PoweredGem {
    public NoHungerGem(Settings settings) {
        super(settings);
        feat = Feats.NO_HUNGER;
    }
}
