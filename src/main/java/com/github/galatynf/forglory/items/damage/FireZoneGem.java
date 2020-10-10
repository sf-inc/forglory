package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class FireZoneGem extends PoweredGem {
    public FireZoneGem(Settings settings) {
        super(settings);
        feat = Feats.FIRE_ZONE;
    }
}
