package com.github.galatynf.forglory.items.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class MountainGem extends PoweredGem {
    public MountainGem(Settings settings) {
        super(settings);
        feat = Feats.MOUNTAIN;
    }
}
