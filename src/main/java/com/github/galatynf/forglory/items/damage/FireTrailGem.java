package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class FireTrailGem extends PoweredGem {
    public FireTrailGem(Settings settings) {
        super(settings);
        feat = Feats.FIRE_TRAIL;
        gem = GemsInit.damageGem;
    }
}
