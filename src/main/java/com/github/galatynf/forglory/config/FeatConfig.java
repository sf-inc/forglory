package com.github.galatynf.forglory.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "feats")
public class FeatConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 11, max = 25)
    @ConfigEntry.Gui.Tooltip()
    public int smite_multiplier = 15;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int combo_max = 5;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int combo_adrenalin_gain = 7;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 16)
    @ConfigEntry.Gui.Tooltip()
    public int bloodlust_multiplier = 8;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int dash_intensity = 3;

    @ConfigEntry.BoundedDiscrete(min = 5, max = 50)
    public int machine_gun_arrows = 30;

    @ConfigEntry.BoundedDiscrete(min = 3, max = 10)
    public int mountain_height = 5;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int striders_grace_speed = 3;

    @ConfigEntry.Gui.CollapsibleObject
    public SuperShieldConfig superShieldConfig = new SuperShieldConfig();

    public static class SuperShieldConfig {
        @ConfigEntry.BoundedDiscrete(min = 10, max = 60)
        @ConfigEntry.Gui.Tooltip(count = 2)
        public int ticks_before_attack = 15;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
        public int damage_added_on_counterattack = 6;
    }

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int life_steal_max_amount = 3;

    @ConfigEntry.BoundedDiscrete(min = 4, max = 8)
    public int fireworker_power = 6;

    @ConfigEntry.BoundedDiscrete(min = 5, max = 30)
    public int seconds_of_last_standing = 15;

    @ConfigEntry.Gui.CollapsibleObject
    public HealTrailConfig healTrailConfig = new HealTrailConfig();

    public static class HealTrailConfig {
        @ConfigEntry.BoundedDiscrete(min = 1, max = 3)
        public int heal_trail_radius = 2;

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

        @ConfigEntry.BoundedDiscrete(min = 4, max = 8)
        public int fire_rate = 6;

        @ConfigEntry.BoundedDiscrete(min = 5, max = 10)
        @ConfigEntry.Gui.Tooltip()
        public int fire_speed = 7;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public InstantKillConfig instantKillConfig = new InstantKillConfig();

    public static class InstantKillConfig {
        @ConfigEntry.BoundedDiscrete(min = 10, max = 100)
        public int health_percentage = 20;

        @ConfigEntry.BoundedDiscrete(min = 10, max = 50)
        public int max_damage = 30;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public UndeadArmyConfig undeadArmyConfig = new UndeadArmyConfig();

    public static class UndeadArmyConfig {
        @ConfigEntry.BoundedDiscrete(min = 1, max = 40)
        public int number_summoned = 5;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
        @ConfigEntry.Gui.Tooltip()
        public int heroes_OPness = 7;
    }
}
