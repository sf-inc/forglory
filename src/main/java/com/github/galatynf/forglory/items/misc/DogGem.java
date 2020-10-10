package com.github.galatynf.forglory.items.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;

public class DogGem extends PoweredGem {
    public DogGem(Settings settings) {
        super(settings);
        feat = Feats.DOG;
    }
}
