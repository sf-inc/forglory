package com.github.galatynf.forglory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "gui_sounds")
public class GUISoundsConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    public boolean hideAdrenalinBar = true;

    @ConfigEntry.Gui.PrefixText
    public boolean enableTierJingles = true;

    public boolean enableClassSounds = true;
}
