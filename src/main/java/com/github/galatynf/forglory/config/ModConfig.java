package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "forglory")
public class ModConfig implements ConfigData {
    public float smite_multiplier = 1.5F; //TODO prevent players to put insane values here

    @ConfigEntry.BoundedDiscrete(min = 3, max = 10)
    public int mountain_height = 4;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int life_steal_max_amount = 3;

    @ConfigEntry.Gui.CollapsibleObject
    public HealTrailConfig healTrailConfig = new HealTrailConfig();

    public static class HealTrailConfig {
        public float heal_trail_radius = 2.0F;

        @ConfigEntry.BoundedDiscrete(min = 10, max = 100)
        public int heal_trail_frequency = 20;

        @ConfigEntry.BoundedDiscrete(max = 20)
        @ConfigEntry.Gui.Tooltip(count = 2)
        public int heal_trail_wait_time = 10;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public FireZoneConfig fireZoneConfig = new FireZoneConfig();

    public static class FireZoneConfig {
        @ConfigEntry.BoundedDiscrete(min = 3, max = 10)
        public int radius = 5;

        @ConfigEntry.BoundedDiscrete(min = 500, max = 1500)
        public int circle_rate = 750;

        @ConfigEntry.BoundedDiscrete(min = 10, max = 50)
        public int fire_rate = 25;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public SuperShieldConfig superShieldConfig = new SuperShieldConfig();

    public static class SuperShieldConfig {
        @ConfigEntry.BoundedDiscrete(min = 10, max = 60)
        @ConfigEntry.Gui.Tooltip(count = 2)
        public int ticks_before_attack = 15;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
        public int damage_added_on_counterattack = 6;
    }

    @ConfigEntry.Category("GUI and sounds")
    @ConfigEntry.Gui.CollapsibleObject
    public Sounds sounds = new Sounds();

    public static class Sounds {
        public boolean enableTierJingles = true;
    }

    @ConfigEntry.Category("Cooldowns")
    @ConfigEntry.Gui.CollapsibleObject
    public Cooldowns cooldowns = new Cooldowns();

    public static class Cooldowns {
        @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
        public int healing_fist_cooldown = 100;

        @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
        public int dash_cooldown = 60; // TODO Add cooldown

        @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
        public int knockback_fist_cooldown = 150;

        @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
        public int mountain_cooldown = 300;

        @ConfigEntry.BoundedDiscrete(min = 100, max = 500)
        public int fireworker_cooldown = 200;

        @ConfigEntry.BoundedDiscrete(min = 100, max = 1000)
        public int instant_kill_cooldown = 200;
    }

    /*
    @ConfigEntry.Gui.Excluded
    InnerStuff invisibleStuff = new InnerStuff();

    static class InnerStuff {
        int a = 0;
        int b = 1;
    }
     */
}