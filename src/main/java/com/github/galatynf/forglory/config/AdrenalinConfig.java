package com.github.galatynf.forglory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "adrenalin")
public class AdrenalinConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public Threshold threshold = new Threshold();

    public static class Threshold {
        @ConfigEntry.BoundedDiscrete(min = 100, max = 2000)
        public int tier1 = 1000;

        @ConfigEntry.BoundedDiscrete(min = 200, max = 4000)
        public int tier2 = 2000;

        @ConfigEntry.BoundedDiscrete(min = 300, max = 6000)
        public int tier3 = 3000;

        @ConfigEntry.BoundedDiscrete(min = 400, max = 8000)
        public int tier4 = 4000;
    }

    @ConfigEntry.BoundedDiscrete(min = 500, max = 10000)
    public int maxAmount = 5000;


    @ConfigEntry.BoundedDiscrete(min = -10, max = -1)
    public int naturalLoss = -1;

    @ConfigEntry.BoundedDiscrete(min = -100, max = -10)
    public int quickLoss = -25;

    @ConfigEntry.BoundedDiscrete(min = 10, max = 50)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int sprintGain = 17;

    @ConfigEntry.BoundedDiscrete(min = 3, max = 20)
    public int jumpGain = 15;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 5)
    public int attackMultiplier = 4;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 10)
    public int fallMultiplier = 8;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 20)
    public int damageMultiplier = 14;
}
