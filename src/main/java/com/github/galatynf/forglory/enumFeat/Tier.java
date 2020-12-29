package com.github.galatynf.forglory.enumFeat;

public enum Tier {
    TIER1(2000),
    TIER2(4000),
    TIER3(6000),
    TIER4(8000);

    public float threshold;
    Tier(float threshold) {
        this.threshold = threshold;
    }
}
