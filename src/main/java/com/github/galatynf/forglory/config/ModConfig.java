package com.github.galatynf.forglory.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "forglory")
public class ModConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("adrenalin")
    @ConfigEntry.Gui.TransitiveObject
    public AdrenalinConfig adrenalinConfig = new AdrenalinConfig();

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
