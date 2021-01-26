package com.github.galatynf.forglory.items.misc;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.items.PoweredGem;
import net.minecraft.item.Item;

public class BloodlustGem extends PoweredGem {
    public BloodlustGem(Item.Settings settings) {
        super(settings);
        feat = Feats.BLOODLUST;
    }
}
