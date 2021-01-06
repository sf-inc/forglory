package com.github.galatynf.forglory.config.constants;

public class AdrenalinConfig {
    private AdrenalinConfig() {}

    public static int TIER1_THRESHOLD = 1000;
    public static int TIER2_THRESHOLD = 2000;   // Must be bigger than tier 1 threshold
    public static int TIER3_THRESHOLD = 3000;   // Must be bigger than tier 2 threshold
    public static int TIER4_THRESHOLD = 4000;   // Must be bigger than tier 3 threshold
    public static int MAX_AMOUNT = 5000;        // Must be bigger than tier 4 threshold
    public static final int MIN_AMOUNT = 0;

    public static float NATURAL_LOSS = -1;      // Must be a negative value
    public static float QUICK_LOSS = -25;       // Must be a negative value
    public static float SPRINT_GAIN  = 1.2f;    // Must be a positive value, bigger than natural loss
    public static float JUMP_GAIN = 10;         // Must be a positive value
    public static float FALL_MULTIPLIER = 5;    // Must be a positive value
    public static float DAMAGE_MULTIPLIER = 10; // Must be a positive value
}
