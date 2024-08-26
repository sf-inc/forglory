package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.ModConfig;

public enum Tier {
    TIER1,
    TIER2,
    TIER3,
    TIER4;

    public float threshold;

    // TODO: Add a function to return in-between thresholds
    // FIXME: Instead of init on join, just make a function returning the right config
    public static void initThresholds() {
        TIER1.threshold = ModConfig.get().adrenalinConfig.threshold.tier1;
        TIER2.threshold = ModConfig.get().adrenalinConfig.threshold.tier2;
        TIER3.threshold = ModConfig.get().adrenalinConfig.threshold.tier3;
        TIER4.threshold = ModConfig.get().adrenalinConfig.threshold.tier4;
    }
}
