package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class FireworkerGem extends PoweredGem {
    public FireworkerGem(Settings settings) {
        super(settings);
        feat = Feats.FIREWORKER;
        gem = GemsInit.damageGem;
    }
}
