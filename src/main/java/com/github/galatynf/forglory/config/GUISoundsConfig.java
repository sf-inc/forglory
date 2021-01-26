package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "gui_sounds")
public class GUISoundsConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public Sounds sounds = new Sounds();

    public static class Sounds {
        public boolean enableTierJingles = true;
    }
}
