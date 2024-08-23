package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.gui.AdrenalinBar;
import com.github.galatynf.forglory.gui.FeatLabel;
import io.github.cottonmc.cotton.gui.client.CottonHud;

public class GuiRegistry {
    public static void init() {
        CottonHud.add(new AdrenalinBar(), 0, -3, 9, 34);

        for (int i = 0; i < Tier.values().length; ++i) {
            CottonHud.add(new FeatLabel(Tier.values()[i]), 0, 20 * (i+1), 100, 15);
        }
    }
}
