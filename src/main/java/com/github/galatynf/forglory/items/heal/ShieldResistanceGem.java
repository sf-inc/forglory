package com.github.galatynf.forglory.items.heal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;
import net.minecraft.item.Item;

public class ShieldResistanceGem extends PoweredGem {
    public ShieldResistanceGem(Item.Settings settings) {
        super(settings);
        feat = Feats.SHIELD_RESISTANCE;
        gem = GemsInit.healGem;
    }
}
