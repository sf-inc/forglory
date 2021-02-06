package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.items.AntiGem;
import com.github.galatynf.forglory.items.Gem;
import com.github.galatynf.forglory.items.PoweredGem;
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

    public static final Gem damageGem = new Gem(settings);
    public static final PoweredGem smiteGem = new PoweredGem(settings, Feats.SMITE, damageGem);
    public static final PoweredGem strengthGem = new PoweredGem(settings, Feats.STRENGTH, damageGem);
    public static final PoweredGem fireTrailGem = new PoweredGem(settings, Feats.FIRE_TRAIL, damageGem);
    public static final PoweredGem machineGunGem = new PoweredGem(settings, Feats.MACHINE_GUN, damageGem);
    public static final PoweredGem damageSlowedGem = new PoweredGem(settings, Feats.DAMAGE_SLOWED, damageGem);
    public static final PoweredGem superShieldGem = new PoweredGem(settings, Feats.SUPER_SHIELD, damageGem);
    public static final PoweredGem fireworkerGem = new PoweredGem(settings, Feats.FIREWORKER, damageGem);
    public static final PoweredGem fireZoneGem = new PoweredGem(settings, Feats.FIRE_ZONE, damageGem);
    public static final PoweredGem instantKillGem = new PoweredGem(settings, Feats.INSTANT_KILL, damageGem);

    public static final Gem healGem = new Gem(settings);
    public static final PoweredGem resistanceGem = new PoweredGem(settings, Feats.RESISTANCE, healGem);
    public static final PoweredGem healingFistGem = new PoweredGem(settings, Feats.HEALING_FIST, healGem);
    public static final PoweredGem shieldResistanceGem = new PoweredGem(settings, Feats.SHIELD_RESISTANCE, healGem);
    public static final PoweredGem healTrailGem = new PoweredGem(settings, Feats.HEAL_TRAIL, healGem);
    public static final PoweredGem lastStandGem = new PoweredGem(settings, Feats.LAST_STAND, healGem);

    public static final Gem miscGem = new Gem(settings);
    public static final PoweredGem arrowComboGem = new PoweredGem(settings, Feats.ARROW_COMBO, miscGem);
    public static final PoweredGem bloodlustGem = new PoweredGem(settings, Feats.BLOODLUST, miscGem);
    public static final PoweredGem dogGem = new PoweredGem(settings, Feats.DOG, miscGem);
    public static final PoweredGem knockbackFistGem = new PoweredGem(settings, Feats.KNOCKBACK_FIST, miscGem);
    public static final PoweredGem mountainGem = new PoweredGem(settings, Feats.MOUNTAIN, miscGem);
    public static final PoweredGem beesGem = new PoweredGem(settings, Feats.BEES, miscGem);
    public static final PoweredGem invisibleGem = new PoweredGem(settings, Feats.INVISIBLE, miscGem);
    public static final PoweredGem undeadArmyGem = new PoweredGem(settings, Feats.UNDEAD_ARMY, miscGem);

    public static final Gem mobilityGem = new Gem(settings);
    public static final PoweredGem noHungerGem = new PoweredGem(settings, Feats.NO_HUNGER, mobilityGem);
    public static final PoweredGem speedGem = new PoweredGem(settings, Feats.SPEED, mobilityGem);
    public static final PoweredGem dashGem = new PoweredGem(settings, Feats.DASH, mobilityGem);
    public static final PoweredGem jumpBoostGem = new PoweredGem(settings, Feats.JUMP_BOOST, mobilityGem);
    public static final PoweredGem stridersGraceGem = new PoweredGem(settings, Feats.STRIDERS_GRACE, mobilityGem);

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
