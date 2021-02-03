package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class HealingFistGem extends PoweredGem {
    public HealingFistGem(Settings settings) {
        super(settings);
        feat = Feats.HEALING_FIST;
        gem = GemsInit.healGem;
    }
}
