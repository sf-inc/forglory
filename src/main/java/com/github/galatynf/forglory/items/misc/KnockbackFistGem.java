package com.github.galatynf.forglory.items.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class KnockbackFistGem extends PoweredGem {
    public KnockbackFistGem(Settings settings) {
        super(settings);
        feat = Feats.KNOCKBACK_FIST;
        gem = GemsInit.miscGem;
    }
}
