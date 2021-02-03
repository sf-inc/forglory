package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class SmiteGem extends PoweredGem {
    public SmiteGem(Settings settings) {
        super(settings);
        feat = Feats.SMITE;
        gem = GemsInit.damageGem;
    }
}
