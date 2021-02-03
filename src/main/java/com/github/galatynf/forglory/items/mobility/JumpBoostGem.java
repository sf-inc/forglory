package com.github.galatynf.forglory.items.mobility;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class JumpBoostGem extends PoweredGem {
    public JumpBoostGem(Settings settings) {
        super(settings);
        feat = Feats.JUMP_BOOST;
        gem = GemsInit.mobilityGem;
    }
}
