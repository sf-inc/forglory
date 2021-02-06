package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.items.AntiGem;
import com.github.galatynf.forglory.items.damage.*;
import com.github.galatynf.forglory.items.heal.*;
import com.github.galatynf.forglory.items.misc.*;
import com.github.galatynf.forglory.items.mobility.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.github.galatynf.forglory.init.ItemsInit.settings;

public class GemsInit {
    private GemsInit() {
    }

    public static final AntiGem antiGemI = new AntiGem(settings.maxDamage(1), Tier.TIER1);
    public static final AntiGem antiGemII = new AntiGem(settings.maxDamage(2), Tier.TIER2);
    public static final AntiGem antiGemIII = new AntiGem(settings.maxDamage(3), Tier.TIER3);
    public static final AntiGem antiGemIV = new AntiGem(settings.maxDamage(4), Tier.TIER4);

    public static final DamageGem damageGem = new DamageGem(settings);
    public static final SmiteGem smiteGem = new SmiteGem(settings);
    public static final StrengthGem strengthGem = new StrengthGem(settings);
    public static final FireTrailGem fireTrailGem = new FireTrailGem(settings);
    public static final MachineGunGem machineGunGem = new MachineGunGem(settings);
    public static final DamageSlowedGem damageSlowedGem = new DamageSlowedGem(settings);
    public static final ShieldGem superShieldGem = new ShieldGem(settings);
    public static final FireworkerGem fireworkerGem = new FireworkerGem(settings);
    public static final FireZoneGem fireZoneGem = new FireZoneGem(settings);
    public static final InstantKillGem instantKillGem = new InstantKillGem(settings);

    public static final HealGem healGem = new HealGem(settings);
    public static final ResistanceGem resistanceGem = new ResistanceGem(settings);
    public static final HealingFistGem healingFistGem = new HealingFistGem(settings);
    public static final ShieldResistanceGem shieldResistanceGem = new ShieldResistanceGem(settings);
    public static final HealTrailGem healTrailGem = new HealTrailGem(settings);
    public static final LastStandGem lastStandGem = new LastStandGem(settings);

    public static final MiscGem miscGem = new MiscGem(settings);
    public static final ArrowComboGem arrowComboGem = new ArrowComboGem(settings);
    public static final BloodlustGem bloodlustGem = new BloodlustGem(settings);
    public static final DogGem dogGem = new DogGem(settings);
    public static final KnockbackFistGem knockbackFistGem = new KnockbackFistGem(settings);
    public static final MountainGem mountainGem = new MountainGem(settings);
    public static final BeesGem beesGem = new BeesGem(settings);
    public static final InvisibleGem invisibleGem = new InvisibleGem(settings);
    public static final UndeadArmyGem undeadArmyGem = new UndeadArmyGem(settings);

    public static final MobilityGem mobilityGem = new MobilityGem(settings);
    public static final NoHungerGem noHungerGem = new NoHungerGem(settings);
    public static final SpeedGem speedGem = new SpeedGem(settings);
    public static final DashGem dashGem = new DashGem(settings);
    public static final JumpBoostGem jumpBoostGem = new JumpBoostGem(settings);
    public static final StridersGraceGem stridersGraceGem = new StridersGraceGem(settings);

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier("forglory", "anti_gem1"), antiGemI);
        Registry.register(Registry.ITEM, new Identifier("forglory", "anti_gem2"), antiGemII);
        Registry.register(Registry.ITEM, new Identifier("forglory", "anti_gem3"), antiGemIII);
        Registry.register(Registry.ITEM, new Identifier("forglory", "anti_gem4"), antiGemIV);

        Registry.register(Registry.ITEM, new Identifier("forglory", "damage_gem"), damageGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "smite_gem"), smiteGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "strength_gem"), strengthGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "fire_trail_gem"), fireTrailGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "machine_gun_gem"), machineGunGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "damage_slowed_gem"), damageSlowedGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "super_shield_gem"), superShieldGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "fireworker_gem"), fireworkerGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "fire_zone_gem"), fireZoneGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "instant_kill_gem"), instantKillGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "heal_gem"), healGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "resistance_gem"), resistanceGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "healing_fist_gem"), healingFistGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "shield_resistance_gem"), shieldResistanceGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "heal_trail_gem"), healTrailGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "last_stand_gem"), lastStandGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "misc_gem"), miscGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "arrow_combo_gem"), arrowComboGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "bloodlust_gem"), bloodlustGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "dog_gem"), dogGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "knockback_fist_gem"), knockbackFistGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "mountain_gem"), mountainGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "bees_gem"), beesGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "invisible_gem"), invisibleGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "undead_army_gem"), undeadArmyGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "mobility_gem"), mobilityGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "no_hunger_gem"), noHungerGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "speed_gem"), speedGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "dash_gem"), dashGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "jump_boost_gem"), jumpBoostGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "striders_grace_gem"), stridersGraceGem);
    }
}
