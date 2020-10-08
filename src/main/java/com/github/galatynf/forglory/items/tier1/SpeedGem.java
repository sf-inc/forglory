package com.github.galatynf.forglory.items.tier1;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class SpeedGem extends PoweredGem {
    public SpeedGem(Settings settings) {
        super(settings);
        feat = Feats.SPEED;
    }
}
