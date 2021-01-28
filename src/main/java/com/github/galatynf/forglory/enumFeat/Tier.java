package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.ModConfig;

public enum Tier {
    NO_TIER,
    TIER1,
    TIER2,
    TIER3,
    TIER4;

    public float threshold;

    public static void initThresholds () {
        TIER1.threshold = ModConfig.get().adrenalinConfig.tier1_threshold;
        TIER2.threshold = ModConfig.get().adrenalinConfig.tier2_threshold;
        TIER3.threshold = ModConfig.get().adrenalinConfig.tier3_threshold;
        TIER4.threshold = ModConfig.get().adrenalinConfig.tier4_threshold;
    }
}
