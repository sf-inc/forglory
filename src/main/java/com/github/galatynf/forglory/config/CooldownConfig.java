package com.github.galatynf.forglory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "cooldowns")
public class CooldownConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int healingFist = 150;

    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    public int dash = 60;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int knockbackFist = 150;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int machineGun = 250;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int mountain = 300;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int fireworker = 200;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 1000)
    public int instantKill = 200;
}
