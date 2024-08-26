package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.AdrenalinConfig;

public enum Tier {
    TIER1,
    TIER2,
    TIER3,
    TIER4;

    private float threshold = 0.f;

    public float getThreshold() {
        return this.threshold;
    }

    public static void init(final AdrenalinConfig adrenalinConfig) {
        TIER1.threshold = adrenalinConfig.threshold.tier1;
        TIER2.threshold = adrenalinConfig.threshold.tier2;
        TIER3.threshold = adrenalinConfig.threshold.tier3;
        TIER4.threshold = adrenalinConfig.threshold.tier4;
    }

    public static float getValueBetween(final Tier tier1, final Tier tier2) {
        return (tier1.threshold + tier2.threshold) / 2.f;
    }
}
