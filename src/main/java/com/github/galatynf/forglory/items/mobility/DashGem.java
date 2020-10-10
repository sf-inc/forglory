package com.github.galatynf.forglory.items.mobility;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class DashGem extends PoweredGem {
    public DashGem(Settings settings) {
        super(settings);
        feat = Feats.DASH;
    }
}
