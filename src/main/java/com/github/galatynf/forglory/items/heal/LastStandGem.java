package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class LastStandGem extends PoweredGem {
    public LastStandGem(Settings settings) {
        super(settings);
        feat = Feats.LAST_STAND;
        gem = GemsInit.healGem;
    }
}
