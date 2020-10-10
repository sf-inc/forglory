package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class DamageSlowedGem extends PoweredGem {
    public DamageSlowedGem(Settings settings) {
        super(settings);
        feat = Feats.DAMAGE_SLOWED;
    }
}
