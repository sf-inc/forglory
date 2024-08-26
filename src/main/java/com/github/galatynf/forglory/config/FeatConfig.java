package com.github.galatynf.forglory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "feats")
public class FeatConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 11, max = 25)
    @ConfigEntry.Gui.Tooltip()
    public int smiteMultiplier = 15;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int comboMax = 5;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int comboAdrenalinGain = 7;

    @ConfigEntry.BoundedDiscrete(min = 2, max = 16)
    @ConfigEntry.Gui.Tooltip()
    public int bloodlustMultiplier = 8;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int dashIntensity = 3;

    @ConfigEntry.BoundedDiscrete(min = 5, max = 50)
    public int machineGunArrows = 15;

    @ConfigEntry.Gui.CollapsibleObject
    public MountainConfig mountain = new MountainConfig();

    public static class MountainConfig {
        @ConfigEntry.BoundedDiscrete(min = 3, max = 10)
        public int height = 5;

        @ConfigEntry.BoundedDiscrete(min = 3, max = 25)
        public int maxDistance = 10;
    }

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int stridersGraceSpeed = 3;

    @ConfigEntry.Gui.CollapsibleObject
    public SuperShield superShield = new SuperShield();

    public static class SuperShield {
        @ConfigEntry.BoundedDiscrete(min = 10, max = 60)
        @ConfigEntry.Gui.Tooltip(count = 2)
        public int ticksBeforeAttack = 15;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
        public int damageAddedOnCounterattack = 6;
    }

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 1, max = 6)
    public int lifeStealMaxAmount = 3;

    @ConfigEntry.BoundedDiscrete(min = 4, max = 8)
    public int fireworkerPower = 6;

    @ConfigEntry.BoundedDiscrete(min = 5, max = 30)
    public int secondsOfLastStanding = 15;

    @ConfigEntry.Gui.CollapsibleObject
    public HealTrail healTrail = new HealTrail();

    public static class HealTrail {
        @ConfigEntry.BoundedDiscrete(min = 1, max = 3)
        public int healTrailRadius = 2;

        @ConfigEntry.BoundedDiscrete(min = 10, max = 100)
        public int healTrailFrequency = 20;

        @ConfigEntry.BoundedDiscrete(max = 20)
        @ConfigEntry.Gui.Tooltip(count = 2)
        public int healTrailWaitTime = 10;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public FireZone fireZone = new FireZone();

    public static class FireZone {
        @ConfigEntry.BoundedDiscrete(min = 3, max = 10)
        public int range = 5;

        @ConfigEntry.BoundedDiscrete(min = 2, max = 10)
        @ConfigEntry.Gui.Tooltip()
        public int speed = 4;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public InstantKill instantKill = new InstantKill();

    public static class InstantKill {
        @ConfigEntry.BoundedDiscrete(min = 20, max = 100)
        public int healthPercentage = 35;

        @ConfigEntry.BoundedDiscrete(min = 10, max = 50)
        public int maxDamage = 30;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public UndeadArmy undeadArmy = new UndeadArmy();

    public static class UndeadArmy {
        @ConfigEntry.BoundedDiscrete(min = 1, max = 40)
        public int numberSummoned = 5;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
        @ConfigEntry.Gui.Tooltip()
        public int heroesOPness = 7;
    }
}
