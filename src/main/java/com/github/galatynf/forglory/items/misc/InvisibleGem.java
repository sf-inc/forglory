package com.github.galatynf.forglory.items.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class InvisibleGem extends PoweredGem {
    public InvisibleGem(Settings settings) {
        super(settings);
        feat = Feats.INVISIBLE;
    }
}
