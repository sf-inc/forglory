package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

@Config(name = "forglory")
public class ModConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("feats")
    @ConfigEntry.Gui.TransitiveObject
    public FeatConfig featConfig = new FeatConfig();

    @ConfigEntry.Category("cooldowns")
    @ConfigEntry.Gui.TransitiveObject
    public CooldownConfig cooldownConfig = new CooldownConfig();

    @ConfigEntry.Category("gui_sounds")
    @ConfigEntry.Gui.TransitiveObject
    public GUISoundsConfig guiSoundsConfig = new GUISoundsConfig();

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}