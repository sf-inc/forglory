package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "adrenalin")
public class AdrenalinConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 100, max = 2000)
    public int tier1_threshold = 1000;

    @ConfigEntry.BoundedDiscrete(min = 200, max = 4000)
    public int tier2_threshold = 2000;

    @ConfigEntry.BoundedDiscrete(min = 300, max = 6000)
    public int tier3_threshold = 3000;

    @ConfigEntry.BoundedDiscrete(min = 400, max = 8000)
    public int tier4_threshold = 4000;

    @ConfigEntry.BoundedDiscrete(min = 500, max = 10000)
    public int max_amount = 5000;

    public float natural_loss = -1;      // Must be a negative value
    public float quick_loss = -25;       // Must be a negative value
    public float sprint_gain  = 1.2f;    // Must be a positive value, bigger than natural loss
    public float jump_gain = 10;         // Must be a positive value
    public float fall_multiplier = 5;    // Must be a positive value
    public float damage_multiplier = 10; // Must be a positive value
}
