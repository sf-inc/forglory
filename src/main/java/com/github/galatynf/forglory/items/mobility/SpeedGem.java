package com.github.galatynf.forglory.items.mobility;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class SpeedGem extends PoweredGem {
    public SpeedGem(Settings settings) {
        super(settings);
        feat = Feats.SPEED;
        gem = GemsInit.mobilityGem;
    }
}
