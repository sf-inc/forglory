package com.github.galatynf.forglory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "gui_sounds")
public class GUISoundsConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    public boolean hide_adrenalin_bar = true;

    @ConfigEntry.Gui.PrefixText
    public boolean enable_tier_jingles = true;

    public boolean enable_class_sounds = true;
}
