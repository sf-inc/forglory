package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "forglory")
public class ModConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(max = (long)1.5)
    public long smite_multiplier = (long) 1.5;

    @ConfigEntry.BoundedDiscrete(min = 3, max = 10)
    public int mountain_height = 4;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int life_steal_max_amount = 3;

    @ConfigEntry.Category(value = "Heal trail")
    public float heal_trail_radius = 2.0F;

    @ConfigEntry.Category(value = "Heal trail")
    @ConfigEntry.BoundedDiscrete(min = 10, max = 100)
    public int heal_trail_frequency = 20;

    @ConfigEntry.Category(value = "Heal trail")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
    public int heal_trail_wait_time = 10;

    /*
    @ConfigEntry.Gui.CollapsibleObject
    InnerStuff stuff = new InnerStuff();

    @ConfigEntry.Gui.Excluded
    InnerStuff invisibleStuff = new InnerStuff();

    static class InnerStuff {
        int a = 0;
        int b = 1;
    }
     */
}