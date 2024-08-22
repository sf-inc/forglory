package com.github.galatynf.forglory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

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


    @ConfigEntry.BoundedDiscrete(min = -10, max = -1)
    public int natural_loss = -1;

    @ConfigEntry.BoundedDiscrete(min = -100, max = -10)
    public int quick_loss = -25;

    @ConfigEntry.BoundedDiscrete(min = 10, max = 50)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int sprint_gain = 17;

    @ConfigEntry.BoundedDiscrete(min = 3, max = 20)
    public int jump_gain = 15;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 5)
    public int attack_multiplier = 4;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 10)
    public int fall_multiplier = 8;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 20)
    public int damage_multiplier = 14;
}
