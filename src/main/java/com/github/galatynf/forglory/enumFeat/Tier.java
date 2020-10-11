package com.github.galatynf.forglory.enumFeat;

public enum Tier {
    TIER1(20),
    TIER2(4000),
    TIER3(6000),
    TIER4(8000);

    public double threshold;
    Tier(double threshold) {
        this.threshold = threshold;
    }
}
