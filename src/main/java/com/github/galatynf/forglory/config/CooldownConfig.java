package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "cooldowns")
public class CooldownConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int healing_fist_cooldown = 100;

    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    public int dash_cooldown = 60;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int knockback_fist_cooldown = 150;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int machine_gun_cooldown = 250;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int mountain_cooldown = 300;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
    public int fireworker_cooldown = 200;

    @ConfigEntry.BoundedDiscrete(min = 100, max = 1000)
    public int instant_kill_cooldown = 200;
}
