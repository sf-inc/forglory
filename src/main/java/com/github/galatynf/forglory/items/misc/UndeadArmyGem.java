package com.github.galatynf.forglory.items.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class UndeadArmyGem extends PoweredGem {
    public UndeadArmyGem(Settings settings) {
        super(settings);
        feat = Feats.UNDEAD_ARMY;
    }
}
