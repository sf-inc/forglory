package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.constants.AdrenalinConfig;

public enum Tier {
    TIER1(AdrenalinConfig.TIER1_THRESHOLD),
    TIER2(AdrenalinConfig.TIER2_THRESHOLD),
    TIER3(AdrenalinConfig.TIER3_THRESHOLD),
    TIER4(AdrenalinConfig.TIER4_THRESHOLD);

    public float threshold;
    Tier(float threshold) {
        this.threshold = threshold;
    }
}
