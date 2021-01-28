package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "gui_sounds")
public class GUISoundsConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    public boolean enable_tier_jingles = true;
}
