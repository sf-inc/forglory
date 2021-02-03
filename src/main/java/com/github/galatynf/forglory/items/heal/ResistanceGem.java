package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class ResistanceGem extends PoweredGem {
    public ResistanceGem(Settings settings) {
        super(settings);
        feat = Feats.RESISTANCE;
        gem = GemsInit.healGem;
    }
}
