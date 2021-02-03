package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class InstantKillGem extends PoweredGem {
    public InstantKillGem(Settings settings) {
        super(settings);
        feat = Feats.INSTANT_KILL;
        gem = GemsInit.damageGem;
    }
}
