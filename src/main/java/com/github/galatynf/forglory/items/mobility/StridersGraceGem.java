package com.github.galatynf.forglory.items.mobility;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class StridersGraceGem extends PoweredGem {
    public StridersGraceGem(Settings settings) {
        super(settings);
        feat = Feats.STRIDERS_GRACE;
        gem = GemsInit.mobilityGem;
    }
}
